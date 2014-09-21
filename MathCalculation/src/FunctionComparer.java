/**
 *
 * FunctionComparer.java
 * 
 * $Id: FunctionComparer.java,v 1.2 2013/10/28 17:25:04 vxd9797 Exp $
 *
 * $Log: FunctionComparer.java,v $
 * Revision 1.2  2013/10/28 17:25:04  vxd9797
 * Final Version.
 *
 * Revision 1.1  2013/10/28 16:43:36  vxd9797
 * Completed Product, Quotient & FunctionComparer.
 *
 * 
 */

import java.util.ArrayList;

/**
 * Compare between two functions or compare an array of functions to find the biggest function in [xmin,xmax]
 * 
 * @author vxd9797
 */

public class FunctionComparer {
	
	/**
	 * Compare two functions in interval [a,b] to find which one is always greater.
	 * 
	 * @param f1 - first function
	 * 		  f2 - second function
	 * 		  xmin - lower bounce of interval
	 * 		  xmax - upper bounce of interval
	 * 
	 * @return int - Return 1 of first function is greater, 
	 * 				-1 if the second one is greater
	 * 				and zero if either of them are always greater
	 * 
	 */
	public int compare(Function f1, Function f2, double xmin, double xmax) {
		double interval = 0.01;
		double x = xmin;
		int panels = (int) ((xmax - xmin)/interval);
		int f1Count = 0;
		int f2Count = 0;
		double f1Value = 0.0;
		double f2Value = 0.0;
		
		// Compare value of two functions for each sub-intervals
		for (int i = 0; i < panels; i++) {
			x = xmin + i*interval;
			f1Value = f1.evaluate(x);
			f2Value = f2.evaluate(x);
			
			if (f1Value > f2Value) {
				f1Count++;
			}
			else if (f1Value < f2Value) {
				f2Count++;
			}
		}
		
		// Return 1 if f1 is always greater
		if (f1Count == panels) {
			return 1;
		}
		
		// Return 2 if f2 is always greater
		else if (f2Count == panels) {
			return -1;
		}
		
		// Return 0 if either is always greater
		else
			return 0;
	}
	
	/**
	 * Compare an array of multiple functions in interval [a,b] to find biggest one of all.
	 * 
	 * @param xmin - lower bounce of interval
	 * 		  xmax - upper bounce of interval
	 * 		  terms - array of functions
	 * 
	 * @return int - Return the biggest function in [a,b], 
	 * 				 return null if none of them are always biggest
	 * 
	 */
	public Function findBiggest(double xmin, double xmax, Function... flist) {
		
		// The biggest function if applicable
		Function fBiggest;
		ArrayList<Function> fEqual = new ArrayList<Function>();
		ArrayList<Function> newList = new ArrayList<Function>();
		
		// FunctionComparer object
		FunctionComparer fc = new FunctionComparer();
		
		for (Function function : flist) {
			newList.add(function);
		}
				
		fBiggest = newList.get(0);
		newList.remove(0);
		
		// Compare each function on the list together
		for (int i = 0; i < newList.size(); i++) {
			if (fc.compare(fBiggest, newList.get(i), xmin, xmax) == (-1)) {
				fBiggest = newList.get(i);
				fEqual.clear();
			}
			else if (fc.compare(fBiggest, newList.get(i), xmin, xmax) == 0) {
				fEqual.add(newList.get(i));
			}
		}
		
		// Check of there are more than one is the biggest
		if (fEqual.size() == 0) {
			return fBiggest;
		}
		else
			return null;
	}

}
