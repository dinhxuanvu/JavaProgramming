/**
 *
 * Function.java
 * 
 * $Id: Function.java,v 1.3 2013/10/28 16:43:36 vxd9797 Exp $
 *
 * $Log: Function.java,v $
 * Revision 1.3  2013/10/28 16:43:36  vxd9797
 * Completed Product, Quotient & FunctionComparer.
 *
 * Revision 1.2  2013/10/05 03:50:12  vxd9797
 * Final Version
 *
 * Revision 1.1  2013/10/04 19:07:58  vxd9797
 * Initial Revision
 *
 *
 */

/**
 * Function is Abstract class that contains all of functionality for all Functions.
 * 
 * @author vxd9797
 */

public abstract class Function {
	
	/**
	 * Accept a double value and evaluate the function with that value.
	 */
	public abstract double evaluate(double value);
	
	/**
	 * Print the Function expression.
	 */
	public String toString() {
		return null;
	}
	
	/**
	 * Get the derivative form of Function
	 */
	public abstract Function derivative();
	
	/**
	 * Get the integral result of Function
	 */
	public abstract double integral(double a, double b, int n);
	
	/**
	 * Check if the Function is a constant
	 */
	public abstract boolean isConstant();
	
}
