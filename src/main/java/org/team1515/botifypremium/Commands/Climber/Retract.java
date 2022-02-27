package org.team1515.botifypremium.Commands.Climber;

import org.team1515.botifypremium.Subsystems.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class Retract extends CommandBase {
    Climber climber;

    public Retract(Climber climber) {
        this.climber = climber;

        addRequirements(climber);
    }

    @Override
    public void execute() {
        climber.retract();
    }

    @Override
    public void end(boolean interrupted) {
        climber.end();
    }

    // @Override
    // public boolean isFinished() {
    //     // Stops when the climber reaches the minimum distance
    //     return climber.stringPot.getDist() <= climber.minDist;
    // }
}
