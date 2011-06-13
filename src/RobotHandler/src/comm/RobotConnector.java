package comm;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import command.interfaces.IControl;

public class RobotConnector  implements IRemoteRobot{
	
	Registry registry;
	
	public RobotConnector()
	{
		// create the registry and bind the name and object.
		try {
			registry = LocateRegistry.createRegistry(3232);
			registry.rebind("RobotConnector", this);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		
		
		
		
		
	}
	
	public void startRobotProcess(String macAddress)
	{
		
		try {
//			Runtime.getRuntime().exec("java C:\\Users\\jkronborg\\Documents\\CDIO\\02343\\src\\RobotHandler\\bin\\runtimeTest");
			System.out.println("Pre-runtime");
			Runtime.getRuntime().exec("java -cp C:\\Users\\jkronborg\\Documents\\CDIO\\02343\\src\\RobotHandler\\bin comm.runtimeTest");
			System.out.println("Post-runtime");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	
	
	public static void main(String[] args) {
		new RobotConnector().startRobotProcess("");
		
		while(true);
	}

	@Override
	public void executeCommand(IControl control) {
		// TODO Auto-generated method stub
		
	}
}
