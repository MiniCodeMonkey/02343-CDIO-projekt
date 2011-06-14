package command.rmi;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.*;
import java.rmi.registry.*;

import command.interfaces.IControl;

public class RmiClient
{
	

	static public void main(String args[])
	{
		IControl propControl;
		Registry registry=null;
		String thisAddress = "localhost";
		int thisPort=3232;  // this port(registry’s port)
		Process p2 = null;
		
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
				//System.out.println("Starting Berta");
				//p1 = Runtime.getRuntime().exec("java -cp bin command.rmi.RmiServer "+bluetooth.constants.Constants.BertaAdr);
				
				System.out.println("Starting Prop!");
//				p2 = Runtime.getRuntime().exec("java -cp bin command.rmi.RmiServer "+bluetooth.constants.Constants.PropAdr);
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
			// look up the remote object
			//bertaControl =	(IControl)(registry.lookup("robot_"+bluetooth.constants.Constants.BertaAdr));
			propControl = (IControl)(registry.lookup("robot_"+bluetooth.constants.Constants.PropAdr));
			// call the remote method
			System.out.println("Start to move");
			propControl.move(true);
			Thread.sleep(2000);
			System.out.println("Stop.");
			propControl.stop();
			p2.destroy();
			System.out.println("p2 Killed!");
		}
		catch(RemoteException e){
			e.printStackTrace();
		}
		catch(NotBoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}