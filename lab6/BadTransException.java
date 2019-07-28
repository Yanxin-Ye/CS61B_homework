package lab6;

public class BadTransException extends Exception {

	  public int amount;  // The invalid account number.

	  /**
	   *  Creates an exception object for nonexistent account "badAcctNumber".
	   **/
	  public BadTransException(int badAmount) {
	    super("Invalid Transaction Amount: " + badAmount);

	    amount = badAmount;
	  }
	}