package command.rmi;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;

import lejos.nxt.remote.NXTCommand;
import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommException;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTConnector;
import lejos.pc.comm.NXTInfo;

import command.impl.Control;
import command.impl.ControlDummy;
import command.interfaces.IControl;
import command.exception.*;

import bluetooth.constants.*;

public class RmiServer
{
	private String mac;
	private static final long serialVersionUID = 2995291509199644292L;
	int      registryPort = 3232;
	String   thisAddress;
	Registry registry;    // rmi registry for lookup the remote objects.
	private lejos.nxt.remote.NXTCommand nxtCommand;
	private NXTCommand NXTCommand;

	public RmiServer(String mac) throws RemoteException
	{
		this.mac=mac;
		System.out.println("Server Started!");

		// set the address of this host.
		String serverAddress= "localhost";	
		System.out.println("this address="+thisAddress+",port="+registryPort);

		try{
//			IControl control = (IControl) new Control(initializeRobot(mac));
			IControl control = (IControl) new ControlDummy();
			IControl stub = (IControl) UnicastRemoteObject.exportObject(control);
			// get the “registry” 
			registry=LocateRegistry.getRegistry(serverAddress, registryPort);
			// Bind the server
			registry.rebind("robot_"+mac, stub);
		}
		catch(RemoteException e){
			e.printStackTrace();
//		} catch (NoRobotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NoArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NXTCommException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
	}
	
	public NXTCommand initializeRobot(String macAdr) throws NoRobotFoundException, NoArgumentException, NXTCommException {
		
		//Kopieret fra BertaCommando og tilpasset generisks til at kunne forbinde på mac-adresse
		NXTInfo robotNXTInfo = null;
		NXTCommand = new NXTCommand();
		NXTConnector conn = new NXTConnector();

		//Start search
		NXTInfo[] info = conn.search(null, macAdr, NXTCommFactory.BLUETOOTH);
		if(info.length == 0)
		{
			throw new NoRobotFoundException(info[0].name);
		}
		if( info[0].deviceAddress.equals(mac) ){
			robotNXTInfo = info[0];
		}
		if(robotNXTInfo == null){
			System.err.println("No robot found!");
			throw new NoRobotFoundException(getRobotName(macAdr));
		}
		// Start Connect
		NXTComm nxtComm = null;
		nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
		System.out.println("Connection success: " + nxtComm.open(robotNXTInfo, lejos.pc.comm.NXTComm.LCP));
		NXTCommand.setNXTComm(nxtComm);
		return NXTCommand;
		}
	
	private static String getRobotName(String macAddress)
	{
		if(macAddress.equals(bluetooth.constants.Constants.BertaAdr))	return bluetooth.constants.Constants.BertaName;
		else if (macAddress.equals(bluetooth.constants.Constants.PropAdr))return bluetooth.constants.Constants.PropName;
		return "";
	}

	static public void main(String args[])
	{
/*		PrintStream fileStream;
		try {
			fileStream = new PrintStream(new FileOutputStream("C:\\log.txt", true));
			System.setOut(fileStream);
			System.setErr(fileStream);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		try{
			RmiServer s=new RmiServer(args[0]);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}