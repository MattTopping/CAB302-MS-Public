package specExceptions;

/**
 * 
 * @author Matthew Topping
 *
 *This class forms a NoItemDuplicatesException. 
 *This will be thrown and handled by the GUI when an item doesn't exist in stock
 *
 **/

@SuppressWarnings("serial")
// Check if the code does need serialisation.

public class ItemDoesNotExistException extends Exception {
	public ItemDoesNotExistException(){
		super();
	}
	
	public ItemDoesNotExistException(String message){
		super(message);
	}
}

