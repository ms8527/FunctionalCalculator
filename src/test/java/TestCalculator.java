import org.junit.Test;
import static org.junit.Assert.*;


public class TestCalculator {
	
	 @Test(expected=IllegalArgumentException.class)
	    public void testWithNull(){
	    	Calculator calculator = new Calculator();
	    	calculator.setExpression("");
	    	assertEquals("","",calculator.execute());
	    }
	 
    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        calculator.setExpression("add(1,2)");
        assertEquals("add(1,2) = 3 ", 3L, calculator.execute());
    }

    @Test
    public void testAddAndMult() {
        Calculator calculator = new Calculator();
        calculator.setExpression("add(1, mult(2,3))");
        assertEquals("add(1, mult(2,3)) = 7", 7L, calculator.execute());
    }

    @Test
    public void testMultAndDiv() {
        Calculator calculator = new Calculator();
        calculator.setExpression("mult(add(2,2),div(9,3))");
        assertEquals("mult(add(2,2),div(9,3)) = 12", 12L, calculator.execute());
    }

    @Test
    public void testLetAndAdd() {
        Calculator calculator = new Calculator();
        calculator.setExpression("let(a,5,add(5,a))");
        assertEquals("let(a,5,add(5,a)) = 10", 10L, calculator.execute());
    }

    @Test
    public void testMultLetAndMultAndAdd() {
        Calculator calculator = new Calculator();
        calculator.setExpression("let(a,5,let(b,mult(a,10),add(b,a)))");
        assertEquals("let(a,5,let(b,mult(a,10),add(b,a))) = 55", 55L, calculator.execute());
    }

    @Test
    public void testMultLetWithMultAdd() {
        Calculator calculator = new Calculator();
        calculator.setExpression("let(a,let(b,10,add(b,b)),let(b,20,add(a,b)))");
        assertEquals("let(a,let(b,10,add(b,b)),let(b,20,add(a,b))) = 40", 40L, calculator.execute());
    }
    
   
}
