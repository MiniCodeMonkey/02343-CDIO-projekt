package bluetooth.impl;

import java.io.IOException;

import lejos.nxt.remote.NXTCommand;
import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommException;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTConnector;
import lejos.pc.comm.NXTInfo;
import bluetooth.interfaces.IBTConnector;

/**
 * @author Morten Hulvej
 * 
 */
public class BTConnector implements IBTConnector {

	private NXTInfo bertaNXTInfo;
	private NXTConnector conn;
	private NXTCommand nxtCommand = null;

	static IBTConnector singleton = null;

	private BTConnector() {
		nxtCommand = new NXTCommand();
		conn = new NXTConnector();
	}

	public static IBTConnector getInstance() {
		if (singleton == null)
			return singleton = new BTConnector();
		else
			return singleton;
	}

	/* (non-Javadoc)
	 * @see connector.IBTConnector#getNxtCommand()
	 */
	@Override
	public NXTCommand getNxtCommand() {
		return nxtCommand;
	}

	@Override
	public int searchAndConnect(boolean shouldBeep) throws NXTCommException {
		NXTInfo[] info = conn.search("", NXT_ADR, NXTCommFactory.BLUETOOTH);

		if (info.length == 0) {
			System.out.println();
			System.out.println("...No NXT(s) found!");
			return NO_NXT_FOUND;
		} else {

			System.out.println("Found " + info.length + " devices");
			int d = 1;
			
			bertaNXTInfo = info[0];
			
//			for (NXTInfo nxtInfo : info) {
//				System.out.println("   " + d + ". " + nxtInfo.name + " "
//						+ nxtInfo.deviceAddress);
//				d++;
//				if (nxtInfo.deviceAddress.equals(NXT_ADR)
//						|| nxtInfo.deviceAddress.equals(NXT_ADR2)) {
//					bertaNXTInfo = nxtInfo;
//				}
//			}
		}
		return connect(shouldBeep);
	}

	@Override
	public void disconnect() throws IOException {
		System.out.println();
		System.out.println("Closing BT-connection..");
		nxtCommand.close();
	}

	private int connect(boolean shouldBeep) throws NXTCommException {
		NXTComm nxtComm = NXTCommFactory
				.createNXTComm(NXTCommFactory.BLUETOOTH);

		if (bertaNXTInfo != null) {
			System.out.println();
			System.out.println("Connecting to B.E.R.T.A..."+bertaNXTInfo.deviceAddress);
			System.out.println("Success?  "
					+ nxtComm.open(bertaNXTInfo, lejos.pc.comm.NXTComm.LCP));
		}

		else {
			System.err
					.println("B.E.R.T.A. not found - (but other NXTs were found)");
			return NO_BERTA_FOUND;
		}
		if (shouldBeep) {
			try {
				nxtCommand.playTone(2000, 200);
				nxtCommand.playTone(50, 200);
				nxtCommand.playTone(2000, 200);
			} catch (IOException e) {
			}
		}

		nxtCommand.setNXTComm(nxtComm);
		return BERTA_FOUND;
	}
}
