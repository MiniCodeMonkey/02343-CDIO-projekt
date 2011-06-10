package comm;

/**
 * @author Terkel Brix og Jeppe Kronborg
 *
 */

import java.io.IOException;
import lejos.nxt.remote.NXTCommand;
import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommException;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTConnector;
import lejos.pc.comm.NXTInfo;

import command.BertaCommando;
import command.exception.NoRobotFoundException;
import command.impl.Control;
import command.interfaces.IControl;


public class RobotConnector {
	private static String BertaAdr = "001653091CAE";
	private static String PropAdr = "0016530918D4";
	private static String BertaName = "B.E.R.T.A.";
	private static String PropName = "P.R.O.P.";

	private static NXTCommand nxtCommand;

	private static String macAdr="";
	/**
	 * Generisk connector til begge robotter. Tager MAC-adressen som parameter
	 * 
	 * @param args
	 * @throws NoRobotFoundException 
	 * @throws NXTCommException 
	 */
	public static void main(String[] args, String macAddress) throws NoRobotFoundException, NXTCommException {
		//Sanitycheck of MAC-address
		if(!(macAddress.equals(BertaAdr)||macAddress.equals(PropAdr))){
			System.out.println("Ukendt MAC-Adresse. Terminerer");
			System.out.println("Kendte MAC-Adresser er: ");
			System.out.println(BertaName+": "+BertaAdr);
			System.out.println(PropName+": "+PropAdr);
			return;
		}
		macAdr = macAddress;
		
		//
		//Kopieret fra BertaCommando og tilpasset generisks til at kunne forbinde på mac-adresse
		//
		NXTInfo robotNXTInfo = null;

		nxtCommand = new NXTCommand();
		NXTConnector conn = new NXTConnector();

		//Start search
		System.out.println("Searching for robot with MAC-address: " + macAddress);
		System.out.println("Named: "+getRobotName(macAddress));
		NXTInfo[] info = conn.search(null, macAddress, NXTCommFactory.BLUETOOTH);

		if(info.length == 0)
		{
			System.out.println("No robot found with MAC-address: " + macAddress);
			throw new NoRobotFoundException(getRobotName(macAddress));
		}

		System.out.println(" OK");
		System.out.println("Found " + info.length + " robots");

		if (info[0].deviceAddress.equals(BertaAdr) || info[0].deviceAddress.equals(PropAdr)){
			robotNXTInfo = info[0];
		}
		if(robotNXTInfo == null){
			System.err.println("No robot found!");
			throw new NoRobotFoundException(getRobotName(macAddress));
		}

		// Start Connect
		NXTComm nxtComm = null;
		nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);

		System.out.println();
		System.out.println("Connected to "+getRobotName(macAddress));
		System.out.println("Success? " + nxtComm.open(robotNXTInfo, lejos.pc.comm.NXTComm.LCP));

		nxtCommand.setNXTComm(nxtComm);
	}

	/**
	 * Giver en instans af klassen {@link Control} der implementere {@link IControl}
	 * 
	 * <br><br>Bemærk at alle metoder returnerer med det samme!!
	 * 
	 * @return {@link IControl} - Et interface med primitive kommandoer til Robot
	 */
	public IControl getControl() {
		IControl c = new Control(nxtCommand);
		return c;
	}
	/**
	 * Lukker forbindelsen
	 * 
	 * @return int - 0 hvis alt er vel; 1 hvis ikke (like you care anyway!! )
	 */
	public int disconnect() {
		int result = -1;
		System.out.println();
		System.out.println("Closing BT-connection to "+getRobotName(macAdr));
		try {
			nxtCommand.close();
			result = 0;
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			result = 1;
		}
		return result;

	}
	
	public boolean isConnected() {
		return nxtCommand.isOpen();
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