package command;

import bluetooth.constants.Constants;
import command.impl.Control;
import command.interfaces.IControl;

import exceptions.MasterRobotNotFound;
import exceptions.NoRobotFoundException;
import exceptions.RobotConnectException;
import lejos.pc.comm.NXTCommException;

/** 
 * @author Morten Hulvej
 */
public class Commando {

	BertaCommando bertaCom = null;
	PropCommando propCom = null;
	
	/**Opretter forbindelse til B.E.R.T.A. og P.R.O.P. -  i denne rækkefølge
	 * <br> 
	 * Systemet kan ikke køre uden B.E.R.T.A...
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
	
}
