package communication.interfaces;
import java.rmi.*;
public interface ReceiveMessageInterface extends Remote
{
	String receiveMessage(String x) throws RemoteException;
	
	public void shutdown() throws RemoteException;
}