package org.team1515.botifypremium.Commands.Autonomous;

import org.team1515.botifypremium.Subsystems.Magazine;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoMag extends CommandBase {
    private Magazine m_magazine;
    private double runTime;
    private Timer timer;
    
    public AutoMag(Magazine magazine, double runTime) {
        this.m_magazine = magazine;
        this.runTime = runTime;
        this.timer = new Timer();

        addRequirements(m_magazine);
    }

    @Override
    public void initialize() {
        this.timer.reset();
        this.timer.start();
    }

    @Override
    public void execute() { 
        m_magazine.goUp();
    }

    @Override
    public boolean isFinished() {
        return timer.hasElapsed(runTime);
    }
}
