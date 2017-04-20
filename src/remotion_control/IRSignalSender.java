package remotion_control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Class that will handle connection to WinLIRC to send signals to IRToy
 * 
 * @author Jacob Spoelstra 2017
 *
 */
public class IRSignalSender {

	String remoteName;
	Socket s;
	PrintWriter output;
	//TODO: Add socket variables
	
	public IRSignalSender(String remoteName, int portno) throws IOException{
		
		output = new PrintWriter(s.getOutputStream(), true);
	
		this.remoteName = remoteName;
		this.start(portno);
	}
	
	public void start(int portno) throws IOException{
		s = new Socket("127.0.0.1", portno);
	}
	
	public void stop() throws IOException{
		s.close();
	}
	
	public void send(String signal){
		String toSend = "SEND_ONCE " + remoteName + " " + signal;
		output.println(toSend);
		
	}
}
