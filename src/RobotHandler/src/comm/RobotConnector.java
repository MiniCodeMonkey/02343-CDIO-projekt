package comm;

import java.io.IOException;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RobotConnector {

	public RobotConnector()
	{
		
		
		
		
		
		
		
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
}
