package by.home.command.exception;

public class CommandException extends Exception{
	private static final long serialVersionUID = 1L;

	public CommandException() {
	}

	public CommandException(String message) {
		super(message);
	}
	
	public CommandException(Exception e){
		
	}
}
