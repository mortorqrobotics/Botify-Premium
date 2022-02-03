package org.team1515.botifypremium.Commands;
 
import org.team1515.botifypremium.Subsystems.Magazine;
 
import edu.wpi.first.wpilibj2.command.CommandBase;
 
public class MagDown extends CommandBase {
 
    private final Magazine magazine;
 
    public MagDown(Magazine magazine) {
        this.magazine = magazine;
 
        addRequirements(magazine);
    }
 
    @Override
    public void execute() {
        magazine.goDown();
    }
 
    @Override
    public void end(boolean interrupted) {
        magazine.end();
    }
}
