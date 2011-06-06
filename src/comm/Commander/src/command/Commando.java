package command;

import exceptions.RobotConnectException;
import lejos.pc.comm.NXTCommException;

/** Opretter forbindelse til B.E.R.T.A. og P.R.O.P. -  i denne rækkefølge
 * <br>
 * @author Morten Hulvej
 */
public class Commando {

	BertaCommando bertaCom;
	PropCommando propCom;
	
	public Commando() throws RobotConnectException {
		try {
			bertaCom = new BertaCommando();
			propCom = new PropCommando();
		} catch (NXTCommException e) {
			e.printStackTrace();
			throw new RobotConnectException();
		}
		
	}
	
}
