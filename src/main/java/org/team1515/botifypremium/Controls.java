package org.team1515.botifypremium;

import edu.wpi.first.wpilibj2.command.button.Button;

public class Controls {
    public static final Button SHOOT = new Button(OI.mainStick::getXButton);
    public static final Button CLIMB = new Button(OI.mainStick::getYButton);
    public static final Button OUTAKE = new Button(OI.mainStick::getLeftBumper);
    public static final Button INTAKE = new Button(OI.mainStick::getRightBumper);
    public static final Button RESETGYRO = new Button(OI.mainStick::getBackButton);
}
