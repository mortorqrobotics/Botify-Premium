package org.team1515.botifypremium.Utils;

public class Utilities {

    /**
     * @param value input value
     * @return double value accounted for base error
     */
    public static double deadband(double value, double deadband) {
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
