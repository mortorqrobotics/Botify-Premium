package org.team1515.botifypremium.Commands.Autonomous;

import org.team1515.botifypremium.Subsystems.Magazine;
import org.team1515.botifypremium.Subsystems.Shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoShoot extends CommandBase {
    private Shooter m_shooter;
    private Magazine m_magazine;
    private double runTime;
    private Timer timer;
    
    public AutoShoot(Shooter shooter, Magazine magazine, double runTime) {
        this.m_shooter = shooter;
        this.m_magazine = magazine;
        this.runTime = runTime;
        this.timer = new Timer();;

        addRequirements(m_shooter);
    }

    @Override
    public void initialize() {
        this.timer.reset();
        this.timer.start();
    }

    @Override
    public void execute() { 
        if(timer.hasElapsed(5)) {
            m_magazine.goUp();
        }

        m_shooter.shoot();
    }

    @Override
    public boolean isFinished() {
        return timer.hasElapsed(runTime);
    }
}
