package org.team1515.botifypremium;

import org.team1515.botifypremium.Commands.Shoot;
import org.team1515.botifypremium.Subsystems.Shooter;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class OI {
    public static Joystick mainStick;
    public static Shooter shooter;

    public OI() {
        mainStick = new Joystick(0);
        shooter = new Shooter();
        
        configureButtons();
    }

    private void configureButtons() {
        Controls.SHOOT.whileHeld(new Shoot(shooter));
        Controls.EXTEND.whileHeld(new InstantCommand(Robot.climber::climb));
        Controls.RETRACT.whileHeld(new InstantCommand(Robot.climber::end));
    }
}
