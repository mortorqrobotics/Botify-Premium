package org.team1515.botifypremium.Commands;

import org.team1515.botifypremium.Subsystems.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class Climb extends CommandBase {

    private final Climber climber;

    public Climb(Climber climber) {
        this.climber = climber;
    }

    @Override 
    public void execute() {
        climber.climb();
    }

    @Override
    public void end(boolean interrupted) {
        climber.end();
    }
}
