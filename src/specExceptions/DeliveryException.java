package specExceptions;

/**
 * 
 * @author Matthew Topping
 *
 *This class forms a DeliveryException. 
 *This will be thrown and handled by the GUI when...
 *
 **/

@SuppressWarnings("serial")
// Check if the code does need serialisation.

public class DeliveryException extends Exception {
	public DeliveryException(){
		super();
	}
	
	public DeliveryException(String message){
		super(message);
	}
}
