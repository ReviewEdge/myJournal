package myJournal.DataStructures;

public class IllegalAccessAttempt extends IllegalArgumentException{

	public IllegalAccessAttempt(String msg) {
		super("Permissions Invalid: " + msg);
	}
	
	public IllegalAccessAttempt() {
		super();
	}
}
