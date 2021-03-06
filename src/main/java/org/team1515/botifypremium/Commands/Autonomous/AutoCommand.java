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

    /**
     * Runs auto one command after another (finished when the isFinished method returns true)
     * @param drivetrain
     * @param intake
     * @param magazine
     * @param shooter
     */
    public AutoCommand(Drivetrain drivetrain, Intaker intake, Magazine magazine, Shooter shooter) {
        addCommands(
            new InstantCommand(() -> OI.gyro.m_navx.zeroYaw()),
            new DriveDist(drivetrain, Units.feetToMeters(6), 0.25), // Drive back into ball and hit wall
            new WaitCommand(0.25), // Wait to prevent tipping over
            new DriveDist(drivetrain, Units.feetToMeters(0.75), -0.5), // Drive forward to get off the wall
            new AutoAlign(drivetrain), // Align with the target
            new AutoShoot(shooter, magazine, 2.5, 0, 9500), // Shoot the ball
            new RotateToAngle(drivetrain, Rotation2d.fromDegrees(225)), // Rotate to the next ball (intake forward)
            new DriveDist(drivetrain, Units.feetToMeters(10.05), 0.25), // Drive towards the next ball
            new RotateToAngle(drivetrain, Rotation2d.fromDegrees(306)), // Rotate to the hub (shooter forward)
            new AutoAlign(drivetrain), // Align with the target
            new AutoShoot(shooter, magazine, 2.5, 0, 9600), // Shoot the final ball
            new DriveAtAngle(drivetrain, Units.feetToMeters(11), Math.toRadians(180 - 9)) // Drive to human player station
        );
    }
}
