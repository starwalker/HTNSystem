package tcrn.tmti.exception;

/**
 * @author Administrator
 *
 */
public class HTNSystemException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8472870625017696744L;


	/**
	 * @param string
	 * @return 
	 */
	public HTNSystemException() {}

    
    /**
     * @param message
     */
    public HTNSystemException(String message)
    {
       super(message);
    }

	

}
