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

				
				if (circle.pointable().direction().angleTo(circle.normal()) <= Math.PI / 2) {
					//volume up cause clockwise
				} else {
					//volume down cause counter clockwise
				}

				break;
			case TYPE_SWIPE:
				SwipeGesture swipe = new SwipeGesture(gesture);
					if(swipe.direction().getX() > 0){
						// channel up
					}
					else{
						//channel down
					}
					
				break;
			case TYPE_SCREEN_TAP:
				//power?
				break;
			case TYPE_KEY_TAP:
				//power?
				break;
			default:
				System.out.println("Unknown gesture type.");
				break;
			}
		}

	}

}
