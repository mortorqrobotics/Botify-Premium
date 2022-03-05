package org.team1515.botifypremium;

import edu.wpi.first.wpilibj2.command.button.Button;

public class Controls {
    public static final Button SHOOT = new Button(OI.secondStick::getXButton);
    // public static final Button CLIMB = new Button(Controls::getRightTrigger);
    // public static final Button RETRACT = new Button(Controls::getLeftTrigger);
    public static final Button OUTAKE = new Button(OI.secondStick::getLeftBumper);
    public static final Button INTAKE = new Button(OI.secondStick::getRightBumper);
    public static final Button MAGUP = new Button(OI.secondStick::getAButton);
    public static final Button MAGDOWN = new Button(OI.secondStick::getBButton);
    public static final Button RESETGYRO = new Button(OI.mainStick::getBackButton);

    public static final Button CLIMBR = new Button(OI.mainStick::getRightBumper);
    public static final Button CLIMBL = new Button(OI.mainStick::getLeftBumper);
    public static final Button ROBOT_ALIGN = new Button(OI.mainStick::getStartButton);
    public static final Button EXPANDV = new Button(OI.mainStick::getRightBumper);
    public static final Button RETRACTV = new Button(Controls::getRightTrigger);
    public static final Button EXPANDD = new Button(OI.mainStick::getLeftBumper);
    public static final Button RETRACTD = new Button(Controls::getLeftTrigger);
    public static final Button DRIVE_DIST = new Button(OI.mainStick::getAButton);
    public static final Button ALIGN_TO_POINT = new Button(OI.mainStick::getBButton);
    public static final Button GET_ANGLE = new Button(OI.mainStick::getXButton);

    public static boolean getRightTrigger() {
        return OI.mainStick.getRightTriggerAxis() >= 0.50;
    }

    public static boolean getLeftTrigger() {
        return OI.mainStick.getLeftTriggerAxis() >= 0.50;
    }
}
