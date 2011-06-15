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

public class RmiServer {
	private String registryHost = "localhost";
	private int registryPort = 1337;
	private Registry registry = null;
	private String bindName = "remote";
	private int robot;
	private Commando commando;
	private IControl stub;
	
	public RmiServer(int robot) {
		this.robot = robot;
	}
	
	public void init() {
		this.bindName = "remote_" + robot;
		IControl control;
		try {
			commando = new Commando(robot);
			control = commando.getControls()[robot];
//			try {
//				control.closeClaw();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			stub = (IControl) UnicastRemoteObject.exportObject(control, 0);
			registry = LocateRegistry.getRegistry(registryHost, registryPort);
			registry.rebind(bindName, stub);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
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

	public static void main(String[] args) {

		int robot = 0;
		if (args.length >= 1) {
			try {
				int robotTemp = Integer.parseInt(args[0]);
				if (robotTemp >= 0 && robotTemp < 2) {
					robot = robotTemp;
				}
			} catch (NumberFormatException e) {
			}
		}
		try {
			System.setOut(new PrintStream(new FileOutputStream("RmiServer_"+robot+"_out.log")));
			System.setErr(new PrintStream(new FileOutputStream("RmiServer_"+robot+"_err.log")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		RmiServer s = new RmiServer(robot);
		s.init();
	}
}