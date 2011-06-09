package command;

import javax.swing.JOptionPane;

import com.sun.org.apache.bcel.internal.classfile.Code;

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
	
	/**Opretter forbindelse til B.E.R.T.A. og P.R.O.P. -  i denne rækkefølge
	 * <br> 
	 * Systemet kan ikke køre uden B.E.R.T.A...
	 * @throws MasterRobotNotFound - hvis B.E.R.T.A. ikke kan findes
	 */
	public Commando() throws MasterRobotNotFound  {
		
			initBothRobots();
	}
	
	/**Opretter forbindelse til én robot
	 * 
	 * @param robot - {@code int}, 0 for BERTA, 1 for PROP
	 */
	public Commando(int robot){
		switch (robot) {
		case 0:
			try {
				initBerta();
			} catch (MasterRobotNotFound e) {
				e.printStackTrace();
			}
			break;
		case 1:
			initProp();
			break;
		default:
			break;
		}
	}

	private void initBothRobots() throws MasterRobotNotFound {
		initBerta();
		initProp();
	}

	private void initBerta() throws MasterRobotNotFound {
		try {
			bertaCom = new BertaCommando();
		} catch (NXTCommException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bertaCom = null;
		} catch (NoRobotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bertaCom = null;
		} finally {
			if (!isBertaConnected())
				throw new MasterRobotNotFound(
						"Cannot run without master-robot: "+Constants.NXT_NAME+" not found!");
		}
	}

	private void initProp() {
		try {
			propCom = new PropCommando();
		} catch (NXTCommException e) {
			JOptionPane.showMessageDialog(null, "Prop not found! (NXTCommExc)");
			propCom = null;
		} catch (NoRobotFoundException e) {
			JOptionPane.showMessageDialog(null, "Prop not found!");
			propCom = null;
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
//		result += propCom.disconnect();
		return result;
	}
	
}
