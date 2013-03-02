package board;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.File;



@SuppressWarnings("serial")
public class BadConfigFormatException extends Exception {
	String message;
	public BadConfigFormatException(String exceptionInfo) {
		message = exceptionInfo;
		logException();
	}

	public String toString() {
		return message;
	}
	private void logException() {
		PrintWriter out = null;
		try {
		out = new PrintWriter(new FileWriter(new File("log.txt"), true));
		} catch(Exception e) {
			System.out.println("Error opening log file.");
		}
		out.println(this.message);
		out.close();
	}
}
