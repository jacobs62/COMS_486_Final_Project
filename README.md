# COMS_486_Final_Project
Remote control of television using LeapMotion gesture detection.

preconditions: need to have an IRtoy with firmware v22  and a leap motion device
SETUP:
  1. download and install WINLirc
  2. plug in IRtoy and leapmotion device into computer
  3. locate configuration file for the tv you want to control 
     files can be found for many devices at lirc.sourceforge.net/remotes/
     most tv manufacturers use the same codes for different models, 
      so even if you cannot locate the exact file to match your remote, 
      one with a similar id will likely still work
  4. copy any remotes you want to use into the remote_config.txt file, including everything between "begin remote" and "end remote"
     the lirc site attempts to standardize all codes, but it is not reinforced so occasionally there is a nonstandard code
     check to make sure the following codes are included and rename them if they are mislabeled:
        KEY_POWER			turns tv on and off
        KEY_VOLUMEUP		turns volume up
        KEY_VOLUMEDOWN		turns volume down
        KEY_CHANNELUP		turns channel up
        KEY_CHANNELDOWN		turns channel down
  5. run WINLirc server through the WINLirc application with the config files needed to control 1 or many TV's
     use the default port of 8765
  6. run our program 
  7. select device from drop down menu
  8. click start button.
  9. use gestures with leapmotion device to control the tv
     a. a screen tap (back and forth with index finger) turns the tv on and off
     b. a swipe (with finger or hand, though hand is less reliable) left or right changes channel down or up
     c. a circular motion (with finger) turns the volume up if clockwise, down if counterclockwise, adjusting by one volume unit per circuit

How to reuse:
	If you were to want to use this project with another technology besides the LeapMotion device, you would need to set up that device to be used with a java program, then you would use our ReMotionController and IRSignalSender.java classes to have the UI and methods to send signals back and forth. You would set up an instance of your device in the ReMotionController code, then in your device you would need to set up an instance and interact with the IRSignalSender class to send codes. This is done by simply calling our methods like IRSignalSender.powerOn(). This will only work if you have a TV remote selected in the UI though. You would need to edit remote_config.txt in order to add new remotes to the program. A big list can be found here: http://lirc.sourceforge.net/remotes/ . 
