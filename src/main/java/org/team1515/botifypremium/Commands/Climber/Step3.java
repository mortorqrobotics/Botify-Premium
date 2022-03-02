package org.team1515.botifypremium.Commands.Climber;

import org.team1515.botifypremium.Subsystems.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class Step3 extends CommandBase {
    Climber climber;

    public Step3(Climber climber) {
        this.climber = climber;

        addRequirements(climber);
    }

    @Override
    public void execute() {
        climber.climb();
    }

    @Override
    public void end(boolean interrupted) {
        climber.end();
    }

    @Override
    public boolean isFinished() {
        if(climber.stringPot.getDist() >= climber.maxDist) {
            return true;
        }
        return false;
    }
}
