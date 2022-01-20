package org.team1515.botifypremium.Subsystems;

import org.team1515.botifypremium.RobotMap;

import edu.wpi.first.wpilibj.Servo;

public class Latcher {

    private static Servo latcher;
    
    private final double closePos = 90.0; //in degrees
    private final double startPos = 0.0;

    public Latcher(){

        latcher = new Servo(RobotMap.LATCHER_ID);
        latcher.set(startPos);

    }

    public void latch() {
        latcher.setAngle(closePos);
    }

    public void unlatch() {
        latcher.setAngle(startPos);
    }

}
