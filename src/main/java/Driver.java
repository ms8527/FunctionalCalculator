import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Author Mohit Sahni
  */
public class Driver {
    final static Logger logger = Logger.getLogger(Driver.class);

    public static void main(String[] args) {
    	loadLogger();
        logger.info("program starting");
        if(args.length < 1) {
        	 logger.error("expression string in calculator class is null or empty, program exiting");
             System.out.println("No expressions found, Please enter expression like mult(2,3) or add(3,5)");
             System.exit(0);
        }
        logger.info("Entered Expression " + args[0]);
        Calculator calculator = new Calculator();
        calculator.setExpression(args[0]);
        long result = calculator.execute();
        System.out.println("Final value of the expression "+args[0]+"="+result);
        logger.info("Final value of the expression "+args[0]+"="+result);
    }

    //This function is to load the log4j properties file
    private static void loadLogger() {
        try {
            Properties props = new Properties();
         FileInputStream istream = 
        		 new FileInputStream("src/main/resources/log4j.properties");
         //InputStream systemResource = Main.class.getClassLoader().getResourceAsStream("");
        // System.out.println(systemResource);

            props.load(istream);
            istream.close();
            PropertyConfigurator.configure(props);
            //Logger level is set
            Logger.getRootLogger().setLevel(Level.DEBUG);
        }
        catch(Exception e) {
            e.printStackTrace();
            System.out.println("WARNING: Logger not loaded");
        }
    }
}