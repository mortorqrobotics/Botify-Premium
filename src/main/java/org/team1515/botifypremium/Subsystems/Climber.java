package org.team1515.botifypremium.Subsystems;

import org.team1515.botifypremium.RobotMap;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Climber extends SubsystemBase {

    private CANSparkMax m_climber;
    private Latcher m_latcher;


    private final double c_speed = 0.25;

    public Climber() {
        m_climber = new CANSparkMax(RobotMap.CLIMBER_ID, MotorType.kBrushless);
        m_climber.restoreFactoryDefaults();

        m_latcher = new Latcher();

    }

    public void climb() {
        m_climber.set(c_speed);
    }

    public void end() {
        m_climber.set(0);
        m_latcher.latch();
    }

}
