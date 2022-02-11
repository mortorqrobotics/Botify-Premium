package org.team1515.botifypremium.Commands;
 
import org.team1515.botifypremium.Subsystems.Magazine;
 
import edu.wpi.first.wpilibj2.command.CommandBase;
 
public class MagUp extends CommandBase {
 
    private final Magazine magazine;
 
    public MagUp(Magazine magazine) {
        this.magazine = magazine;
 
        addRequirements(magazine);
    }
 
    @Override
    public void execute() {
        magazine.goUp();
    }
 
    @Override
    public void end(boolean interrupted) {
        magazine.end();
    }
}
