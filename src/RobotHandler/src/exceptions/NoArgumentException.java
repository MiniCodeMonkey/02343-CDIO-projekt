package exceptions;

public class NoArgumentException extends Exception {

	public NoArgumentException(String errMessage) {
		System.out.println(errMessage+": UKENDT MAC-ADRESSE!");
		
		
	}
}
