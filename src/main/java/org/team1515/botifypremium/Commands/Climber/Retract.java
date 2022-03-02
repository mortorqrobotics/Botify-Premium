package org.team1515.botifypremium.Commands.Climber;

import org.team1515.botifypremium.Subsystems.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class Retract extends CommandBase {
    Climber climberL;
    Climber climberR;

    public Retract(Climber climberL, Climber climberR) {
        this.climberL = climberL;
        this.climberR = climberR;

        addRequirements(climberL);
        addRequirements(climberR);
    }

    @Override
    public void execute() {
        climberL.retract();
        climberR.retract();
    }

    @Override
    public void end(boolean interrupted) {
        climberL.end();
        climberR.end();
    }

    @Override
    public boolean isFinished() {
        if(climberL.stringPot.getDist() <= climberL.maxDist && climberR.stringPot.getDist() <= climberR.maxDist) {
            return true;
        }
        return false;
    }
}
