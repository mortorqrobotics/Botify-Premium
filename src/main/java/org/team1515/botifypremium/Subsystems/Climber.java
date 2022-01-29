package org.team1515.botifypremium.Subsystems;

import org.team1515.botifypremium.RobotMap;
import org.team1515.botifypremium.Utils.StringPot;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Climber extends SubsystemBase {

    private CANSparkMax m_climber;
    private Latcher m_latcher;
    private StringPot stringPot;
    private WaitCommand waitCommand;

    private final double maxDist = 68.58; //27 inches, max extension of climber
    public final double minDist = 5.00;
    private final double c_speed = 0.25;
    public final int SECONDS = 3;

    public Climber() {
        m_climber = new CANSparkMax(RobotMap.CLIMBER_ID, MotorType.kBrushless);
        m_climber.restoreFactoryDefaults();

        m_latcher = new Latcher();
        stringPot = new StringPot();
        waitCommand = new WaitCommand(SECONDS);

    }

    public void climbPeriodic(){
        if (stringPot.getDist() >= maxDist){

            end(); latch();
            waitCommand.initialize();
            retract();
            
            while (stringPot.getDist() > minDist) retract();
            end();
            
        }

    }

    public void climb() {
        m_climber.set(c_speed);
    }

    public void retract(){
       m_climber.set(-c_speed); 
    }

    public void end() {
        m_climber.set(0);
    }

    public void latch(){
        m_latcher.latch();
    }

}
