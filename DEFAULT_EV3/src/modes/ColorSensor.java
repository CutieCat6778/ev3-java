package modes;

import lejos.hardware.Button;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;
import main.Skeleton;
import main.Skeleton.EV3ColorSensorWrapper;

public class ColorSensor {
		
    /*
     * 
     * Configuration array to turn on any functions
     * 
     * Indexes:
     *  0 is toch sensor
     *  1 is gyro sensor
     *  2 is color sensor
     *  3 is distance sensor
     * 
     */
	
	final public static boolean[] config = new boolean[]{false, false, true, false};
	
	public static void main(String[] args) {
		Skeleton robot = new Skeleton(config);
		
		Button.waitForAnyPress();
		
		double colorThreshold = 0.35;
		boolean state = true;
		MovePilot pilot = robot.getPilot();
		
		EV3ColorSensorWrapper colorSensor = robot.getColorSensor();
		
		while(state) {
			if(colorSensor.getReflectedRGB()[1] < colorThreshold) {
				pilot.rotate(90);
				goForward(pilot);
			} else if(colorSensor.getReflectedRed() < colorThreshold) {
				pilot.rotate(-90);
				goForward(pilot);

			}
		}
	}
	
	public static void goForward(MovePilot pilot) {
		pilot.forward();
		Delay.msDelay(1000);
		pilot.stop();
	}
}
