package remotion_control;
/**
 * Class that will generate GUI and manage IRSignalSender and MotionListener
 * 
 * @author Jacob Spoelstra, David Lauria 2017
 *
 */
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.leapmotion.leap.Controller;

public class ReMotionController {

	private JFrame frame;
	
	private IRSignalSender sender;
	private MotionListener listener;
	private Controller controller;
	private String[] remoteOptions;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReMotionController window = new ReMotionController();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ReMotionController() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		setupRemoteOptions();
		
		JComboBox comboBox = new JComboBox(remoteOptions);
		comboBox.setBounds(129, 70, 174, 31);
		frame.getContentPane().add(comboBox);
		
		JLabel lblChooseRemote = new JLabel("Choose Remote");
		lblChooseRemote.setBounds(26, 78, 97, 14);
		frame.getContentPane().add(lblChooseRemote);
		
		JButton startButton = new JButton("Start");
		startButton.setBounds(306, 70, 100, 31);
		startButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				start(remoteOptions[comboBox.getSelectedIndex()]);
			}
		});
		frame.getContentPane().add(startButton);
	}
	
	private void setupRemoteOptions(){
		File f = new File("remote_config.txt");
		Scanner scanFile;
		try {
			scanFile = new Scanner(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		ArrayList<String> remotes = new ArrayList<>();
		while(scanFile.hasNextLine()){
			String line = scanFile.nextLine();
			while(!line.contains("begin remote") && scanFile.hasNextLine()){
				line = scanFile.nextLine();
			}
			while(!line.contains("name") && scanFile.hasNextLine()){
				line = scanFile.nextLine();
			}
			if(line.contains("name")){
				Scanner lineScan = new Scanner(line);
				lineScan.next();//discard "name" and whitespace
				String name = lineScan.next();
				remotes.add(name);
				lineScan.close();
			}
		}
		remoteOptions = remotes.toArray(new String[remotes.size()]);
		
		scanFile.close();
	}
	
	private void start(String remoteName){	
		try {
			sender = new IRSignalSender(remoteName, 8765);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		listener = new MotionListener(sender);
		Controller controller = new Controller();
		controller.addListener(listener);
		while(true);
	}
}
