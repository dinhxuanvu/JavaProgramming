/**
 *
 * Cosine.java
 * 
 * $Id: Cosine.java,v 1.7 2013/10/28 17:42:17 vxd9797 Exp $
 *
 * $Log: Cosine.java,v $
 * Revision 1.7  2013/10/28 17:42:17  vxd9797
 * Fixed Consine integral.
 *
 * Revision 1.6  2013/10/28 17:25:02  vxd9797
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
 * Revision 1.1  2013/10/04 19:06:09  vxd9797
 * Initial Revision
 *
 *
 */

/**
 * Sine class presents the Sine function.
 * 
 * @author vxd9797
 */

public class Cosine extends Function {

	// Cosine function
	Function cs;

	/**
	 * Constructor for Cosine function
	 * @param c - Function
	 */
	public Cosine(Function c) {
		cs = c;
	}

	/**
	 * Calculate the cosine of a value
	 * 
	 * @param value - double value
	 * @return The result of cosine(value)
	 */
	@Override
	public double evaluate(double value) {
		return Math.cos(cs.evaluate(value));
	}

	/**
	 * Return the derivative function of Cosine
	 * 
	 * @return Function f - Derivative function of cosine which is sine
	 */
	@Override
	public Function derivative() {
		Function fSine = new Sine(cs);
		Function fOne = new Constant(-1);
		Function fElse = new Product(fOne, cs.derivative());
		Function f = new Product(fElse, fSine);
		return f;
	}

	/**
	 * Calculate integral of Cosine.
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
				sum += Math.cos(cs.evaluate(x));
			}
			else {
				x = a + i*delta;
				sum += 2*Math.cos(cs.evaluate(x));
			}
		}
		
		sum = (delta/2)*(sum);
		return sum;
	}

	/**
	 * Check if function is constant or not
	 * 
	 * @return false - Cosine is not constant
	 */
	@Override
	public boolean isConstant() {
		return false;
	}

	/**
	 * Return the representation of Cosine function
	 * 
	 * @return The string representation of Cosine
	 */
	public String toString() {
		return "cosine" + cs.toString();
	}

}
