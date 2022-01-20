package org.team1515.botifypremium.Subsystems;

import org.team1515.botifypremium.RobotMap;

import edu.wpi.first.wpilibj.Servo;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Climber {

    private CANSparkMax m_climber;
    private static Servo latcher;

    private final double c_speed = 0.25;
    private final double closePos = 0.5;
    private final double startPos = 0.0;

    public Climber() {

        m_climber = new CANSparkMax(RobotMap.CLIMBER_ID, MotorType.kBrushless);
        m_climber.restoreFactoryDefaults();

        latcher.set(startPos);

    }

    public void climb() {
        m_climber.set(c_speed);
    }

    public void retract() {
        m_climber.set(-c_speed);
    }

    public void latch() {
        latcher.set(closePos);
    }

    public void unlatch() {
        latcher.set(startPos);
    }

}
