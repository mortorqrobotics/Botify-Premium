package org.team1515.botifypremium.Commands.Autonomous;

import org.team1515.botifypremium.Robot;
import org.team1515.botifypremium.RobotMap;
import org.team1515.botifypremium.Commands.AutoAlign;
import org.team1515.botifypremium.Commands.DriveDist;
import org.team1515.botifypremium.Subsystems.Drivetrain;
import org.team1515.botifypremium.Subsystems.Intaker;
import org.team1515.botifypremium.Subsystems.Magazine;
import org.team1515.botifypremium.Subsystems.Shooter;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutoCommand extends SequentialCommandGroup {
    public AutoCommand(Drivetrain drivetrain, Intaker intake, Magazine magazine, Shooter shooter) {
        addCommands(
            new DriveDist(drivetrain, (12.25 * 12 * RobotMap.INCHES_TO_METERS) - 1.25),
            // new AutoAlign(drivetrain, Robot.limelight),
            new AutoShoot(shooter, magazine, 5)
        );
    }
}
