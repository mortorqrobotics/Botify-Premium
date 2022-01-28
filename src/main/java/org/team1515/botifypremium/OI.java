package org.team1515.botifypremium;

import org.team1515.botifypremium.Commands.Climb;
import org.team1515.botifypremium.Commands.Intake;
import org.team1515.botifypremium.Commands.Shoot;
import org.team1515.botifypremium.Subsystems.Climber;
import org.team1515.botifypremium.Subsystems.Intaker;
import org.team1515.botifypremium.Subsystems.Shooter;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class OI {
    public static Joystick mainStick;
    public static Shooter shooter;
    public static Climber climber;
    public static Intaker intake;

    public OI() {
        mainStick = new Joystick(0);
        shooter = new Shooter();
        climber = new Climber();
        intake = new Intaker();
        
        configureButtons();
    }

    public Command getAutoCommand() {
        return new InstantCommand();
    }

    private void configureButtons() {
        Controls.SHOOT.whileHeld(new Shoot(shooter));
        Controls.CLIMB.whenPressed(new Climb(climber));
        Controls.INTAKE.whileHeld(new Intake(intake));
        Controls.OUTAKE.whileHeld(new Intake(intake));
    }
}
