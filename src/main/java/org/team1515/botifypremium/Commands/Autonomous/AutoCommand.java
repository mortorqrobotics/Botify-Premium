package org.team1515.botifypremium.Commands.Autonomous;

import org.team1515.botifypremium.OI;
import org.team1515.botifypremium.Robot;
import org.team1515.botifypremium.Commands.AutoAlign;
import org.team1515.botifypremium.Commands.DriveDist;
import org.team1515.botifypremium.Subsystems.Drivetrain;
import org.team1515.botifypremium.Subsystems.Intaker;
import org.team1515.botifypremium.Subsystems.Magazine;
import org.team1515.botifypremium.Subsystems.Shooter;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class AutoCommand extends SequentialCommandGroup {
    public AutoCommand(Drivetrain drivetrain, Intaker intake, Magazine magazine, Shooter shooter) {
        addCommands(
            new InstantCommand(() -> OI.gyro.m_navx.zeroYaw()),
            new DriveDist(drivetrain, Units.feetToMeters(6)), // Drive back into ball and hit wall
            new WaitCommand(0.25), // Wait to prevent tipping over
            new DriveDist(drivetrain, Units.feetToMeters(1), -1), // Drive forward to get off the wall
            new AutoAlign(drivetrain, Robot.limelight), // Align with the target
            new AutoShoot(shooter, magazine, 2.5, 0), // Shoot the ball
            new RotateToAngle(drivetrain, Rotation2d.fromDegrees(230)), // Rotate to the next ball (intake forward)
            new DriveDist(drivetrain, Units.feetToMeters(10.05)), // Drive towards the next ball
            new RotateToAngle(drivetrain, Rotation2d.fromDegrees(306)), // Rotate to the hub (shooter forward)
            new AutoAlign(drivetrain, Robot.limelight), // Align with the target
            new AutoShoot(shooter, magazine, 2.5, 0), // Shoot the final ball
            new DriveAtAngle(drivetrain, Units.feetToMeters(11), Math.toRadians(180 - 9)) // Drive to final
        );
    }
}
