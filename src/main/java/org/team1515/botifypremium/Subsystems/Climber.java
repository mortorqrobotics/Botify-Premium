package org.team1515.botifypremium.Subsystems;

import org.team1515.botifypremium.RobotMap;
import org.team1515.botifypremium.Utils.StringPot;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Climber extends SubsystemBase {

    private CANSparkMax m_climberR;
    private CANSparkMax m_climberL;
    public StringPot stringPot;

    public final double maxDist = 68.58; //27 inches, max extension of climber
    public final double minDist = 5.00;
    private final double c_speed = 0.25;

    public Climber() {
        m_climberR = new CANSparkMax(RobotMap.RIGHTCLIMBER_ID, MotorType.kBrushless);
        m_climberR.restoreFactoryDefaults();
        m_climberL = new CANSparkMax(RobotMap.LEFTCLIMBER_ID, MotorType.kBrushless);
        m_climberL.restoreFactoryDefaults();

        stringPot = new StringPot();
    }

    public void climb() {
        m_climberR.set(c_speed);
        m_climberL.set(c_speed);
    }

    public void retract(){
        m_climberR.set(-c_speed);
        m_climberL.set(-c_speed);    }

    public void end() {
        m_climberR.set(0);
        m_climberL.set(0);    }
}
