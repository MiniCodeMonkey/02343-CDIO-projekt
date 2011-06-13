package comm;

import java.rmi.Remote;

import command.interfaces.IControl;

public interface IRemoteRobot extends Remote {
	
	void recieveString(String x);

}
