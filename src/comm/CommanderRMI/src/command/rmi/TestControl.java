package command.rmi;

import java.io.IOException;
import java.rmi.RemoteException;

import command.interfaces.IControl;

public class TestControl /*extends java.rmi.server.UnicastRemoteObject */implements IControl {

	private static final long serialVersionUID = 2826770462397937429L;

	protected TestControl() throws RemoteException {
		super();
	}

	@Override
	public void move(int speed, boolean reverse) throws IOException, RemoteException {
		System.out.println("Speed: " + speed + " - Reverse: " + reverse);
	}

	@Override
	public void move(boolean reverse) throws IOException, RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void left(int turnSpeed) throws IOException, RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void left() throws IOException, RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void right(int turnSpeed) throws IOException, RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void right() throws IOException, RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() throws IOException, RemoteException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int getBatteryLevel() throws IOException {
		return 42;
	}

	@Override
	public int getDistanceToNearestObject() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void openClaw(int speed) throws IOException, RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void openClaw() throws IOException, RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeClaw(int speed) throws IOException, RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeClaw() throws IOException, RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopClaw() throws IOException, RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isClawMoving() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasCake() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reverse(int speed, int duration) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disconnect() throws IOException, RemoteException {
		// TODO Auto-generated method stub
		
	}
}