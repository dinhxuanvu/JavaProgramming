/**
 *
 * Constant.java
 * 
 * $Id: Constant.java,v 1.4 2013/10/28 17:25:03 vxd9797 Exp $
 *
 * $Log: Constant.java,v $
 * Revision 1.4  2013/10/28 17:25:03  vxd9797
 * Final Version.
 *
 * Revision 1.3  2013/10/28 16:43:35  vxd9797
 * Completed Product, Quotient & FunctionComparer.
 *
 * Revision 1.2  2013/10/05 03:50:12  vxd9797
 * Final Version
 *
 * Revision 1.1  2013/10/04 19:07:46  vxd9797
 * Initial Revision
 *
 *
 */

/**
 * Constant function presents a constant which doesn't change regardless of the parameter.
 * 
 * @author vxd9797
 */

public class Constant extends Function {
	
	private double value;
	
	/**
	 * Constant constructor.
	 * Accept a double value as parameter
	 */
	public Constant(double value) {
		this.value = value;
	}

	/**
	 * Evaluate the value of Constant function
	 * 
	 * @param value - Double
	 * @return value - the value of function
	 */
	@Override
	public double evaluate(double value) {
		return this.value;
	}

	/**
	 * Get the derivative form of the Constant function
	 * 
	 * @return 0 - The derivative of constant is always 0
	 */
	@Override
	public Function derivative() {
		Constant c = new Constant(0);
		return c;
	}

	/**
	 * Get the integral value of constant. 
	 * 
	 * @param double a - lower bounce of integral
	 * 		  double b - upper bounce of integral
	 * 		  int n - number of intervals between [a,b]
	 * 
	 * @return inV - Integral value of constant in [a,b]
	 */
	@Override
	public double integral(double a, double b, int n) {
		double inV = value*(b - a);
		return inV;
	}

	/**
	 * Check if function is constant or not
	 * 
	 * @return false - Cosine is not constant
	 */
	@Override
	public boolean isConstant() {
		return true;
	}
	
	/**
	 * Get the string presentation of constant function
	 * 
	 * @return String(value) - Convert the value to String
	 */
	public String toString() {
		return Double.toString(value);
	}

}
