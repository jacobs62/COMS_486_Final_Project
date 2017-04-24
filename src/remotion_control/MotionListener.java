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
	
	IRSignalSender irDevice;
	private int circleProgress = 0;
	
	public MotionListener(IRSignalSender sender){
		super();
		irDevice = sender;
	}

	public void onConnect(Controller controller) {
		controller.enableGesture(Gesture.Type.TYPE_SWIPE);
		controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
		controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
		controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
	}

	public void onFrame(Controller controller) {
		Frame frame = controller.frame();
	
		GestureList gestures = frame.gestures();
		for (int i = 0; i < gestures.count(); i++) {
			Gesture gesture = gestures.get(i);

			switch (gesture.type()) {
			case TYPE_CIRCLE:
				CircleGesture circle = new CircleGesture(gesture);

				boolean up = false;
				
				if (circle.pointable().direction().angleTo(circle.normal()) <= Math.PI / 2) {
					up = true;
				}
				
				if((int)circle.progress() > circleProgress){
					circleProgress = (int)circle.progress();
					if(up){
						irDevice.volumeUp();
					} else {
						irDevice.volumeDown();
					}
				}
				
				if(circle.state() == State.STATE_STOP){
					circleProgress = 0;
				}

				break;
			case TYPE_SWIPE:
				SwipeGesture swipe = new SwipeGesture(gesture);
				
				boolean channelUp = false;
				
					if(swipe.direction().getX() > 0){
						channelUp=true;
					}
					
					if(swipe.state() == State.STATE_STOP){
						if(channelUp){
							irDevice.channelUp();
						} else {
							irDevice.channelDown();
						}
					}
					
				break;
			case TYPE_SCREEN_TAP:
				irDevice.powerOn();
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
