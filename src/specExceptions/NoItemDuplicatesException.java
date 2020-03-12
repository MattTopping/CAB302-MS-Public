package specExceptions;

/**
 * 
 * @author Matthew Topping
 *
 *This class forms a NoItemDuplicatesException. 
 *This will be thrown and handled by the GUI when an item is attempted to be initialised that already exists
 *
 **/

@SuppressWarnings("serial")
// Check if the code does need serialisation.

public class NoItemDuplicatesException extends Exception {
	public NoItemDuplicatesException(){
		super();
	}
	
	public NoItemDuplicatesException(String message){
		super(message);
	}
}
