package command.exception;

public class RobotConnectException extends Exception {
	
	public RobotConnectException() {
			super("Connection to a robot failed");
	}
	
	public RobotConnectException(String mess) {
		super(mess);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
