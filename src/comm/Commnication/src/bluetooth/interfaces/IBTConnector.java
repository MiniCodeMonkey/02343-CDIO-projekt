package bluetooth.interfaces;

import java.io.IOException;

import bluetooth.constants.Constants;


import lejos.nxt.remote.NXTCommand;
import lejos.pc.comm.NXTCommException;

public interface IBTConnector {

	public final String NXT_NAME = Constants.NXT_NAME;
	final String NXT_ADR = Constants.NXT_ADR;
	final String NXT_ADR2 = Constants.NXT_ADR2;
	final int NO_BERTA_FOUND = 2;
	final int NO_NXT_FOUND = 1;
	final int BERTA_FOUND = 0;

	/**
	 * @return {@link NXTCommand} - command modul tilknyttet denne forbindelse
	 */
	public NXTCommand getNxtCommand();

	/**
	 * Søger efter NXT'er med de specifikke adresser, angivet i {@link Constants}
	 * 
	 * @param shouldBeep - bipper når forbindelse er oprettet (pt SLÅET FRA)
	 * @return int - hhv konstanterne BERTA_FOUND, NO_NXT_FOUND, NO_NXT_FOUND
	 * @throws NXTCommException
	 */
	public int searchAndConnect(boolean shouldBeep)
			throws NXTCommException;

	/**
	 * @throws IOException
	 */
	public void disconnect() throws IOException;

}