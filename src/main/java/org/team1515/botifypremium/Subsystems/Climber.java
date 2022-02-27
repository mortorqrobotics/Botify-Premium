package org.team1515.botifypremium.Subsystems;

import org.team1515.botifypremium.RobotMap;
import org.team1515.botifypremium.Utils.StringPot;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Climber extends SubsystemBase {

    private CANSparkMax m_climber;
    public StringPot stringPot;

    private int climberID;

    public final double maxDist = 68.58; //27 inches, max extension of climber
    public final double minDist = 5.00;
    private final double c_speed = 0.25;

    public Climber(int climberID) {
        this.climberID = climberID;

        m_climber = new CANSparkMax(climberID, MotorType.kBrushless);
        m_climber.restoreFactoryDefaults();

        m_climber.setIdleMode(IdleMode.kBrake);
   
        stringPot = new StringPot();
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
}
