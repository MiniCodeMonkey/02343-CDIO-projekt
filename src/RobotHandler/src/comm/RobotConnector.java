package comm;

import java.io.IOException;
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
	
	public void RobotClientSomething()
	{
		if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
		
		// create the registry and bind the name and object.
		try {
			registry = LocateRegistry.createRegistry(3232);
			
			remoteRobot = (IRemoteRobot)(registry.lookup("RobotConnector"));
			remoteRobot.recieveString(text);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
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
		
	
	
	public static void main(String[] args) throws NoRobotFoundException, NoArgumentException, NXTCommException {
		
		RobotConnector robo = new RobotConnector();
		
		robo.RobotClientSomething();
		
		RobotStub robostub = new RobotStub();
	}

	@Override
	public void recieveString(String x) {
		
		
	}
}
