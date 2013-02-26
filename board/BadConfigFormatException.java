package board;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.File;


@SuppressWarnings("serial")
public class BadConfigFormatException extends Exception {
	String message;
	public BadConfigFormatException(String exceptionInfo) throws Exception {
		message = exceptionInfo;
		logException();
	}

	public String toString() {
		return message;
	}
	private void logException() throws Exception {
		PrintWriter out = new PrintWriter(new FileWriter(new File("log.txt"), true));
		out.println(this.message);
		out.close();
	}
}
