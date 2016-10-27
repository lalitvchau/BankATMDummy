package exception;

public class InvalidLogin extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ATM Number and Password is Wrong ! ";
	}
    
}
