package controller;

public class ControllerException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	String message;
	
	public ControllerException()
	{
		super();
		this.message = "Undefined";
	}
	
	public ControllerException(String message)
	{
		super();
		this.message = message;
	}
	
	public String getMessage()
	{
		return this.message;
	}
}
