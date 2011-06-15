package comm;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteRobot extends Remote {
	
	void recieveString(String x) throws RemoteException;

}
