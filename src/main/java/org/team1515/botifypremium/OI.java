package org.team1515.botifypremium;


import org.team1515.botifypremium.Commands.DefaultDriveCommand;
import org.team1515.botifypremium.Subsystems.Drivetrain;
import org.team1515.botifypremium.Utils.Gyroscope;
import org.team1515.botifypremium.Utils.Utilities;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class OI {
    public static XboxController mainStick;

    public static Gyroscope gyro;
    private final Drivetrain drivetrain;

    public OI() {
        mainStick = new XboxController(0);

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
