/**
 *
 * Variable.java
 * 
 * $Id: Variable.java,v 1.5 2013/10/28 17:25:03 vxd9797 Exp $
 *
 * $Log: Variable.java,v $
 * Revision 1.5  2013/10/28 17:25:03  vxd9797
 * Final Version.
 *
 * Revision 1.4  2013/10/28 16:43:35  vxd9797
 * Completed Product, Quotient & FunctionComparer.
 *
 * Revision 1.3  2013/10/05 03:50:12  vxd9797
 * Final Version
 *
 * Revision 1.2  2013/10/04 19:14:31  vxd9797
 * Fix X syntax in Variable.java
 *
 * Revision 1.1  2013/10/04 19:06:23  vxd9797
 * Initial Revision
 *
 *
 */

/**
 * Variable function presents a variable x which is always the same.
 * 
 * @author vxd9797
 */

public class Variable extends Function {

	// Variable X is same and only
	public static final Variable X = new Variable();

	private Function f = new Constant(1);

	/**
	 * Constructor of a variable.
	 */
	private Variable() {

	}

	/**
	 * Evaluate the result of variable with a double value
	 * Accept a double value as parameter
	 * 
	 * @param value - Double 
	 * @return value - the result of evaluation
	 */
	@Override
	public double evaluate(double value) {
		return value;
	}

	/**
	 * Get derivation function of Variable function
	 * 
	 * @return Function f - f is a Constant function with value 1
	 */
	@Override
	public Function derivative() {
		return f;
	}

	/**
	 * Get the integral value of Variable function
	 * 
	 * @param double a - lower bounce of integral
	 * 		  double b - upper bounce of integral
	 * 		  int n - number of intervals between [a,b]
	 * 
	 * @return double value - Integral valua of Variable X in [a,b]
	 */
	@Override
	public double integral(double a, double b, int n) {
		double bottom = 0.0;
		double top = 0.0;
		bottom = Math.sqrt(a)/2;
		top = Math.sqrt(b)/2;
		double value = top - bottom;
		return value;
	}

	/**
	 * Check if function is constant or not
	 * 
	 * @return false - Variable is not constant
	 */
	@Override
	public boolean isConstant() {
		return false;
	}
	/**
	 * Get the string presentation of variable function
	 * 
	 * @return x - Always return the "x" as variable is the same and only
	 */
	public String toString() {
		return "x";
	}

}