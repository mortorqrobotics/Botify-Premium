package org.team1515.botifypremium.Commands;

import org.team1515.botifypremium.Subsystems.Shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class Shoot extends CommandBase {

    private final Shooter shooter;

    public Shoot(Shooter shooter) {
        this.shooter = shooter;
    }

    @Override 
    public void execute() {
        shooter.shoot();
    }

    @Override
    public void initialize() {
        shooter.updateSpeed();
    }

    @Override
    public void end(boolean interrupted) {
        shooter.end();
    }
}
