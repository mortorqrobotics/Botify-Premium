package org.team1515.botifypremium;

import org.team1515.botifypremium.Commands.Intake;
import org.team1515.botifypremium.Commands.MagDown;
import org.team1515.botifypremium.Commands.MagUp;
import org.team1515.botifypremium.Commands.Outtake;
import org.team1515.botifypremium.Commands.Shoot;
import org.team1515.botifypremium.Commands.Climber.Climb;
import org.team1515.botifypremium.Subsystems.Climber;
import org.team1515.botifypremium.Subsystems.Intaker;
import org.team1515.botifypremium.Subsystems.Magazine;
import org.team1515.botifypremium.Commands.DefaultDriveCommand;
import org.team1515.botifypremium.Subsystems.Drivetrain;
import org.team1515.botifypremium.Subsystems.Shooter;
import org.team1515.botifypremium.Utils.Gyroscope;
import org.team1515.botifypremium.Utils.Utilities;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class OI {
    public static XboxController mainStick;
    public static Shooter shooter;
    public static Climber climber;
    public static Intaker intake;
    public static Magazine magazine;
    public static Gyroscope gyro;
    private final Drivetrain drivetrain;

    public OI() {
        mainStick = new XboxController(0);
        shooter = new Shooter();
        // climber = new Climber();
        // intake = new Intaker();
        // magazine = new Magazine();
        gyro = new Gyroscope();
        drivetrain = new Drivetrain();
        drivetrain.setDefaultCommand(new DefaultDriveCommand(
            drivetrain,
            () -> -modifyAxis(mainStick.getLeftY()) * Drivetrain.MAX_VELOCITY_METERS_PER_SECOND,
            () -> -modifyAxis(mainStick.getLeftX()) * Drivetrain.MAX_VELOCITY_METERS_PER_SECOND,
            () -> -modifyAxis(mainStick.getRightX()) * Drivetrain.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND
        ));

        configureButtons();
    }

    public Command getAutoCommand() {
        return new InstantCommand();
    }

    /**
     * Binds the controller buttons to commands
     */
    private void configureButtons() {
        Controls.SHOOT.whileHeld(new Shoot(shooter));
        // Controls.CLIMB.whenHeld(new Climb(climber));
        // Controls.INTAKE.whileHeld(new Intake(intake));
        // Controls.OUTAKE.whileHeld(new Outtake(intake));
        // Controls.MAGUP.whileHeld(new MagUp(magazine));
        // Controls.MAGDOWN.whileHeld(new MagDown(magazine));
        
        // Back button zeros the gyroscope
        Controls.RESETGYRO.whenPressed(drivetrain::zeroGyroscope); // No requirements because we don't need to interrupt anything
            
    }

    private static double modifyAxis(double value) {
        value = Utilities.deadband(value, 0.05);

        // Square the axis
        value = Math.copySign(value * value, value);

        return value;
    }
}
