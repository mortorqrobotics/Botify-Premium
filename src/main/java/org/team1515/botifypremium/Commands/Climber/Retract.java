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
    public boolean isFinished() {
        if(climber.stringPot.getDist() <= climber.minDist) {
            return true;
        }
        return false;
    }
}
