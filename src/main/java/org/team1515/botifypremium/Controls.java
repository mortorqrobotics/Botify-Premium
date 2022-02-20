package org.team1515.botifypremium;

import edu.wpi.first.wpilibj2.command.button.Button;

public class Controls {
    public static final Button SHOOT = new Button(OI.mainStick::getXButton);
    public static final Button CLIMB = new Button(Controls::getLeftTrigger);
    public static final Button RETRACT = new Button(Controls::getRightTrigger);
    public static final Button OUTAKE = new Button(OI.mainStick::getLeftBumper);
    public static final Button INTAKE = new Button(OI.mainStick::getRightBumper);
    public static final Button MAGUP = new Button(OI.mainStick::getAButton);
    public static final Button MAGDOWN = new Button(OI.mainStick::getBButton);
    public static final Button RESETGYRO = new Button(OI.mainStick::getBackButton);

    public static boolean getRightTrigger(){
        return OI.mainStick.getRightTriggerAxis() >= 0.50;
    }

    public static boolean getLeftTrigger(){
        return OI.mainStick.getLeftTriggerAxis() >= 0.50;
    }
}
