package specExceptions;

/**
 * 
 * @author Matthew Topping
 *
 *This class forms a CSVFormatException. 
 *This will be thrown and handled by the GUI when a CSV file is not readable
 *
 **/

@SuppressWarnings("serial")
// Check if the code does need serialisation.

public class CSVFormatException extends Exception{
	public CSVFormatException(){
		super();
	}
	
	public CSVFormatException(String message){
		super(message);
	}
}
