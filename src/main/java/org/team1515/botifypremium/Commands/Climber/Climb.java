package org.team1515.botifypremium.Commands.Climber;

import org.team1515.botifypremium.Subsystems.Climber;
import org.team1515.botifypremium.Subsystems.Latcher;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class Climb extends SequentialCommandGroup  {
    public Climb(Climber climber, Latcher latcher) {
        addCommands(
            new Extend(climber),
            new Latch(latcher),
            new Retract(climber)
        );
    }
}
