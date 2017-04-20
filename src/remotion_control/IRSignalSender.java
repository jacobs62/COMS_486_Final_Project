package remotion_control;
/**
 * Class that will handle connection to WinLIRC to send signals to IRToy
 * 
 * @author Jacob Spoelstra 2017
 *
 */
public class IRSignalSender {

	String remoteName;
	//TODO: Add socket variables
	
	public IRSignalSender(String remoteName, int portno){
		this.remoteName = remoteName;
		this.start(portno);
	}
	
	public void start(int portno){
		//TODO: Connect to server
	}
	
	public void stop(){
		//TODO: disconnect from server
	}
	
	public void send(String signal){
		//TODO: send signal
	}
}
