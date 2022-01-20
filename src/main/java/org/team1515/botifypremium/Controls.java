package org.team1515.botifypremium;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class Controls {
    private static final int A_BUTTON = 1;
    public static final JoystickButton SHOOT = new JoystickButton(OI.mainStick, A_BUTTON);

    private static final int R_BUTTON = 8;
    public static final JoystickButton EXTEND = new JoystickButton(OI.mainStick, R_BUTTON);

    private static final int L_BUTTON = 7;
    public static final JoystickButton RETRACT = new JoystickButton(OI.mainStick, L_BUTTON);


}
