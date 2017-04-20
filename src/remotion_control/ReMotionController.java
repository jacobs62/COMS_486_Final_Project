package remotion_control;
/**
 * Class that will generate GUI and manage IRSignalSender and MotionListener
 * 
 * @author Jacob Spoelstra, David Lauria 2017
 *
 */
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class ReMotionController {

	private JFrame frame;

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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(129, 70, 174, 31);
		frame.getContentPane().add(comboBox);
		
		JLabel lblChooseRemote = new JLabel("Choose Remote");
		lblChooseRemote.setBounds(26, 78, 97, 14);
		frame.getContentPane().add(lblChooseRemote);
	}
}
