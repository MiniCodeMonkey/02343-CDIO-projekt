package command.rmi;

import java.io.IOException;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import command.interfaces.IControl;

public class RmiClient {
	private static final int MAX_TRIES = 100;
	private boolean bertaEnabled, propEnabled;
	private IControl[] control;
	private int registryPort = 1337;
	private Registry registry;
	private Process processes[];
	
	public RmiClient() {
		bertaEnabled = true;
		propEnabled = true;
	}
	
	public RmiClient(int robot) {
		bertaEnabled = false;
		propEnabled = false;
		if (robot == 0) {
			bertaEnabled = true;
		} else if (robot == 1) {
			propEnabled = true;
		}
	}
	
	public void init() {
		control = new IControl[2];
		processes = new Process[2];
		try {
			registry = LocateRegistry.createRegistry(registryPort);
		} catch (RemoteException e) {
			System.err.println("Fejl ved oprettelse af RMI registry");
			e.printStackTrace();
			System.exit(1);
		}
		if (bertaEnabled) {
			try {
				System.out.println("Starting process");
				processes[0] = Runtime.getRuntime().exec("java -cp bin;bluecove.jar;pccomm.jar command.rmi.RmiServer 0");
				boolean successful = false;
				for (int i = 0; i < MAX_TRIES; i++) {
					try {
						System.out.println("Forsøger at forbinde ... Forsøg nr. " + (i+1));
						control[0] = (IControl)(registry.lookup("remote_0"));
						successful = true;
						break;
					} catch (NotBoundException e) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e1) {
						}
					}
				}
				if (!successful) {
					throw new NotBoundException();
				}
			} catch (AccessException e) {
				System.err.println("Fejl ved tilgang til BERTA");
				e.printStackTrace();
			} catch (RemoteException e) {
				System.err.println("Fejl ved forbindelse til BERTA");
				e.printStackTrace();
			} catch (NotBoundException e) {
				System.err.println("BERTA kunne ikke findes i RMI registret");
				e.printStackTrace();
			} catch (IOException e) {
				System.err.println("Kunne ikke starte BERTA-styringsprogram");
				e.printStackTrace();
			}
		}
		if (propEnabled) {
			try {
				System.out.println("Starting process");
				processes[1] = Runtime.getRuntime().exec("java -cp bin;bluecove.jar;pccomm.jar command.rmi.RmiServer 1");
				boolean successful = false;
				for (int i = 0; i < MAX_TRIES; i++) {
					try {
						System.out.println("Forsøger at forbinde ... Forsøg nr. " + (i+1));
						control[1] = (IControl)(registry.lookup("remote_1"));
						successful = true;
						break;
					} catch (NotBoundException e) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e1) {
						}
					}
				}
				if (!successful) {
					throw new NotBoundException();
				}
			} catch (AccessException e) {
				System.err.println("Fejl ved tilgang til PROP");
				e.printStackTrace();
			} catch (RemoteException e) {
				System.err.println("Fejl ved forbindelse til PROP");
				e.printStackTrace();
			} catch (NotBoundException e) {
				System.err.println("PROP kunne ikke findes i RMI registret");
				e.printStackTrace();
			} catch (IOException e) {
				System.err.println("Kunne ikke starte PROP-styringsprogram");
				e.printStackTrace();
			}
		}
	}
	
	public void shutdown() {
		if (bertaEnabled) {
			try {
				control[0].stop();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				control[0].stopClaw();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				registry.unbind("remote_0");
			} catch (AccessException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (NotBoundException e) {
				e.printStackTrace();
			}
			processes[0].destroy();
		}
		if (propEnabled) {
			try {
				control[1].stop();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				control[1].stopClaw();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				registry.unbind("remote_1");
			} catch (AccessException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (NotBoundException e) {
				e.printStackTrace();
			}
			processes[1].destroy();
		}
	}
	
	public IControl[] getControl() {
		return control;
	}

	public static void main(String[] args) {
		RmiClient c = new RmiClient();
		c.init();
		IControl[] control = c.getControl();
		
		try {
			System.out.println("Battery level 0: " + control[0].getBatteryLevel());
			System.out.println("Battery level 1: " + control[1].getBatteryLevel());
			for (int i = 0; i < 20; i++) {
				control[0].move(100,false);
				control[1].stop();
				control[0].closeClaw();
				control[1].openClaw();
				try {
					Thread.sleep(800);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				control[0].stop();
				control[1].move(100,false);
				control[0].openClaw();
				control[1].closeClaw();
				try {
					Thread.sleep(800);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			control[0].stop();
			control[1].stop();
			control[0].stopClaw();
			control[1].stopClaw();
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		c.shutdown();
	}
}