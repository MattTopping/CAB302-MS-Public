package specExceptions;

/**
 * 
 * @author Matthew Topping
 *
 *This class forms a StockException. 
 *This will be thrown and handled by the GUI when...
 *
 **/

@SuppressWarnings("serial")
//Check if the code does need serialisation.

public class StockException extends Exception {
	public StockException(){
		super();
	}
	
	public StockException(String message){
		super(message);
	}
}
