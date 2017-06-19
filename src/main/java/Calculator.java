import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Properties;

/**
 * Author: Mohit Sahni. This class is the Calculator class which performs all the
 * operation ADD,SUB,MULT,DIV and LET Word and numbers are set into an array
 * Recursively parses each value in the array until it gets a return value
 */
public class Calculator {

	final static Logger logger = Logger.getLogger(Calculator.class);

	// expression is initial string which the user enters, tokens is array
	// holding the words and numbers
	private String expression;
	// token is string array to store the split expression
	private String[] tokens;
	// To display the final value is stored in result
	private long result;
	// counter keeps track of where we are in the token array ie what's the
	// position
	private int counter;
	// variable array is for saving the values generated from LET function, by
	// matching the ASCII values of char (a to z)
	private long[] variables;

	public Calculator() {
		loadLogger();
		logger.info("Created a Calculator");
	}

	// setter for expression string
	protected void setExpression(String expression) {
		this.expression = expression;
	}

	// getter for expression string
	protected String getExpression() {
		return expression;
	}

	// This function is to load the log4j properties file
	private void loadLogger() {
		try {
			Properties props = new Properties();
			// load the logger properties file
			FileInputStream istream = new FileInputStream(
					"src/main/resources/log4j.properties");

			/*
			 * FileInputStream istream =
			 * (FileInputStream)Main.class.getClassLoader()
			 * .getResourceAsStream("/log4j.properties");
			 */
			props.load(istream);
			istream.close();
			PropertyConfigurator.configure(props);
			// Logger level is set
			Logger.getRootLogger().setLevel(Level.DEBUG);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("WARNING: Logger not loaded");
		}
	}

	protected long execute() {
		logger.info("Calculator class executing");
		// check empty or null expression
		if (expression == null || expression.isEmpty()) {
			logger.error("expression string in calculator class is null or empty, program exiting");
			System.out.println("No expressions found");
			// System.exit(0);
			throw new IllegalArgumentException("No expressions found");
		}
		// 26 possible character as max size for variable[]
		variables = new long[26];
		// in order to split the string with non-word character
		tokens = expression.split("[\\W]+");
		logger.info("Expression is split into: " + (Arrays.toString(tokens)));
		result = processTokens();
		logger.info("Final value of the expression " + result);
		return result;
	}

	// uses counter variable to remember position while traversing through token
	// array
	private long processTokens() {
		long value = 0;
		// switch is used to find what is encountered while traversing and then
		// perform the desired operation accordingly
		switch (tokens[counter]) {
		case "add":
			logger.info("got ADDITION");
			value = add();
			break;
		case "sub":
			logger.info("got SUBSTRACTION");
			value = sub();
			break;
		case "mult":
			logger.info("got MULTIPLICATION");
			value = mult();
			break;
		case "div":
			logger.info("got DIVISION");
			value = div();
			break;
		case "let":
			logger.info("got LET");
			value = let();
			counter++;
			value = processTokens();
			break;
		default:
			char ch = tokens[counter].charAt(0);
			// test if variable previously set, then return that value
			if (tokens[counter].length() == 1 && (ch >= 'a' && ch <= 'z')) {
				logger.info("array value detected: " + variables[ch - 97]);
				return variables[ch - 97];
			} else {
				// else number found
				logger.info("number detected " + tokens[counter]);
				value = Long.parseLong(tokens[counter]);
			}
			break;
		}
		return value;
	}

	// add method: add(value/expression, value/expression)
	// gets the value using processTokens at each value/expression and adding
	// them
	// incrementing global counter variable which keeps track of our counter in
	// the tokens[] array
	// same algorithm is used for sub, div, and mult
	private long add() {
		counter++;
		long v1 = processTokens();
		counter++;
		long v2 = processTokens();
		long sum = v1 + v2;
		logger.info("add function generates: " + sum);
		return sum;
	}

	// subtraction method,sub(value/expression, value/expression)
	private long sub() {
		counter++;
		long v1 = processTokens();
		counter++;
		long v2 = processTokens();
		long sub = v1 - v2;
		logger.info("sub function generates: " + sub);
		return sub;
	}

	// multiplication method
	private long mult() {
		counter++;
		long v1 = processTokens();
		counter++;
		long v2 = processTokens();
		long mul = v1 * v2;
		logger.info("mult function generates: " + mul);
		return mul;
	}

	// division method
	private long div() {
		counter++;
		long v1 = processTokens();
		counter++;
		long v2 = processTokens();
		long div = v1 / v2;
		logger.info("div function generates: " + div);
		return div;
	}

	// let statement: let(<variable name>, <value expression>, <expression where
	// variable is used>)
	// for assigning values to variables:
	// differs from other methods as it returns processTokens for the expression
	// using the variable
	private long let() {
		counter++;
		char val = tokens[counter].charAt(0);
		counter++;
		variables[val - 97] = processTokens();
		long value = processTokens();
		logger.info("let function generates: " + value);
		return value;
	}

}
