package org.team1515.botifypremium.Commands.Climber;

import org.team1515.botifypremium.Subsystems.Latcher;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class Latch extends CommandBase {
    Latcher latcher;

    public Latch(Latcher latcher) {
        this.latcher = latcher;

        addRequirements(latcher);
    }

    @Override
    public void execute() {
        latcher.latch();
    }

    @Override
    public boolean isFinished() {
        // Stops when the servo angle reaches the closed position 
        return latcher.getAngle() == Latcher.closePos;
    }
}