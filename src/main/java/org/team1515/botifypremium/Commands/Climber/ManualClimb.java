package org.team1515.botifypremium.Commands.Climber;

import org.team1515.botifypremium.OI;
import org.team1515.botifypremium.Subsystems.Climber;
import org.team1515.botifypremium.Utils.ClimberDirection;
import org.team1515.botifypremium.Utils.ClimberStates;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ManualClimb extends CommandBase {
    Climber verticalClimber;
    Climber diagonalClimber;
    int expand;

    public static ClimberStates climberState = ClimberStates.VERTICAL;
    public static ClimberDirection climberDirection = ClimberDirection.EXTEND;

    public ManualClimb(Climber verticalClimber, Climber diagonalClimber) {
        this.verticalClimber = verticalClimber;
        this.diagonalClimber = diagonalClimber;

        addRequirements(verticalClimber);
        addRequirements(diagonalClimber);
    }

    @Override
    public void execute() {
        if(climberDirection == ClimberDirection.EXTEND){
            if(climberState == ClimberStates.VERTICAL)
                verticalClimber.expand();
            else {
                diagonalClimber.expand(); 
            }
        }
        if(climberDirection == ClimberDirection.RETRACT){
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
