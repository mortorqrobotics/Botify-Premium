package org.team1515.botifypremium.Utils;

import edu.wpi.first.wpilibj.AnalogInput;

import org.team1515.botifypremium.RobotMap;

public class StringPot {

    private final int inConversion = 144;

    AnalogInput stringPot;

    public StringPot(){
        stringPot = new AnalogInput(RobotMap.STRING_ID);
    }

    /**
     * @return int value of distance in inches
     */
    public int getDist(){
        return stringPot.getValue() / inConversion;
    }
    
}
