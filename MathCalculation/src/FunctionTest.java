/**
 *
 * FunctionTest.java
 * 
 * $Id: FunctionTest.java,v 1.4 2013/10/28 16:43:35 vxd9797 Exp $
 *
 * $Log: FunctionTest.java,v $
 * Revision 1.4  2013/10/28 16:43:35  vxd9797
 * Completed Product, Quotient & FunctionComparer.
 *
 * Revision 1.3  2013/10/05 03:50:12  vxd9797
 * Final Version
 *
 * Revision 1.2  2013/10/04 19:14:34  vxd9797
 * Fix X syntax in Variable.java
 *
 * Revision 1.1  2013/10/04 19:07:26  vxd9797
 * Initial Revision
 *
 *
 */

/**
 * Test the Function class to confirm the Function is working properly.
 * Create four different functions to print the expression.
 * Generate the result with a double value.
 * Create the derivative form of the functions.
 * 
 * @author vxd9797
 */

public class FunctionTest {
	
	/**
	 * Main function: Perform the test.
	 * 
	 */
	public static void main(String[] args) {
		
		// Create four functions as Sum, Sine, Cosine and Constant
		Function fSum = new Sum(Variable.X, Variable.X, new Constant(7));
		Function fSine = new Sine(new Sum(Variable.X, Variable.X, new Constant(7)));
		Function fConst = new Constant(1);
		Function fCosine = new Cosine(new Sum(Variable.X, new Constant(2)));
		Function fPro = new Product(Variable.X, new Constant(5), new Sum( new Sine(Variable.X), new Constant(5)));
		Function fQuo = new Quotient(new Sum(Variable.X, new Constant(3)), new Cosine(Variable.X));
		
		// Print out the expression, result of evaluate, derivative and integral form for Function fSum
		System.out.println(fSum.toString());
		System.out.println(fSum.evaluate(1));
		System.out.println(fSum.derivative().toString());
		System.out.println(fSum.integral(0, 1, 5000));
		
		System.out.println("");
		
		// Print out the expression, result of evaluate, derivative and integral form for Function fSine
		System.out.println(fSine.toString());
		System.out.println(fSine.evaluate(2));
		System.out.println(fSine.derivative().toString());
		System.out.println(fSine.integral(0, 1, 10000));
		
		System.out.println("");
		
		// Print out the expression, result of evaluate, derivative and integral form for Function fConst
		System.out.println(fConst.toString());
		System.out.println(fConst.evaluate(3));
		System.out.println(fConst.derivative().toString());
		System.out.println(fConst.integral(0, 1, 10000));
		
		System.out.println("");
		
		// Print out the expression, result of evaluate, derivative and integral form for Function fCosine
		System.out.println(fCosine.toString());
		System.out.println(fCosine.evaluate(4));
		System.out.println(fCosine.derivative().toString());
		System.out.println(fCosine.integral(0, 1, 10000));
		
		System.out.println("");
		
		// Print out the expression, result of evaluate, derivative and integral form for Function fPro
		System.out.println(fPro.toString());
		System.out.println(fPro.evaluate(4));
		System.out.println(fPro.derivative().toString());
		System.out.println(fPro.integral(0, 1, 10000));
		
		System.out.println("");
		
		// Print out the expression, result of evaluate, derivative and integral form for Function fQuo
		System.out.println(fQuo.toString());
		System.out.println(fQuo.evaluate(4));
		System.out.println(fQuo.derivative().toString());
		System.out.println(fQuo.integral(2, 3, 10000));
		
		System.out.println("");
		
		// Test compare function
		Function xplusone = new Sum(Variable.X, new Constant(1));
		Function xsq = new Product(Variable.X, Variable.X);
		FunctionComparer fc = new FunctionComparer();
		System.out.println(fc.compare(xplusone, xsq, 0, 1)); // returns 1 since x+1 bigger here
		System.out.println(fc.compare(xplusone, xsq, 2, 5)); // returns -1 since x^2 bigger here
		System.out.println(fc.compare(xplusone, xsq, -3, 3)); // returns 0 since neither bigger here
		
		System.out.println("");
		
		// Test findBiggest function
		Function f1 = new Sum(new Product(Variable.X, Variable.X, Variable.X), new Constant(3));
		Function f2 = new Sum(new Product(Variable.X, Variable.X), new Constant(2));
		Function f3 = new Sum(Variable.X, new Constant(1));
		Function[] fArray = new Function[3];
		fArray[0] = f1;
		fArray[1] = f2;
		fArray[2] = f3;
		FunctionComparer fc2 = new FunctionComparer();
		System.out.println(fc2.findBiggest(1, 3, fArray));
	}
}
	
	
