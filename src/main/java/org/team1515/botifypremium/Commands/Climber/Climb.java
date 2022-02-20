package org.team1515.botifypremium.Commands.Climber;

import org.team1515.botifypremium.Subsystems.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class Climb extends CommandBase{ //extends SequentialCommandGroup

    /**
     * Runs each climber command one after another
     */

    private final Climber climb;

    public Climb(Climber climb) {
        this.climb = climb;

        addRequirements(climb);
    }

    @Override 
    public void execute() {
        climb.climb();
    }

    @Override
    public void end(boolean interrupted) {
        climb.end();
    }
    // public Climb(Climber climber) {
        // addCommands(
        //     new Extend(climber),
        //     new Retract(climber)
        // );
    // }
}
