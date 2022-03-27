package org.team1515.botifypremium.Commands.Climber;

import org.team1515.botifypremium.OI;
import org.team1515.botifypremium.Subsystems.Climber;
import org.team1515.botifypremium.Utils.ClimberStates;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ManualClimb extends CommandBase {
    Climber verticalClimber;
    Climber diagonalClimber;
    int expand;

    public static ClimberStates climberState = ClimberStates.VERTICAL;

    public ManualClimb(Climber verticalClimber, Climber diagonalClimber, int expand) {
        this.verticalClimber = verticalClimber;
        this.diagonalClimber = diagonalClimber;
        this.expand = expand;

        addRequirements(verticalClimber);
        addRequirements(diagonalClimber);
    }

    @Override
    public void execute() {
        if(expand == 1){
            if(climberState == ClimberStates.VERTICAL)
                verticalClimber.expand();
            else {
                diagonalClimber.expand(); 
            }
        }
        if(expand == -1){
            if(climberState == ClimberStates.VERTICAL) {
                verticalClimber.retract();
            }
            else {
                diagonalClimber.retract(); 
            }
        }
    }

    @Override
    public void end(boolean interrupted) {
        verticalClimber.end();
        diagonalClimber.end();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
