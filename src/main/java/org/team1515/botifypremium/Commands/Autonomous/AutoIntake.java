package org.team1515.botifypremium.Commands.Autonomous;

import org.team1515.botifypremium.Subsystems.Intaker;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoIntake extends CommandBase {
    private Intaker m_intake;
    private double runTime;
    private Timer timer;
    
    public AutoIntake(Intaker intake, double runTime) {
        this.m_intake = intake;
        this.runTime = runTime;
        this.timer = new Timer();

        addRequirements(m_intake);
    }

    @Override
    public void initialize() {
        this.timer.reset();
        this.timer.start();
    }

    @Override
    public void execute() { 
        m_intake.startIntake();
    }

    @Override
    public boolean isFinished() {
        return timer.hasElapsed(runTime);
    }
}
