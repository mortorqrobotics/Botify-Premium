package org.team1515.botifypremium.Commands.Autonomous;

import org.team1515.botifypremium.Robot;
import org.team1515.botifypremium.Commands.AutoAlign;
import org.team1515.botifypremium.Commands.DriveDist;
import org.team1515.botifypremium.Subsystems.Drivetrain;
import org.team1515.botifypremium.Subsystems.Intaker;
import org.team1515.botifypremium.Subsystems.Magazine;
import org.team1515.botifypremium.Subsystems.Shooter;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class TwoBallAuto extends SequentialCommandGroup {
    public TwoBallAuto(Drivetrain drivetrain, Intaker intake, Magazine magazine, Shooter shooter) {
        addCommands(
            new DriveDist(drivetrain, (Units.feetToMeters(10.0)) - 1.25),
            new AutoAlign(drivetrain, Robot.limelight),
            new AutoShoot(shooter, magazine, 7)
        );
    }
}
