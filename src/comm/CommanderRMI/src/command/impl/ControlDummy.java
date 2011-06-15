package command.impl;

import java.io.IOException;
import java.rmi.RemoteException;

import command.interfaces.IControl;

public class ControlDummy extends java.rmi.server.UnicastRemoteObject implements IControl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4090592166577019471L;

	public ControlDummy() throws RemoteException {
		super();
	}

	@Override
	public void move(int speed, boolean reverse) throws IOException,
			RemoteException {
		System.out.println("move");
	}

	@Override
	public void move(boolean reverse) throws IOException, RemoteException {
		System.out.println("move");
	}

	@Override
	public void left(int turnSpeed) throws IOException, RemoteException {
		System.out.println("left");
	}

	@Override
	public void left() throws IOException, RemoteException {
		System.out.println("left");
	}

	@Override
	public void right(int turnSpeed) throws IOException, RemoteException {
		System.out.println("right");
	}

	@Override
	public void right() throws IOException, RemoteException {
		System.out.println("right");
	}

	@Override
	public void stop() throws IOException, RemoteException {
		System.out.println("stop");
	}

	@Override
	public int getBatteryLevel() throws IOException, RemoteException {
		return 0;
	}

	@Override
	public int getDistanceToNearestObject() throws RemoteException {
		return 0;
	}

	@Override
	public void openClaw(int speed) throws IOException, RemoteException {
	}

	@Override
	public void openClaw() throws IOException, RemoteException {
	}

	@Override
	public void closeClaw(int speed) throws IOException, RemoteException {
	}

	@Override
	public void closeClaw() throws IOException, RemoteException {
	}

	@Override
	public void stopClaw() throws IOException, RemoteException {
	}

	@Override
	public boolean isClawMoving() throws RemoteException {
		return false;
	}

	@Override
	public boolean hasCake() throws RemoteException {
		return false;
	}

	@Override
	public void reverse(int speed, int duration) throws RemoteException {
	}

	@Override
	public void disconnect() throws IOException, RemoteException {
	}
}