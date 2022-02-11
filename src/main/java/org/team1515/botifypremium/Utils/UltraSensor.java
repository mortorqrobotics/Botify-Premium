package org.team1515.botifypremium.Utils;

import edu.wpi.first.wpilibj.AnalogInput;

import org.team1515.botifypremium.RobotMap;

public class UltraSensor {

    AnalogInput ultraSensor;

    private final double detectDist = 4.0;

    public UltraSensor(){
        ultraSensor = new AnalogInput(RobotMap.ULTRA_ID);
    }

    /**
     * @return boolean value if a cargo is detected
     */
    public boolean itemDetected(){
        if (ultraSensor.getValue() >= detectDist){
            return true;
        }
        return false;
    }
    
}

