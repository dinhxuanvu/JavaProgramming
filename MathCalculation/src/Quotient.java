/**
 *
 * Quotient.java
 * 
 * $Id: Quotient.java,v 1.2 2013/10/28 17:25:04 vxd9797 Exp $
 *
 * $Log: Quotient.java,v $
 * Revision 1.2  2013/10/28 17:25:04  vxd9797
 * Final Version.
 *
 * Revision 1.1  2013/10/28 16:43:36  vxd9797
 * Completed Product, Quotient & FunctionComparer.
 *
 * 
 */

/**
 * Quotient of functions (numerator and denominator)
 * 
 * @author vxd9797
 */

public class Quotient extends Function {

	// Functions for numerator and denominator
	Function num;
	Function denom;
	
	/**
	 * Constructor for Quotient function
	 * @param f1, f2 - Functions
	 * f1 - Numerator
	 * f2 - Denominator
	 */
	public Quotient (Function f1, Function f2) {
		num = f1;
		denom = f2;
	}

	/**
	 * Evaluate the Quotient Function with a double value.
	 * @param value - Double value that is used to evaluate Quotient function
	 * @return double (numerator(value)/denominator(value)) - Value of quotient evaluation 
	 */
	@Override
	public double evaluate(double value) {
		return num.evaluate(value)/denom.evaluate(value);
	}

	/**
	 * Get the derivative function of Quotient function
	 * 
	 * @return Function result - Return derivative function of quotient (zdy - ydz)/y^2)
	 */
	@Override
	public Function derivative() {
		Function f0 = new Constant(-1);
		Function f1 = new Product(num, denom.derivative());
		Function f2 = new Product(f0, denom, num.derivative());
		Function newNum = new Sum(f1, f2);
		Function newDenom = new Product(denom, denom);
		
		Function result = new Quotient(newNum, newDenom);
		
		return result;
	}
	
	/**
	 * Calculate integral value of quotient function
	 * 
	 * @param double a - lower bounce of integral
	 * 		  double b - upper bounce of integral
	 * 		  int n - number of intervals between [a,b]
	 * 
	 * @return double sum - integral value of quotient function in [a,b] using trapezoid rule
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
				sum += num.evaluate(x)/denom.evaluate(x);
			}
			else {
				x = a + i*delta;
				sum += 2*(num.evaluate(x)/denom.evaluate(x));
			}
		}
		
		sum = (delta/2)*(sum);
		return sum;
	}

	/**
	 * Check if function is constant or not
	 * 
	 * @return false - Quotient is not constant
	 */
	@Override
	public boolean isConstant() {
		return false;
	}
	
	/**
	 * Return the string presentation of Sum function
	 * 
	 * @return string - String presentation
	 */
	public String toString() {
		String string = "(";
		string += num.toString() + "/" + denom.toString();
		string += ")";
		return string;
	}

}
