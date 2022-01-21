package org.team1515.botifypremium.Utils;

public class Utilities {

    public static double deadband = 0.05;

    /**
     * @param value Controller input value
     * @return double Joystick value accounted for controller error
     */
    public static double deadband(double value) {
        if (Math.abs(value) > deadband) {
            if (value > 0.0) {
                return (value - deadband) / (1.0 - deadband);
            } else {
                return (value + deadband) / (1.0 - deadband);
            }
        } else {
            return 0.0;
        }
    }
}
