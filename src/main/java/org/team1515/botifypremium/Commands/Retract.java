package org.team1515.botifypremium.Commands;

import org.team1515.botifypremium.Subsystems.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class Retract extends CommandBase {

    private final Climber climber;

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
}
