package command;

import java.io.IOException;

import command.impl.Control;
import command.interfaces.IControl;
import command.exception.NoRobotFoundException;

import lejos.nxt.remote.NXTCommand;
import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommException;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTConnector;
import lejos.pc.comm.NXTInfo;
import bluetooth.constants.Constants;

/**
 * @author Morten Hulvej ( og Terkel)
 *
 */
public class BertaCommando {
	
	private NXTCommand nxtCommand;

	/**
	 * Opretter forbindelse til BERTA (MAC: 00:16:53:09:1C:AE)
	 * <br>
	 * @throws NXTCommException
	 * @throws NoRobotFoundException 
	 */
	public BertaCommando() throws NXTCommException, NoRobotFoundException {
		NXTInfo bertaNXTInfo = null;

		nxtCommand = new NXTCommand();
		NXTConnector conn = new NXTConnector();

		// SEARCH /////////////////////////////////////////

		System.out.print("Searching for NXTs...");
		NXTInfo[] info = conn.search(null, Constants.NXT_ADR, NXTCommFactory.BLUETOOTH);
		
		
		if (info.length == 0) {
			System.out.println("...no NXT(s) found!");
			throw new NoRobotFoundException(Constants.NXT_NAME);
		}

		System.out.println(" OK");
		System.out.println();
		System.out.println("Found " + info.length + " devices");
		int d = 1;
		for (NXTInfo nxtInfo : info) {
			System.out.println("   " + d + ". " + nxtInfo.name + " "
					+ nxtInfo.deviceAddress);
			d++;
			if (nxtInfo.deviceAddress.equals(Constants.NXT_ADR)) {
				bertaNXTInfo = nxtInfo;
				System.out.println("B.E.R.T.A. found");
			}
		}
		if (bertaNXTInfo == null){
			System.err.println("B.E.R.T.A. not found!");
			throw new NoRobotFoundException(Constants.NXT_NAME);
		}

		// CONNECT ////////////////////////////////////////

		NXTComm nxtComm = null;
		nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);

		System.out.println();
		System.out.println("Connected to " + bertaNXTInfo.name);
		System.out.println("Success?  "
				+ nxtComm.open(bertaNXTInfo, lejos.pc.comm.NXTComm.LCP));

		nxtCommand.setNXTComm(nxtComm);
	}
	/**
	 * Giver en instans af klassen {@link Control} der implementere {@link IControl}
	 * 
	 * <br><br>Bemærk at alle metoder returnerer med det samme!!
	 * 
	 * @return {@link IControl} - Et interface med primitive kommandoer til BERTA
	 */
	public IControl getControl() {
		IControl c = new Control(nxtCommand);
		return c;
	}
	/**
	 * Lukker forbindelsen til BERTA
	 * 
	 * @return int - 0 hvis alt er vel; 1 hvis ikke (like you care anyway!! )
	 */
	public int disconnect() {
		int result = -1;
		System.out.println();
		System.out.println("Closing BT-connection..");
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

}
