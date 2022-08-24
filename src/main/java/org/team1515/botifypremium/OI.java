package org.team1515.botifypremium;

import org.team1515.botifypremium.Commands.Intake;
import org.team1515.botifypremium.Commands.MagDown;
import org.team1515.botifypremium.Commands.MagUp;
import org.team1515.botifypremium.Commands.Outtake;
import org.team1515.botifypremium.Commands.Shoot;
import org.team1515.botifypremium.Commands.Sing;
import org.team1515.botifypremium.Commands.Autonomous.AutoCommand;
import org.team1515.botifypremium.Commands.Autonomous.DriveAtAngle;
import org.team1515.botifypremium.Commands.Autonomous.RotateToAngle;
import org.team1515.botifypremium.Commands.Autonomous.TwoBallAuto;
import org.team1515.botifypremium.Commands.Climber.Expand;
import org.team1515.botifypremium.Commands.Climber.Retract;
import org.team1515.botifypremium.Commands.Climber.ManualClimb;
import org.team1515.botifypremium.Commands.Sing;
import org.team1515.botifypremium.Subsystems.Climber;
import org.team1515.botifypremium.Subsystems.Intaker;
import org.team1515.botifypremium.Subsystems.Magazine;
import java.util.function.BooleanSupplier;

import com.ctre.phoenix.motorcontrol.ControlMode;

import org.team1515.botifypremium.Commands.AutoAlign;
import org.team1515.botifypremium.Commands.DefaultDriveCommand;
import org.team1515.botifypremium.Subsystems.Drivetrain;
import org.team1515.botifypremium.Subsystems.Shooter;
import org.team1515.botifypremium.Subsystems.Singer;
import org.team1515.botifypremium.Utils.ClimberDirection;
import org.team1515.botifypremium.Utils.ClimberStates;
import org.team1515.botifypremium.Utils.Gyroscope;
import org.team1515.botifypremium.Utils.Utilities;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Button;

public class OI {
    public static XboxController mainStick;
    public static XboxController secondStick;
    public static Shooter shooter;
    public static Climber climberRV;
    public static Climber climberLV;
    public static Climber climberRD;
    public static Climber climberLD;
    public static Intaker intake;
    public static Magazine magazine;
    public static Gyroscope gyro;
    private final Drivetrain drivetrain;
    private final Singer singer;

    public OI() {
        mainStick = new XboxController(0);
        secondStick = new XboxController(1);
        shooter = new Shooter();

        climberRV = new Climber(RobotMap.RIGHT_VERTICAL_CLIMBER_ID, -1);
        climberLV = new Climber(RobotMap.LEFT_VERTICAL_CLIMBER_ID, 1);
        climberRD = new Climber(RobotMap.RIGHT_DIAGONAL_CLIMBER_ID, -1);
        climberLD = new Climber(RobotMap.LEFT_DIAGONAL_CLIMBER_ID, 1);

        singer = new Singer();

        intake = new Intaker();
        magazine = new Magazine();
        gyro = new Gyroscope();
        drivetrain = new Drivetrain();
        drivetrain.setDefaultCommand(new DefaultDriveCommand(
            drivetrain,
            () -> -modifyAxis(-mainStick.getLeftY() * getRobotSpeed()) * Drivetrain.MAX_VELOCITY_METERS_PER_SECOND,
            () -> -modifyAxis(-mainStick.getLeftX() * getRobotSpeed()) * Drivetrain.MAX_VELOCITY_METERS_PER_SECOND,
            () -> -modifyAxis(mainStick.getRightX() * getRobotSpeed()) * Drivetrain.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND,
            Controls.DRIVE_ROBOT_ORIENTED
        ));

        configureButtons();
    }

    /**
     * Slow robot down if the trigger is held
     * @return robot speed (percentage)
     */
    public static double getRobotSpeed() {
        return Controls.getLeftTrigger() ? 0.45 : 0.7;
        // return 0.7;
    } 

    public Command getAutoCommand() {
        // return new AutoCommand(drivetrain, intake, magazine, shooter); // 3 ball auto
        return new TwoBallAuto(drivetrain, intake, magazine, shooter); // 2 Ball auto
    }

    private void configureButtons() {
        Controls.SHOOT.whileHeld(new Shoot(shooter));

        // Climber expand and retract together
        Controls.EXPAND_VERTICAL.whileHeld(new Expand(climberRV, climberLV));
        Controls.RETRACT_VERTICAL.whileHeld(new Retract(climberRV, climberLV));
        Controls.EXPAND_DIAGONAL.whileHeld(new Expand(climberLD, climberRD));
        Controls.RETRACT_DIAGONAL.whileHeld(new Retract(climberLD, climberRD));

        // Climber manual override to align
        Controls.MANUAL_CLIMBER_L.whileHeld(new ManualClimb(climberLV, climberLD));
        Controls.MANUAL_CLIMBER_R.whileHeld(new ManualClimb(climberRV, climberRD));

        Controls.INTAKE.whileHeld(new Intake(intake));
        Controls.OUTAKE.whileHeld(new Outtake(intake));
        Controls.MAGUP.whileHeld(new MagUp(magazine));
        Controls.MAGDOWN.whileHeld(new MagDown(magazine));
        Controls.RESET_SPEED.whenPressed(new InstantCommand(() -> shooter.updateSpeed()));

        Controls.ROBOT_ALIGN.whenPressed(new AutoAlign(drivetrain));

        // Back button zeros the gyroscope        
        Controls.RESETGYRO.whenPressed(drivetrain::zeroGyroscope); // No requirements because we don't need to interrupt anything

        // Set the mode for the manual climb (using this because there aren't enough buttons)
        Controls.LEFT_DPAD.whenPressed(new InstantCommand(() -> ManualClimb.climberState = ClimberStates.VERTICAL));
        Controls.RIGHT_DPAD.whenPressed(new InstantCommand(() -> ManualClimb.climberState = ClimberStates.DIAGONAL));
        Controls.UP_DPAD.whenPressed(new InstantCommand(() -> ManualClimb.climberDirection = ClimberDirection.EXTEND));
        Controls.DOWN_DPAD.whenPressed(new InstantCommand(() -> ManualClimb.climberDirection = ClimberDirection.RETRACT));
    
        Controls.Sing.whenPressed(new Sing(singer));
    }

    private static double modifyAxis(double value) {
        value = Utilities.deadband(value, 0.08);

        // Square the axis
        value = Math.copySign(value * value, value);

        return value;
    }

}
 