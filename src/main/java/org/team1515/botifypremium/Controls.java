package org.team1515.botifypremium;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class Controls {
    public static final Button SHOOT = new Button(OI.mainStick::getXButton);
    public static final Button EXTEND = new Button(OI.mainStick::getAButton);
    public static final Button RETRACT = new Button(OI.mainStick::getBButton);
    // public static final Button LATCH = new Button(OI.mainStick);
    // public static final Button UNLATCH = new Button(OI.mainStick);
    public static final Button RESETGYRO = new Button(OI.mainStick::getBackButton);
}
