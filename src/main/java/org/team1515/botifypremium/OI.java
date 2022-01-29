package org.team1515.botifypremium;

import org.team1515.botifypremium.Commands.Intake;
import org.team1515.botifypremium.Commands.MagDown;
import org.team1515.botifypremium.Commands.MagUp;
import org.team1515.botifypremium.Commands.Shoot;
import org.team1515.botifypremium.Subsystems.Climber;
import org.team1515.botifypremium.Subsystems.Intaker;
import org.team1515.botifypremium.Subsystems.Magazine;
import org.team1515.botifypremium.Subsystems.Shooter;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class OI {
    public static Joystick mainStick;
    public static Shooter shooter;
    public static Climber climber;
    public static Intaker intake;
    public static Magazine mag;

    public OI() {
        mainStick = new Joystick(0);
        shooter = new Shooter();
        climber = new Climber();
        intake = new Intaker();
        mag = new Magazine();
        
        configureButtons();
    }

    public Command getAutoCommand() {
        return new InstantCommand();
    }

    private void configureButtons() {
        Controls.SHOOT.whileHeld(new Shoot(shooter));
        Controls.CLIMB.whenPressed(new InstantCommand(Robot.climber::climb));
        Controls.INTAKE.whileHeld(new Intake(intake));
        Controls.OUTAKE.whileHeld(new Intake(intake));
        Controls.MAGUP.whileHeld(new MagUp(mag));
        Controls.MAGDOWN.whileHeld(new MagDown(mag));
    }
}
