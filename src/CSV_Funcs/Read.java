/**
 * This file is one of the classes in the solution to the 
 * CAB302 2017 pair assignment
 * 
 */


package CSV_Funcs;

/**
 * The abstract Read class contains some methods that are used
 * when reading CSV files and don't belong in other classes
 * 
 * @author Samuel West - n9709681  
 *
 */

public abstract class Read {	
	/**
	 * Takes a string which should be an integer and converts it.
	 * The handling of if the parse fails is in the read functions.
	 * 
	 * @param the string containing the number
	 * @return the int for that string
	 *
	 */
	
	public int convInt(String num){		
		int newInt = Integer.parseInt(num);
		return newInt;
	}
	
	/**
	 * Takes a string which should be an double and converts it.
	 * Like convInt, exceptions are handled in the reading.
	 * 
	 * @param the string containing the number
	 * @return the double for that string
	 *
	 */
	
	public double convDouble(String num){		
		double newDouble = Double.parseDouble(num);
		return newDouble;		
	}
}
