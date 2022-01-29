package org.team1515.botifypremium.Subsystems;

import org.team1515.botifypremium.Robot;
import org.team1515.botifypremium.RobotMap;
import org.team1515.botifypremium.Utils.ShooterDist;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;

public class Magazine extends SubsystemBase{
    private TalonFX m_mag;
    private final double speed = 0.5;

    public Magazine() {
        m_mag = new TalonFX(RobotMap.MAG_ID);
        m_mag.configFactoryDefault();
        m_mag.configNeutralDeadband(0.001);
    }

    public void goUp() {
        m_mag.set(ControlMode.PercentOutput, speed);
    }

    public void goDown() {
        m_mag.set(ControlMode.PercentOutput, -speed);
    }

    public void end() {
        m_mag.set(ControlMode.PercentOutput, 0.0);
    }
}