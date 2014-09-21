/**
 * Sorter.java
 * 
 * File:
 * 		$Id: Sorter.java,v 1.1 2013/10/09 02:38:18 vxd9797 Exp $
 * 
 * Revisions:
 * 		$Log: Sorter.java,v $
 * 		Revision 1.1  2013/10/09 02:38:18  vxd9797
 * 		Initial Revision
 *
 * 
 */

import java.util.*;

/**
 * Implementation of merge sort and quick sort for integers.
 * 
 * @author zjb
 * @author vxd9797
 */
public class Sorter {

	// Variables field
	private int adds = 0;
	private int compares = 0;

	/**
	 * Sorter constructor.
	 */
	private Sorter() {

	}

	/**
	 * Merges two lists, assumes that they are sorted.
	 * 
	 * @param list1
	 *            One sorted list
	 * @param list2
	 *            Another sorted list
	 * @return Merged list
	 */
	private List<Integer> merge(List<Integer> list1, List<Integer> list2) {
		List<Integer> result = new ArrayList<Integer>();
		int index1 = 0, index2 = 0;
		int len1 = list1.size();
		int len2 = list2.size();
		while (index1 < len1 && index2 < len2) {

			if (index1 >= len1) {
				compares++;
			} else
				compares += 2;

			if (list1.get(index1) <= list2.get(index2)) {
				result.add(list1.get(index1));

				adds++;

				compares++;

				index1 = index1 + 1;
			} else {
				compares++;
				result.add(list2.get(index2));
				adds++;
				index2 = index2 + 1;
			}
		}
		if (index1 < len1) {
			compares++;
			result.addAll(list1.subList(index1, len1));
		}
		if (index2 < len2) {
			compares++;
			result.addAll(list2.subList(index2, len2));
		}
		return result;
	}

	/**
	 * Sorts the given list using Merge Sort.
	 * 
	 * @param nums
	 *            List to be sorted
	 * @return Sorted list
	 */
	public List<Integer> mergeSort(List<Integer> nums) {

		if (nums.size() <= 1) {
			compares++;
			return nums;
		}

		List<Integer> odds = new ArrayList<Integer>();
		List<Integer> evens = new ArrayList<Integer>();
		boolean odd = true;
		for (Integer num : nums) {
			if (odd) {
				odds.add(num);
				adds++;
			} else {
				adds++;
				evens.add(num);
			}
			odd = !odd;
		}
		return merge(mergeSort(odds), mergeSort(evens));
	}

	/**
	 * Sorts the given list using Quick Sort.
	 * 
	 * @param nums
	 *            List to be sorted
	 * @return Sorted list
	 */
	public List<Integer> quickSort(List<Integer> nums) {
		if (nums.size() <= 1) {
			compares++;
			return nums;
		}

		int pivot = nums.get(0);

		List<Integer> less = new ArrayList<Integer>();
		List<Integer> same = new ArrayList<Integer>();
		List<Integer> more = new ArrayList<Integer>();

		for (Integer num : nums) {
			if (num < pivot) {
				less.add(num);
				compares++;
				adds++;
			} else if (num > pivot) {
				more.add(num);
				compares += 2;
				adds++;
			} else {
				same.add(pivot);
				compares += 2;
				adds++;
			}
		}

		less = quickSort(less);
		more = quickSort(more);

		less.addAll(same);

		for (int x = 0; x < same.size(); x++) {
			adds++;
		}

		less.addAll(more);

		for (int y = 0; y < same.size(); y++) {
			adds++;
		}
		return less;
	}

