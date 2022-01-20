package org.team1515.botifypremium;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class OI {
    public static Joystick mainStick;

    public OI() {
        mainStick = new Joystick(0);

        Controls.SHOOT.whenPressed(
            new InstantCommand(() -> Robot.shooter.shoot())
        );
    }
}
