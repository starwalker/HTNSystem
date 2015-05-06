package tcrn.tmti.exception;

/**
 * @author Manish
 *
 */
public class HTNSystemException extends Exception {

	
	private static final long serialVersionUID = -8472870625017696744L;


	
	public HTNSystemException() {}

    
    /**
     * @param message error message to throw
     */
    public HTNSystemException(String message)
    {
       super(message);
    }

	

}
