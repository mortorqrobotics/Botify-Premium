package org.team1515.botifypremium;

import org.team1515.botifypremium.Commands.Climb;
import org.team1515.botifypremium.Commands.DefaultDriveCommand;
import org.team1515.botifypremium.Commands.Retract;
import org.team1515.botifypremium.Commands.Shoot;
import org.team1515.botifypremium.Subsystems.Climber;
import org.team1515.botifypremium.Subsystems.Drivetrain;
import org.team1515.botifypremium.Subsystems.Shooter;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class OI {
    public static Joystick mainStick;
    public static Shooter shooter;
    public static Climber climber;
    private final Drivetrain drivetrain;



    public OI() {
        mainStick = new Joystick(0);
        shooter = new Shooter();
        climber = new Climber();
        drivetrain = new Drivetrain();

        drivetrain.setDefaultCommand(new DefaultDriveCommand(
            drivetrain,
            () -> -modifyAxis(mainStick.getY()) * Drivetrain.MAX_VELOCITY_METERS_PER_SECOND,
            () -> -modifyAxis(mainStick.getX()) * Drivetrain.MAX_VELOCITY_METERS_PER_SECOND,
            () -> -modifyAxis(mainStick.getX()) * Drivetrain.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND
    ));

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
        
        // Back button zeros the gyroscope
        Controls.RESETGYRO.whenPressed(drivetrain::zeroGyroscope); // No requirements because we don't need to interrupt anything
            
    }

    private static double deadband(double value, double deadband) {
        if (Math.abs(value) > deadband) {
          if (value > 0.0) {
            return (value - deadband) / (1.0 - deadband);
          } else {
            return (value + deadband) / (1.0 - deadband);
          }
        } else {
          return 0.0;
        }
      }
    
      private static double modifyAxis(double value) {
        // Deadband
        value = deadband(value, 0.05);
    
        // Square the axis
        value = Math.copySign(value * value, value);
    
        return value;
      }
}
