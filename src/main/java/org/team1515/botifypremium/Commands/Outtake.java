package org.team1515.botifypremium.Commands;

import org.team1515.botifypremium.Subsystems.Intaker;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class Outtake extends CommandBase {
    private final Intaker intake;

    public Outtake(Intaker intake) {
        this.intake = intake;

        addRequirements(intake);
    }

    @Override 
    public void execute() {
        intake.startOuttake();
    }

    @Override
    public void end(boolean interrupted) {
        intake.end();
    }
}
