package org.team1515.botifypremium.Commands.Climber;

import org.team1515.botifypremium.Subsystems.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class Retract extends CommandBase {
    Climber climber1;
    Climber climber2;

    public Retract(Climber climber1, Climber climber2) {
        this.climber1 = climber1;
        this.climber2 = climber2;

        addRequirements(climber1);
        addRequirements(climber2);
    }

    @Override
    public void execute() {
        climber1.retract();
        climber2.retract();
    }

    @Override
    public void end(boolean interrupted) {
        climber1.end();
        climber2.end();
    }
}
