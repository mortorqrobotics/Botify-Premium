package org.team1515.botifypremium;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class Controls {
    private static final int A_BUTTON = 1;
    public static final JoystickButton SHOOT = new JoystickButton(OI.mainStick, A_BUTTON);

    private static final int R_BUTTON = 8;
    public static final JoystickButton EXTEND = new JoystickButton(OI.mainStick, R_BUTTON);

    private static final int L_BUTTON = 7;
    public static final JoystickButton RETRACT = new JoystickButton(OI.mainStick, L_BUTTON);

    public static final int Y_BUTTON = 4;
    public static final JoystickButton LATCH = new JoystickButton(OI.mainStick, Y_BUTTON);

    public static final int X_BUTTON = 3;
    public static final JoystickButton UNLATCH = new JoystickButton(OI.mainStick, X_BUTTON);

    private static final int LT_BUTTON = 10;
    public static final JoystickButton OUTAKE = new JoystickButton(OI.mainStick, LT_BUTTON);
    
    private static final int RT_BUTTON = 11;
    public static final JoystickButton INTAKE = new JoystickButton(OI.mainStick, RT_BUTTON);

}
