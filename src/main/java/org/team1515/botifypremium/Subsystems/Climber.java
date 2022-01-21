package org.team1515.botifypremium.Subsystems;

import org.team1515.botifypremium.RobotMap;

import edu.wpi.first.wpilibj.Servo;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Climber {

    private CANSparkMax m_climber;

    private final double c_speed = 0.25;

    public Climber() {
        m_climber = new CANSparkMax(RobotMap.CLIMBER_ID, MotorType.kBrushless);
        m_climber.restoreFactoryDefaults();

    }

    public void climb() {
        m_climber.set(c_speed);
    }

    public void retract() {
        m_climber.set(-c_speed);
    }

    public void stop(){
        m_climber.set(0);
    }

}
