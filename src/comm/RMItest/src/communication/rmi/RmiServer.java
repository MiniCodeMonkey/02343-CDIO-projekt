package communication.rmi;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;

import command.impl.Control;
import communication.interfaces.IControl;

public class RmiServer
{
	private String mac;
	private static final long serialVersionUID = 2995291509199644292L;
	int      registryPort = 3232;
	String   thisAddress;
	Registry registry;    // rmi registry for lookup the remote objects.

	public RmiServer(String mac) throws RemoteException
	{
		this.mac=mac;
		System.out.println("Server Started!");

		// set the address of this host.
		String serverAddress= "localhost";	
		System.out.println("this address="+thisAddress+",port="+registryPort);

		try{
			IControl control = (IControl) new Control();
			IControl stub = (IControl) UnicastRemoteObject.exportObject(control);
			// get the “registry” 
			registry=LocateRegistry.getRegistry(serverAddress, registryPort);
			// Bind the server
			registry.rebind("robot_"+mac, stub);
		}
		catch(RemoteException e){
			e.printStackTrace();
		}
	}

	static public void main(String args[])
	{

		try{
			RmiServer s=new RmiServer(args[0]);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}