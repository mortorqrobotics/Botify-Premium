package org.team1515.botifypremium.Commands.Autonomous;

import org.team1515.botifypremium.Robot;
import org.team1515.botifypremium.Commands.AutoAlign;
import org.team1515.botifypremium.Commands.DriveDist;
import org.team1515.botifypremium.Subsystems.Drivetrain;
import org.team1515.botifypremium.Subsystems.Intaker;
import org.team1515.botifypremium.Subsystems.Magazine;
import org.team1515.botifypremium.Subsystems.Shooter;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutoCommand extends SequentialCommandGroup {
    public AutoCommand(Drivetrain drivetrain, Intaker intake, Magazine magazine, Shooter shooter) {
        addCommands(
            new DriveDist(drivetrain, Units.feetToMeters(6)), // Drive back into ball and hit wall
            new DriveDist(drivetrain, Units.feetToMeters(1), -1), // Drive forward to get off the wall
            new AutoAlign(drivetrain, Robot.limelight), // Align with the target
            new AutoShoot(shooter, magazine, 4), // Shoot the ball
            new RotateToAngle(drivetrain, Rotation2d.fromDegrees(57 + 180)), // Rotate to the next ball (intake forward)
            new DriveDist(drivetrain, Units.feetToMeters(9.85)), // Drive towards the next ball
            new RotateToAngle(drivetrain, Rotation2d.fromDegrees(55)), // Rotate to the hub (shooter forward)
            new AutoAlign(drivetrain, Robot.limelight), // Align with the target
            new AutoShoot(shooter, magazine, 6) // Shoot the final ball
        );
    }
}
