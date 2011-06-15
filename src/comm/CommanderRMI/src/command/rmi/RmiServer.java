package command.rmi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import command.Commando;
import command.interfaces.IControl;

/**
 * @author Per Clausen, Terkel Brix & Jeppe Kronborg 
 *
 */
public class RmiServer {
	private String registryHost = "localhost";
	private int registryPort = 1337;
	private Registry registry = null;
	private String bindName = "remote";
	private int robot;
	private Commando commando;
	private IControl stub;
	
	/**
	 * Konstruktør der sætter nr. for en robot der afgør om det er B.E.R.T.A. eller P.R.O.P.
	 * @param robot Hvilket nr. robotten er
	 */
	public RmiServer(int robot) {
		this.robot = robot;
	}
	
	/**
	 * Initialiserer robotten
	 */
	public void init() {
		this.bindName = "remote_" + robot;
		IControl control;
		try {
			//Får controls til at styre en robot
			commando = new Commando(robot);
			control = commando.getControls()[robot];
//			try {
//				control.closeClaw();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			
			//Finder registry'et der ligger på klienten og binder sig til det
			stub = (IControl) UnicastRemoteObject.exportObject(control, 0);
			registry = LocateRegistry.getRegistry(registryHost, registryPort);
			registry.rebind(bindName, stub);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Lukker forbindelsen til en robot
	 */
	public void shutdown() {
		try {
			registry.unbind(bindName);
			UnicastRemoteObject.unexportObject(stub, true);
		} catch (AccessException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sørger for at hver robot får sin egen process
	 * @param args
	 */
	public static void main(String[] args) {

		int robot = 0;
		if (args.length >= 1) {
			try {
				//Tjekker at de rigtige parameter bliver givet med
				int robotTemp = Integer.parseInt(args[0]);
				if (robotTemp >= 0 && robotTemp < 2) {
					robot = robotTemp;
				}
			} catch (NumberFormatException e) {
			}
		}
		try {
			//Log til at se hvad robotten laver da den ikke har et konsol-vindue
			System.setOut(new PrintStream(new FileOutputStream("RmiServer_"+robot+"_out.log")));
			System.setErr(new PrintStream(new FileOutputStream("RmiServer_"+robot+"_err.log")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		RmiServer s = new RmiServer(robot);
		s.init();
		
	}
}