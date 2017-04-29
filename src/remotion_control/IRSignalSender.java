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
	/**
	 * String representation for code to power device on and off
	 */
	private static final String POWER = "KEY_POWER";
	/**
	 * String representation for code to turn volume up
	 */
	private static final String VOLUME_UP = "KEY_VOLUMEUP";
	/**
	 * String representation for code to turn volume down
	 */
	private static final String VOLUME_DOWN = "KEY_VOLUMEDOWN";
	/**
	 * String representation for code to turn channel up
	 */
	private static final String CHANNEL_UP = "KEY_CHANNELUP";
	/**
	 * String representation for code to turn channel down
	 */
	private static final String CHANNEL_DOWN = "KEY_CHANNELDOWN";
	/**
	 * String representation of the remote whose codes are being sent.  
	 * Remote is defined in remote_config.txt
	 */
	String remoteName;
	/**
	 * Socket for communicating with WINLirc server
	 */
	Socket s;
	/**
	 * PrintWriter for sending signals to server
	 */
	PrintWriter output;
	/**
	 * Constructor sets remoteName and starts connection to server
	 * @param remoteName
	 * @param portno
	 * @throws IOException
	 */
	public IRSignalSender(String remoteName, int portno) throws IOException{
		this.remoteName = remoteName;
		this.start(portno);
	}
	/**
	 * Opens socket at localhost:portno and gets output
	 * @param portno
	 * @throws IOException
	 */
	public void start(int portno) throws IOException{
		s = new Socket("127.0.0.1", portno);
		output = new PrintWriter(s.getOutputStream(), true);
	}
	/**
	 * Closes socket
	 * @throws IOException
	 */
	public void stop() throws IOException{
		s.close();
	}
	/**
	 * Sends signal to turn power on/off
	 */
	public void power(){
		send(POWER);
	}
	/**
	 * Sends signal to turn channel up
	 */
	public void channelUp(){
		send(CHANNEL_UP);
	}
	/**
	 * Sends signal to turn channel down
	 */
	public void channelDown(){
		send(CHANNEL_DOWN);
	}
	/**
	 * Sends signal to turn volume up
	 */
	public void volumeUp(){
		send(VOLUME_UP);
	}
	/**
	 * Sends signal to turn volume down
	 */
	public void volumeDown(){
		send(VOLUME_DOWN);
	}
	/**
	 * Sends signal to WINLirc server
	 * @param signal
	 */
	private void send(String signal){
		//Signal printed to console to see what signal is being sent
		System.out.println(signal);
		String toSend = "SEND_ONCE " + remoteName + " " + signal;
		output.println(toSend);
		
	}
}
