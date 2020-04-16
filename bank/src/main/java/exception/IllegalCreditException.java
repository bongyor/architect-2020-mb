package exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class IllegalCreditException extends Exception{}
