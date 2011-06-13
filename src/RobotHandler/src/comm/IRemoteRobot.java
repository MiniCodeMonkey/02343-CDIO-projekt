package comm;

import java.rmi.Remote;

import command.interfaces.IControl;

public interface IRemoteRobot extends Remote {
	
	void executeCommand(IControl control);

}
