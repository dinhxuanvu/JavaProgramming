/**
 *
 * Product.java
 * 
 * $Id: Product.java,v 1.2 2013/10/28 17:25:04 vxd9797 Exp $
 *
 * $Log: Product.java,v $
 * Revision 1.2  2013/10/28 17:25:04  vxd9797
 * Final Version.
 *
 * Revision 1.1  2013/10/28 16:43:36  vxd9797
 * Completed Product, Quotient & FunctionComparer.
 *
 * 
 */

import java.util.LinkedList;

/**
 * Product of functions.
 * 
 * @author vxd9797
 */

public class Product extends Function {
	
	// List of functions
	LinkedList<Function> func = new LinkedList<Function>();
	
	/**
	 * Constructor for Product function
	 * @param terms - Array of functions
	 */

	public Product(Function... terms) {
		
		double totalConstant = 1;
		
		for (int i = 0; i < terms.length; i++) {
			
			// If the product has only number 1, return 1 as a constant
			if (terms.length == 1 && terms[0].evaluate(0) == 1) {
				Function f = new Constant(1);
				func.add(f);
				break;
			}
			
			if (terms[i].isConstant() == true) {
				// If product has a zero, the product is zero
				if (terms[i].evaluate(0) == 0) {
					Function f = new Constant(0);
					func.add(f);
					break;
				}
				
				// Ignore multiple number 1 in product
				else if (terms[i].evaluate(0) == 1) {
				}
				else
				{
					totalConstant = totalConstant*terms[i].evaluate(0);
				}
			}
			else
			{
				func.add(terms[i]);
			}
		}
		
		// Product of constants
		Constant c = new Constant(totalConstant);
		if (c.evaluate(0) != 1) {
			func.addFirst(c);
		}
		else
		{
			
		}
	}
	
	/**
	 * Evaluate the Product Function with a double value.
	 * @param value - Double value that is used to evaluate Product function
	 * @return double valueProduct - Value of product evaluation
	 */
	@Override
	public double evaluate(double value) {
		
		double valueProduct = 1;
		
		if (func.isEmpty() == true) {
			return 0;
		}
		
		for (int i = 0; i < func.size(); i++) {
			valueProduct = valueProduct*func.get(i).evaluate(value);
		}
		return valueProduct;
	}

	/**
	 * Get the derivative function of Product function
	 * 
	 * @return Function f - Return derivative function of product (zdy + ydz)
	 */
	@Override
	public Function derivative() {
		LinkedList<Function> fCopy = new LinkedList<Function>();
		for (Function function : func) {
			fCopy.add(function);
		}

		if (fCopy.size() >= 2) {
			Function f1 = new Product(fCopy.get(0));
			fCopy.remove(0);
			Function[] fArray = new Function[fCopy.size()];

			fArray = fCopy.toArray(fArray);
			
			Function f2 = new Product(fArray);
			
			Function f3 = new Product(f1.derivative(), f2);
			Function f4 = new Product(f1, f2.derivative());

			Function f = new Sum(f3, f4);		
			return f;
		}
		else
		{
			Function fC = new Sum(fCopy.get(0).derivative());
			return fC;
		}
	}

	/**
	 * Calculate integral value of product function
	 * 
	 * @param double a - lower bounce of integral
	 * 		  double b - upper bounce of integral
	 * 		  int n - number of intervals between [a,b]
	 * 
	 * @return double sum - integral value of product function in [a,b] using trapezoid rule
	 */
	@Override
	public double integral(double a, double b, int n) {
		
		// Calculate delta value
		double delta = (b-a)/n;
		double x = a;
		double sum = 0.0;
		
		Function[] fArray = new Function[func.size()];
		func.toArray(fArray);
		Product f = new Product(fArray);
		
		// Trapezoid rule
		for (int i = 0; i < n; i++) {
			if (i == 0 || i == (n-1)) {
				x = a + i*delta;
				sum += f.evaluate(x);
			}
			else {
				x = a + i*delta;
				sum += 2*f.evaluate(x);
			}
		}
		
		sum = (delta/2)*(sum);
		return sum;
	}

	/**
	 * Check if function is constant or not
	 * 
	 * @return false - Product is not constant
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
				string += " * ";
			}
		}
		string += ")";
		return string;
	}

}
