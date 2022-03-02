package org.team1515.botifypremium.Commands.Climber;

import org.team1515.botifypremium.Subsystems.Climber;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class Climb extends SequentialCommandGroup {

    /**
     * Runs each climber command one after another
     */

    public Climb(Climber climberL, Climber climberLD, Climber climberR, Climber climberRD) {
        addCommands(
            new Extend(climberL, climberR), //Step 1
            new Retract(climberL, climberR), //Step 2
            new Extend(climberLD, climberRD), //Step 3
            new Retract(climberLD, climberRD), //Step 4
            new Extend(climberL, climberR), //Step 5
            new Retract(climberL, climberR), //Step 6
            new Extend(climberLD, climberRD), //Step 7
            new Extend(climberL, climberR) //Step 8
        );
    }
}

/*
Step 1: Both verticals go up

Step 2: Borth verticals down

Step 3: Both diagonals go up

Step 4: Both diagonals down

Step 5: Both verticals go up

Step 6: Both verticals down

Step 7: Both diagonals go up

Step 8: Both verticals go up

(Optional) Step 9: Both diagonals down
*/
