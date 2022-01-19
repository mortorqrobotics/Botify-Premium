package org.team1515.botifypremium.Utils;

public class Utilities {
    
    /** 
     * @param value Controller input value
     * @return double Joystick value accounted for controller error
     */
    public static double deadband(double value) {
        if(value < 0.05) {
            return 0.0;
        }

        return value;
    }
}
