/**
 *
 * Sum.java
 * 
 * $Id: Sum.java,v 1.4 2013/10/28 17:25:03 vxd9797 Exp $
 *
 * $Log: Sum.java,v $
 * Revision 1.4  2013/10/28 17:25:03  vxd9797
 * Final Version.
 *
 * Revision 1.3  2013/10/28 16:43:35  vxd9797
 * Completed Product, Quotient & FunctionComparer.
 *
 * Revision 1.2  2013/10/05 03:50:12  vxd9797
 * Final Version
 *
 * Revision 1.1  2013/10/04 19:06:32  vxd9797
 * Initial Revision
 *
 *
 */

import java.util.ArrayList;

/**
 * Sum of functions.
 * 
 * @author vxd9797
 */

public class Sum extends Function {

	ArrayList<Function> func = new ArrayList<Function>();

	/**
	 * Sum Constructor.
	 * Accept either an array of Functions or separate functions.
	 * If there are multiple constant function, they are added together to create a single constant function
	 */
	public Sum(Function... terms) {
		double totalConstant = 0;
		for (int i = 0; i < terms.length; i++) {
			if (terms[i].isConstant() == true) {
				totalConstant += terms[i].evaluate(0);
			}
			else
			{
				func.add(terms[i]);
			}
		}
		Constant c = new Constant(totalConstant);
		func.add(c);
	}

	/**
	 * Evaluate the Sum Function with a double value.
	 * @param value - Double value that is used to evaluate Sum function
	 * @return double - Value of Sum evaluation
	 */
	@Override
	public double evaluate(double value) {
		double total = 0;
		for (int i = 0; i < func.size(); i++) {
			total += func.get(i).evaluate(value);
		}
		return total;
	}

	/**
	 * Get the derivative function of Sum function
	 * 
	 * @return Function c - Return sum of the derivative functions
	 */
	@Override
	public Function derivative() {

		ArrayList<Function> fList = new ArrayList<Function>();	
		
		for (int i = 0; i < func.size(); i++) {
			fList.add(func.get(i).derivative());
		}
		
		Function[] fArray = new Function[fList.size()];

		fList.toArray(fArray);
		
		Function c = new Sum(fArray);

		return c;
	}

	/**
	 * Get the integral value of Sum function
	 * 
	 * @param double a - lower bounce of integral
	 * 		  double b - upper bounce of integral
	 * 		  int n - number of intervals between [a,b]
	 * 
	 * @return double valueSum - Integral of sum is sum of integrals
	 */
	@Override
	public double integral(double a, double b, int n) {
		double valueSum = 0;
		for (int i = 0; i < func.size(); i++) {
			valueSum += func.get(i).integral(a, b, n);
		}
		return valueSum;
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
	 * Return the string presentation of Sum function
	 * 
	 * @return string - String presentation
	 */
	public String toString() {
		String string = "(";
		for (int i = 0; i < func.size(); i++) {
			if (i == (func.size() - 1)){
				string += func.get(i).toString();
			}
			else
			{
				string += func.get(i).toString();
				string += " + ";
			}
		}
		string += ")";
		return string;
	}

}
