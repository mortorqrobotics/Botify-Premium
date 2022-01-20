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

        Controls.EXTEND.whileHeld(
            new InstantCommand(() -> Robot.climber.climb())
        );

        Controls.EXTEND.whenReleased(
            new InstantCommand(() -> Robot.climber.stop())  
        );

        Controls.RETRACT.whileHeld(
            new InstantCommand(() -> Robot.climber.retract())
        );

        Controls.RETRACT.whenReleased(
            new InstantCommand(() -> Robot.climber.stop())  
        );

        Controls.LATCH.whenPressed(
            new InstantCommand(() -> Robot.latcher.latch())  
        );

        Controls.UNLATCH.whenPressed(
            new InstantCommand(() -> Robot.latcher.unlatch())  
        );

    }
}
