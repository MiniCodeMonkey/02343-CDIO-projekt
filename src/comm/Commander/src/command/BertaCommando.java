package command;

import java.io.IOException;

import command.impl.Control;
import command.interfaces.IControl;

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
	 * Opretter forbindelse til BERTA (virker pt. kun med NXT: 001653091CAE)
	 * <br>
	 * @throws NXTCommException hvis ingen BERTA fundet
	 */
	public BertaCommando() throws NXTCommException {
		NXTInfo bertaNXTInfo = null;
		System.out.println("Bluetooth test | PC -> NXT");

		nxtCommand = new NXTCommand();
		NXTConnector conn = new NXTConnector();

		// SEARCH /////////////////////////////////////////

		NXTInfo[] info = conn.search("", null, NXTCommFactory.BLUETOOTH);

		if (info.length == 0) {
			System.out.println();
			System.out.println("...No NXT(s) found!");
			System.exit(1);
		} else {

			System.out.println("Found " + info.length + " devices");
			int d = 1;
			for (NXTInfo nxtInfo : info) {
				System.out.println("   " + d + ". " + nxtInfo.name + " "
						+ nxtInfo.deviceAddress);
				d++;
				if (/*nxtInfo.deviceAddress.equals(Constants.NXT_ADR)
						|| */nxtInfo.deviceAddress.equals(Constants.NXT_ADR2)) {
					bertaNXTInfo = nxtInfo;
					System.out.println("NXT chosen");
				}

			}
		}

		// CONNECT ////////////////////////////////////////

		NXTComm nxtComm = null;
		try {
			nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
		} catch (NXTCommException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		if (bertaNXTInfo != null) {
			System.out.println();
			System.out.println("Connecting to B.E.R.T.A...");
				System.out
						.println("Success?  "
								+ nxtComm.open(bertaNXTInfo,
										lejos.pc.comm.NXTComm.LCP));
		}

		else
			System.err.println("B.E.R.T.A. not found!");

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

}
