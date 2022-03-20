package org.team1515.botifypremium.Commands.Autonomous;

import org.team1515.botifypremium.Robot;
import org.team1515.botifypremium.Commands.AutoAlign;
import org.team1515.botifypremium.Commands.DriveDist;
import org.team1515.botifypremium.Commands.RotateToPoint;
import org.team1515.botifypremium.Subsystems.Drivetrain;
import org.team1515.botifypremium.Subsystems.Intaker;
import org.team1515.botifypremium.Subsystems.Magazine;
import org.team1515.botifypremium.Subsystems.Shooter;
import org.team1515.botifypremium.Utils.Field;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutoCommand extends SequentialCommandGroup {
    public AutoCommand(Drivetrain drivetrain, Intaker intake, Magazine magazine, Shooter shooter) {
        addCommands(
            new DriveDist(drivetrain, Units.feetToMeters(6)),
            new DriveDist(drivetrain, Units.feetToMeters(1), -1),
            new AutoAlign(drivetrain, Robot.limelight),
            new AutoShoot(shooter, magazine, 4),
            new RotateToAngle(drivetrain, Rotation2d.fromDegrees(57 + 180)),
            new DriveDist(drivetrain, Units.feetToMeters(9.85)),
            new RotateToAngle(drivetrain, Rotation2d.fromDegrees(55 + 180)),
            new AutoAlign(drivetrain, Robot.limelight),
            new AutoShoot(shooter, magazine, 6)
        );
    }
}
