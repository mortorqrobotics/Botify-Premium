package org.team1515.botifypremium.Subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.team1515.botifypremium.RobotMap;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intaker extends SubsystemBase {
    private CANSparkMax m_intake;
    public static final double speed = 0.9;

    public Intaker() {
        m_intake = new CANSparkMax(RobotMap.INTAKE_ID, MotorType.kBrushed);
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
