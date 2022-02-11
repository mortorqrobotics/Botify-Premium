package org.team1515.botifypremium.Subsystems;

import org.team1515.botifypremium.RobotMap;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Latcher extends SubsystemBase {

    private Servo latcher;
    
    public static final double closePos = 90.0; //in degrees
    public static final double startPos = 0.0;

    public Latcher() {
        latcher = new Servo(RobotMap.LATCHER_ID);
        latcher.setAngle(startPos);
    }

    public void latch() {
        latcher.setAngle(closePos);
    }

    public void unlatch() {
        latcher.setAngle(startPos);
    }

    public double getAngle() {
        return latcher.getAngle();
    }
}
