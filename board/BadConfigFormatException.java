/*
 * Exception: BadConfigFormatException
 * Authors: Brandon Rodriguez, Hunter Lang
 * This exception class is thrown whenever we have a bad initialization of the config files.
 * See other classes for examples of throwing this exception
 */

package board;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.File;

@SuppressWarnings("serial") // To make the compiler happy

public class BadConfigFormatException extends Exception {
	String message;
	// Constructor, there is no default constructor, which requires that the exception has a message
	public BadConfigFormatException(String exceptionInfo) {
		message = exceptionInfo; // Set the message
		logException(); // Log the exception occurrence
	}

	@Override
	public String toString() {
		return message;
	}

	// logException method. Creates a file writer in append mode, and writes the exception message to it
	private void logException() {	
		PrintWriter out = null;

		try {
			out = new PrintWriter(new FileWriter(new File("log.txt"), true));
			out.println(this.message);
			out.close();
		
		} catch(Exception e) {
			System.out.println("Error opening log file.");
		}
		
	}
}
