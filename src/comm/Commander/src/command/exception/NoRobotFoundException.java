package command.exception;

public class NoRobotFoundException extends Exception {

	public NoRobotFoundException(String nxtName) {
		super(nxtName + " was not found!");
	}
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
