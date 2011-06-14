package command;

import java.io.IOException;

import command.impl.Control;
import command.impl.ControlSensor;
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
public class PropSensorCommando {
	
	private NXTCommand nxtCommand;

	/**
	 * Opretter forbindelse til PROP (MAC: 00:16:53:09:18:d4)
	 * <br>
	 * @throws NXTCommException
	 * @throws NoRobotFoundException
	 */
	public PropSensorCommando() throws NXTCommException, NoRobotFoundException {
		NXTInfo propNXTInfo = null;

		nxtCommand = new NXTCommand();
		NXTConnector conn = new NXTConnector();

		// SEARCH /////////////////////////////////////////
		
		System.out.print("Searching for NXTs...");
		NXTInfo[] info = conn.search(null, Constants.PropName, NXTCommFactory.BLUETOOTH);
		
		
		if (info.length == 0) {
			System.out.println("...no NXT(s) found!");
			throw new NoRobotFoundException(Constants.PropName);
		}

		System.out.println(" OK");
		System.out.println();
		System.out.println("Found " + info.length + " devices");
		int d = 1;
		for (NXTInfo nxtInfo : info) {
			System.out.println("   " + d + ". " + nxtInfo.name + " "
					+ nxtInfo.deviceAddress);
			d++;
			if (nxtInfo.deviceAddress.equals(Constants.PropName)) {
				propNXTInfo = nxtInfo;
				System.out.println("P.R.O.P. found");
			}

		}

		if (propNXTInfo == null){
			System.err.println("P.R.O.P. not found!");
			throw new NoRobotFoundException(Constants.PropName);
		}
		
		// CONNECT ////////////////////////////////////////

		NXTComm nxtComm = null;
		nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);

		System.out.println();
		System.out.println("Connected to " +propNXTInfo.name);
		System.out.println("Success?  "
				+ nxtComm.open(propNXTInfo,	lejos.pc.comm.NXTComm.LCP));

		nxtCommand.setNXTComm(nxtComm);
	}
	/**
	 * Giver en instans af klassen {@link Control} der implementere {@link IControl}
	 * 
	 * <br><br>Bemærk at alle metoder returnerer med det samme!!
	 * 
	 * @return {@link IControl} - Et interface med primitive kommandoer til BERTA
	 */
	public ControlSensor getControl() {
		ControlSensor c = new ControlSensor(nxtCommand);
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
