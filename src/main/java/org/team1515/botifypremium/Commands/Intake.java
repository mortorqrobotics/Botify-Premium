package org.team1515.botifypremium.Commands;

import org.team1515.botifypremium.Subsystems.Intaker;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class Intake extends CommandBase {
    private final Intaker intake;

    public Intake(Intaker intake) {
        this.intake = intake;

        addRequirements(intake);
    }

    @Override 
    public void execute() {
        intake.startIntake();
    }

    @Override
    public void end(boolean interrupted) {
        intake.end();
    }
}
