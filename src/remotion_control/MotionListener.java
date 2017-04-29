package remotion_control;

/**
 * Class that will connect to LeapMotion and listen for gestures
 * 
 * @author Jacob Spoelstra, David Lauria 2017
 *
 */
import com.leapmotion.leap.*;
import com.leapmotion.leap.Gesture.State;

public class MotionListener extends Listener {
	/**
	 * IRSignalSender to send IR messages
	 */
	IRSignalSender irDevice;
	/**
	 * Progress around a circle when using circle gesture
	 */
	private int circleProgress = 0;
	/**
	 * Constructor creates MotionListener and sets irDevice
	 * @param sender
	 */
	public MotionListener(IRSignalSender sender){
		super();
		irDevice = sender;
	}
	/**
	 * Enable gestures SWIPE, CIRCLE, SCREEN_TAP, and KEY_TAP on connection to LeapMotion Controller
	 */
	public void onConnect(Controller controller) {
		controller.enableGesture(Gesture.Type.TYPE_SWIPE);
		controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
		controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
		controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
	}
	/**
	 * On frame received from controller, check for action to be taken
	 */
	public void onFrame(Controller controller) {
		Frame frame = controller.frame();
	
		GestureList gestures = frame.gestures();
		for (int i = 0; i < gestures.count(); i++) {
			Gesture gesture = gestures.get(i);

			switch (gesture.type()) {
			case TYPE_CIRCLE:
				CircleGesture circle = new CircleGesture(gesture);

				boolean up = false;
				//If rotation is clockwise, set up to true
				if (circle.pointable().direction().angleTo(circle.normal()) <= Math.PI / 2) {
					up = true;
				}
				//If a full rotation has occurred, change volume
				if((int)circle.progress() > circleProgress){
					circleProgress = (int)circle.progress();
					if(up){
						irDevice.volumeUp();
					} else {
						irDevice.volumeDown();
					}
				}
				//Reset circleProgress when gesture ceases
				if(circle.state() == State.STATE_STOP){
					circleProgress = 0;
				}

				break;
			case TYPE_SWIPE:
				SwipeGesture swipe = new SwipeGesture(gesture);
				
				boolean channelUp = false;
				//If swipe to the right, set channelUp to true
				if(swipe.direction().getX() > 0){
					channelUp=true;
				}
				//If gesture ended, change channel
				if(swipe.state() == State.STATE_STOP){
					if(channelUp){
						irDevice.channelUp();
					} else {
						irDevice.channelDown();
					}
				}
					
				break;
			case TYPE_SCREEN_TAP:
				irDevice.power();
				break;
			case TYPE_KEY_TAP:
				
				break;
			default:
				System.out.println("Unknown gesture type.");
				break;
			}
		}

	}

}
