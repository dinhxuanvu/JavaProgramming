/**
 *
 * Sine.java
 * 
 * $Id: Sine.java,v 1.6 2013/10/28 17:25:03 vxd9797 Exp $
 *
 * $Log: Sine.java,v $
 * Revision 1.6  2013/10/28 17:25:03  vxd9797
 * Final Version.
 *
 * Revision 1.5  2013/10/28 16:43:35  vxd9797
 * Completed Product, Quotient & FunctionComparer.
 *
 * Revision 1.4  2013/10/26 19:01:27  vxd9797
 * Fixed the errors with Sine and Cosine.
 *
 * Revision 1.3  2013/10/05 03:50:12  vxd9797
 * Final Version
 *
 * Revision 1.2  2013/10/04 19:27:10  vxd9797
 * Fix toString problem in Sin and Cosine.
 *
 * Revision 1.1  2013/10/04 19:06:59  vxd9797
 * Initial Revision
 *
 *
 */

/**
 * Sine class presents the Sine function.
 * 
 * @author vxd9797
 */

public class Sine extends Function {

	// Sine function
	Function sin;

	/**
	 * Constructor for Sine function
	 * @param s - Function
	 */
	public Sine(Function s) {
		sin = s;
	}

	/**
	 * Calculate the sine of a value
	 * 
	 * @param value - double value
	 * @return The result of sine(value)
	 */
	@Override
	public double evaluate(double value) {
		return Math.sin(sin.evaluate(value));
	}

	/**
	 * Return the derivative function of Sine
	 * 
	 * @return Function f - Derivative function of sine which is cosine
	 */
	@Override
	public Function derivative() {
		Function fCosine = new Cosine(sin);
		Function fElse = new Product(sin.derivative());
		Function f = new Product(fElse, fCosine);
		return f;
	}

	/**
	 * Calculate integral of Sine.
	 * 
	 * @param double a - lower bounce of integral
	 * 		  double b - upper bounce of integral
	 * 		  int n - number of intervals between [a,b]
	 * 
	 * @return double sum - Integral value of sin in [a,b] using trapezoid rule
	 */
	@Override
	public double integral(double a, double b, int n) {
		
		// Calculate delta value
		double delta = (b-a)/n;
		double x = a;
		double sum = 0.0;
		
		// Trapezoid rule
		for (int i = 0; i < n; i++) {
			if (i == 0 || i == (n-1)) {
				x = a + i*delta;
				sum += Math.sin(sin.evaluate(x));
			}
			else {
				x = a + i*delta;
				sum += 2*Math.sin(sin.evaluate(x));
			}
		}
		
		sum = (delta/2)*(sum);
		return sum;
	}

	/**
	 * Check if function is constant or not
	 * 
	 * @return false - Sine is not constant
	 */
	@Override
	public boolean isConstant() {
		return false;
	}

	/**
	 * Return the representation of Sine function
	 * 
	 * @return The string representation of Sine
	 */
	public String toString() {
		return "sin" + sin.toString();
	}

}
