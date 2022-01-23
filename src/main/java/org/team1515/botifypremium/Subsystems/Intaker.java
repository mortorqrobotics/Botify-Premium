package org.team1515.botifypremium.Subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.team1515.botifypremium.RobotMap;

public class Intaker {
    private CANSparkMax m_intake;

    public static final double speed = 0.5;

    public Intaker() {
        m_intake = new CANSparkMax(RobotMap.INTAKE_ID, MotorType.kBrushless);
        m_intake.restoreFactoryDefaults();
    }

    public void startIntake() {
        m_intake.set(speed);
    }

    public void startOuttake() {
        m_intake.set(-speed);
    }

    public void end() {
        m_intake.set(0);
    }
}
