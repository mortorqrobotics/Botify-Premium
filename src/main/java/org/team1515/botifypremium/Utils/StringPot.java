package org.team1515.botifypremium.Utils;

import edu.wpi.first.wpilibj.AnalogInput;

public class StringPot {

    private final double cenConversion = 56.6929;

    AnalogInput stringPot;

    public StringPot(int stringID){
        stringPot = new AnalogInput(stringID);
    }

    /**
     * @return int value of distance in centimeters
     */
    public double getDist(){
        return stringPot.getValue() / cenConversion;
    }
    
}
