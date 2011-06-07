package command;

import lejos.pc.comm.NXTCommException;
import bluetooth.constants.Constants;

import command.exception.MasterRobotNotFound;
import command.exception.NoRobotFoundException;
import command.interfaces.IControl;

/** 
 * @author Morten Hulvej
 */
public class Commando {

	BertaCommando bertaCom = null;
	PropCommando propCom = null;
	
	/**Opretter forbindelse til B.E.R.T.A. og P.R.O.P. -  i denne r�kkef�lge
	 * <br> 
	 * Systemet kan ikke k�re uden B.E.R.T.A...
	 * @throws MasterRobotNotFound - hvis B.E.R.T.A. ikke kan findes
	 */
	public Commando() throws MasterRobotNotFound  {
		
			try {
				bertaCom = new BertaCommando();
			} catch (NXTCommException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoRobotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (!isBertaConnected())
					throw new MasterRobotNotFound(
							"Cannot run without master-robot: "+Constants.NXT_NAME+" not found!");
			}
			try {
				propCom = new PropCommando();
			} catch (NXTCommException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoRobotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	/**
	 * @return - et array af typen {@link IControl}
	 */
	public IControl[] getControls(){
		
		IControl[] controls = new IControl[2];
		
		if (isBertaConnected())
			controls[0] = bertaCom.getControl();
		else controls[0] = null;
		
		if (isPropConnected())
			controls[1] = propCom.getControl();
		else controls[1] = null;
		
		return controls;
	}
	
	public boolean isBertaConnected(){
		return bertaCom != null? true : false;
	}
	public boolean isPropConnected(){
		return propCom != null? true : false;
	}
	public int disconnect(){
		int result = 0;
		result = bertaCom.disconnect();
		result += propCom.disconnect();
		return result;
	}
	
}
