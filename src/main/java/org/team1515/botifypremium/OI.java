package org.team1515.botifypremium;

import org.team1515.botifypremium.Commands.Climb;
import org.team1515.botifypremium.Commands.Retract;
import org.team1515.botifypremium.Commands.Shoot;
import org.team1515.botifypremium.Subsystems.Climber;
import org.team1515.botifypremium.Subsystems.Shooter;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class OI {
    public static Joystick mainStick;
    public static Shooter shooter;
    public static Climber climber;

    public OI() {
        mainStick = new Joystick(0);
        shooter = new Shooter();
        climber = new Climber();
        
        configureButtons();
    }

    public Command getAutoCommand() {
        return new InstantCommand();
    }

    private void configureButtons() {
        Controls.SHOOT.whileHeld(new Shoot(shooter));
        Controls.EXTEND.whileHeld(new Climb(climber));
        Controls.RETRACT.whileHeld(new Retract(climber));
        Controls.LATCH.whenPressed(new InstantCommand(Robot.latcher::latch));
        Controls.UNLATCH.whenPressed(new InstantCommand(Robot.latcher::unlatch));
    }
}
