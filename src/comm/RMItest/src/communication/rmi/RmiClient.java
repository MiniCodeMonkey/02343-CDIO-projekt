package communication.rmi;
import java.io.IOException;
import java.rmi.*;
import java.rmi.registry.*;

import communication.interfaces.ReceiveMessageInterface;

public class RmiClient
{
	

	static public void main(String args[])
	{
		String recievedFromServer1 ="";
		String recievedFromServer2 ="";
		
		ReceiveMessageInterface rmiServer1, rmiServer2;
		Registry registry=null;
		String thisAddress = "localhost";
		int thisPort=3232;  // this port(registry’s port)
		String text1="HokusPokus";
		String text2="FilliHanKat";
		
		
		System.out.println("sending "+text1+" to "+thisAddress+":"+thisPort);
		System.out.println("this address="+thisAddress+",port="+thisPort);

		try{
			// create the local registry
			registry = LocateRegistry.createRegistry( thisPort );
		}
		catch(RemoteException e){
			e.printStackTrace();
		}

		try{
			try {
				System.out.println("Starting Server 1!");
				Process p1 = Runtime.getRuntime().exec("java -cp bin communication.rmi.RmiServer 1");
				
				System.out.println("Starting Server 2!");
				Process p2 = Runtime.getRuntime().exec("java -cp bin communication.rmi.RmiServer 2");
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// look up the remote object
			rmiServer1=
				(ReceiveMessageInterface)(registry.lookup("arg_1"));
			rmiServer2 = (ReceiveMessageInterface)(registry.lookup("arg_2"));
			// call the remote method
			recievedFromServer1  = rmiServer1.receiveMessage(text1);
			recievedFromServer2 = rmiServer2.receiveMessage(text2);
			System.out.println("Recieved from Server1: "+recievedFromServer1);
			System.out.println("Recieved from Server2: "+recievedFromServer2);
			try {
				rmiServer1.shutdown();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try{
				rmiServer2.shutdown();
			} catch(Exception e){
				e.printStackTrace();
			}
			
			System.out.println("Serverssss ShutDown!");
		}
		catch(RemoteException e){
			e.printStackTrace();
		}
		catch(NotBoundException e){
			e.printStackTrace();
		}
	}
}