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

//Exception class body
public class BadConfigFormatException extends Exception {

	String message; // Used to store the error code or message describing which exception occurred

	// Constructor, there is no default constructor, which requires that the exception has a message
	public BadConfigFormatException(String exceptionInfo) {
		message = exceptionInfo; // Set the message
		logException(); // Log the exception occurrence
	}

	// toString method. Allows for printing the exception directly without using another function
	@Override
	public String toString() {
		return message;
	}

	// logException method. Creates a file writer in append mode, and writes the exception message to it
	private void logException() {	
		// Set up the writer before the try catch block
		PrintWriter out = null;

		try {
			// Try to make the file writer
			out = new PrintWriter(new FileWriter(new File("log.txt"), true));
		
			 // If something goes wrong, catch the error, print a message, and don't log.
		} catch(Exception e) {
			System.out.println("Error opening log file.");
		}
		
		// Write out the message to the log
		out.println(this.message);
		
		// Close the log file
		out.close();
	}
}
