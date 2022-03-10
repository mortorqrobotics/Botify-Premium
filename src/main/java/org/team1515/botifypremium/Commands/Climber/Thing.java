package org.team1515.botifypremium.Commands.Climber;

import org.team1515.botifypremium.Subsystems.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class Thing extends CommandBase {
    Climber climber1;
    int expand;

    public Thing(Climber climber1, int expand) {
        this.climber1 = climber1;
        this.expand = expand;

        addRequirements(climber1);
    }

    @Override
    public void execute() {
        if(expand == 1){
        climber1.expand();}
        if(expand == -1){
            climber1.retract();
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