	/**
	 * Sorts the given list using Strand Sort.
	 * 
	 * @param nums
	 *            List to be sorted
	 * @return Sorted list
	 */
	public List<Integer> strandSort(List<Integer> nums) {

		LinkedList<Integer> newList = new LinkedList<Integer>();
		List<Integer> result = new ArrayList<Integer>();

		// Copy the nums List to a newList LinkedList
		for (int i = 0; i < nums.size(); i++) {
			adds++;
			newList.add(nums.get(i));
		}

		// Check for the case nums List is empty or has only one element
		if (nums.size() <= 1) {
			compares++;
			return nums;
		}

		while (newList.size() > 0) {
			List<Integer> inorder = new ArrayList<Integer>();
			compares++;
			inorder.add(newList.removeFirst());
			adds++;
			for (int x = 0; x < newList.size(); x++) {
				compares++;

				if (newList.get(x) >= inorder.get(inorder.size() - 1)) {
					compares++;
					inorder.add(newList.remove(x));
					adds++;
				}
			}

			result = merge(inorder, result);

		}

		return result;
	}

	/**
	 * Reset the count statistics.
	 */
	public void reset() {
		adds = 0;
		compares = 0;
	}

	/**
	 * Print the count Statistics.
	 */
	public void printStats() {
		System.out.println("Compares: " + compares + " Adds: " + adds);
	}

	/**
	 * Main method, creates a random list of Integers and sorts them using both
	 * merge sort and quick sort.
	 * 
	 */
	public static void main(String[] args) {

		// Test with Unsorted list
		System.out.println("Unsorted List Test");

		// Generate the list with random numbers
		List<Integer> nums = new ArrayList<Integer>();
		Random r = new Random();
		for (int i = 0; i < 100; i++) {
			nums.add(r.nextInt(500));
		}

		// Print out the original list
		System.out.println("Original list: ");
		for (Integer n : nums) {
			System.out.print(n + " ");
		}

		System.out.println();

		// Create a new Softer
		Sorter s = new Sorter();

		// MergeSort the list and display the modified list
		System.out.println("Merge-sorted list: ");
		List<Integer> msorted = s.mergeSort(nums);
		for (Integer n : msorted) {
			System.out.print(n + " ");
		}

		System.out.println();

		// Print the comparison statistics
		s.printStats();
		// Reset the statistics
		s.reset();

		// QuickSort the list and display the list
		System.out.println("Quick-sorted list: ");
		List<Integer> qsorted = s.quickSort(nums);
		for (Integer n : qsorted) {
			System.out.print(n + " ");
		}

		System.out.println();

		s.printStats();
		s.reset();

		// StrandSort the list and display the elements
		System.out.println("Strand-sorted list: ");
		List<Integer> ssorted = s.strandSort(nums);
		for (Integer n : ssorted) {
			System.out.print(n + " ");
		}

		System.out.println();

		s.printStats();
		s.reset();

		System.out.println();

		// Test with Sorted List
		System.out.println("Sorted List Test");

		Sorter s2 = new Sorter();

		// Generate the list with random numbers
		List<Integer> numsSorted = new ArrayList<Integer>();
		Random r2 = new Random();
		for (int i = 0; i < 100; i++) {
			numsSorted.add(r2.nextInt(500));
		}

		numsSorted = s2.mergeSort(numsSorted);
		s2.reset();

		// Print out the original sorted list
		System.out.println("Original sorted list: ");
		for (Integer n : numsSorted) {
			System.out.print(n + " ");
		}

		System.out.println();

		// MergeSort the list and display the modified list
		System.out.println("Merge-sorted list: ");
		List<Integer> msorted2 = s2.mergeSort(numsSorted);
		for (Integer n : msorted2) {
			System.out.print(n + " ");
		}

		System.out.println();

		s2.printStats();
		s2.reset();

		// QuickSort the list and display the list
		System.out.println("Quick-sorted list: ");
		List<Integer> qsorted2 = s2.quickSort(numsSorted);
		for (Integer n : qsorted2) {
			System.out.print(n + " ");
		}

		System.out.println();

		s2.printStats();
		s2.reset();

		// StrandSort the list and display the elements
		System.out.println("Strand-sorted list: ");
		List<Integer> ssorted2 = s2.strandSort(numsSorted);
		for (Integer n : ssorted2) {
			System.out.print(n + " ");
		}

		System.out.println();

		s2.printStats();
		s2.reset();
	}

}