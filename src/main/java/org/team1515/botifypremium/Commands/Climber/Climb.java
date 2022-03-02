package org.team1515.botifypremium.Commands.Climber;

import org.team1515.botifypremium.Subsystems.Climber;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class Climb extends SequentialCommandGroup {

    /**
     * Runs each climber command one after another
     */

    public Climb(Climber climber) {
        addCommands(
            new Step1(climber),
            new Step2(climber),
            new Step3(climber),
            new Step4(climber),
            new Step5(climber),
            new Step6(climber),
            new Step7(climber),
            new Step8(climber)
        );
    }
}
