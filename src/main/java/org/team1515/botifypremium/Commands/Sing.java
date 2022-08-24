package org.team1515.botifypremium.Commands;

import org.team1515.botifypremium.Subsystems.Singer;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class Sing extends CommandBase {

    private final Singer singer;
    boolean finished = false;
    public Sing(Singer singer) {
        this.singer = singer;
        
    }

    @Override 
    public void execute() {
        singer.sing();
    }

    @Override
    public boolean isFinished() {
        return singer.isFinished;
    }
}
