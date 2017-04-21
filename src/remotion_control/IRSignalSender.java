package remotion_control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.print.attribute.standard.PrinterLocation;

/**
 * Class that will handle connection to WinLIRC to send signals to IRToy
 * 
 * @author Jacob Spoelstra, David Lauria 2017
 *
 */
public class IRSignalSender {
	
	private static final String POWER = "KEY_POWER";
	private static final String VOLUME_UP = "KEY_VOLUMEUP";
	private static final String VOLUME_DOWN = "KEY_VOLUMEDOWN";
	private static final String CHANNEL_UP = "KEY_CHANNELUP";
	private static final String CHANNEL_DOWN = "KEY_CHANNELDOWN";

	String remoteName;
	Socket s;
	PrintWriter output;
	
	public IRSignalSender(String remoteName, int portno) throws IOException{
		this.remoteName = remoteName;
		this.start(portno);
	}
	
	public void start(int portno) throws IOException{
		s = new Socket("127.0.0.1", portno);
		output = new PrintWriter(s.getOutputStream(), true);
	}
	
	public void stop() throws IOException{
		s.close();
	}
	
	public void powerOn(){
		send(POWER);
	}
	
	public void powerOff(){
		send(POWER);
	}
	
	public void channelUp(){
		send(CHANNEL_UP);
	}
	
	public void channelDown(){
		send(CHANNEL_DOWN);
	}
	
	public void volumeUp(){
		send(VOLUME_UP);
	}
	
	public void volumeDown(){
		send(VOLUME_DOWN);
	}
	
	private void send(String signal){
		System.out.println(signal);
		String toSend = "SEND_ONCE " + remoteName + " " + signal;
		output.println(toSend);
		
	}
}
