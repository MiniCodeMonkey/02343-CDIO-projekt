package comm;

/**
 * @author Terkel Brix og Jeppe Kronborg
 */

import java.io.IOException;
import lejos.nxt.remote.NXTCommand;
import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommException;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTConnector;
import lejos.pc.comm.NXTInfo;

import command.exception.NoRobotFoundException;
import command.impl.Control;
import command.interfaces.IControl;
import exceptions.NoArgumentException;


public class RobotConnector {
	private static String BertaAdr = "001653091CAE";
	private static String PropAdr = "0016530918D4";
	private static String BertaName = "B.E.R.T.A.";
	private static String PropName = "P.R.O.P.";
	private static NXTCommand nxtCommand;
	private static String macAdr = "";
	private static NXTCommand NXTCommand;
	
	/**
	 * Generisk connector til begge robotter. Tager MAC-adressen som parameter
	 * 
	 * @param args
	 * @throws NoRobotFoundException 
	 * @throws NXTCommException 
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws NoArgumentException 
	 */
	
	public static void main(String[] args) throws NoRobotFoundException, NXTCommException, IOException, InterruptedException, NoArgumentException {
		if(args.length<1)
		{
			macAdr = "No MAC-address recieved";
		}
		else
		{
			macAdr = args[0];
		}
		
		//Sanitycheck of MAC-address TODO: Lav en exception istedet
		if( !((macAdr.equals(BertaAdr) || (macAdr.equals(PropAdr)))))
		{
			throw new NoArgumentException(macAdr);
		}

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
		
		if( info[0].deviceAddress.equals(PropAdr) ){
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
		nxtCommand.setNXTComm(nxtComm);
		
		}

	/**
	 * Giver en instans af klassen {@link Control} der implementere {@link IControl}
	 * 
	 * <br><br>Bemærk at alle metoder returnerer med det samme!!
	 * 
	 * @return {@link IControl} - Et interface med primitive kommandoer til Robot
	 */
	public static IControl getControl() {
		IControl c = new Control(NXTCommand);
		return c;
	}
	/**
	 * Lukker forbindelsen
	 * 
	 * @return int - 0 hvis alt er vel; 1 hvis ikke (like you care anyway!! )
	 */
	public static int disconnect() {
		int result = -1;
		System.out.println();
		System.out.println("Closing BT-connection to "+getRobotName(macAdr));
		try {
			NXTCommand.close();
			result = 0;
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			result = 1;
		}
		return result;

	}
	
	public boolean isConnected() {
		return NXTCommand.isOpen();
	}

	/**
	 * Funktion til at finde Robot navn udfra MAC Adressen
	 * @param macAddress Strengen der er start parameter for klassen
	 * @return returner enten B.E.R.T.A eller P.R.O.P.
	 */
	private static String getRobotName(String macAddress)
	{
		if(macAddress.equals(BertaAdr))	return BertaName;
		else if (macAddress.equals(PropAdr))return PropName;
		return "";
	}
}