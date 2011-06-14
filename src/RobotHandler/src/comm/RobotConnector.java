package comm;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import lejos.pc.comm.NXTCommException;

import command.exception.NoRobotFoundException;
import command.interfaces.IControl;
import exceptions.NoArgumentException;

public class RobotConnector  implements IRemoteRobot{
	
	IRemoteRobot remoteRobot;
	Registry registry;
	String berta = "00";
	String prop = "00";
    String text = "Tis eller dø søde mormor";
	
	public static void main(String[] args) throws NoRobotFoundException, NoArgumentException, NXTCommException, InterruptedException {
		
		RobotConnector robo = new RobotConnector();
		try {
			System.out.println("IP: "+(InetAddress.getLocalHost()).toString());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		robo.RobotClientSomething();
		Thread.sleep(20000);
		
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
		
	
	
	
	
public void RobotClientSomething()
	{
		if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
		
		// create the registry and bind the name and object.
		try {
			System.out.println("Creating Registry");
			registry = LocateRegistry.createRegistry(3232);
			System.out.println("Registry Created");
			//remoteRobot = (IRemoteRobot)(registry.lookup("RobotConnector"));
			//remoteRobot.recieveString(text);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //catch (NotBoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	@Override
	public void recieveString(String x) {
		
		
	}
}
