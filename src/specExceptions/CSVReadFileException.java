/**
 * This file is one of the exception classes in the solution to 
 * the CAB302 2017 pair assignment
 * 
 */


package specExceptions;

/**
 * This exception is thrown when an error occurs while reading a
 * CSV file.
 * 
 * @author Samuel West - n9709681
 * 
 */

@SuppressWarnings("serial")
public class CSVReadFileException extends Exception{
	public CSVReadFileException(){
		super();
	}
	
	public CSVReadFileException(String message){
		super(message);
	}
}
