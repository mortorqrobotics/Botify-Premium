package org.team1515.botifypremium.Subsystems;

import org.team1515.botifypremium.RobotMap;
import org.team1515.botifypremium.Utils.StringPot;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Climber extends SubsystemBase {

    private CANSparkMax m_climber;
    private Latcher m_latcher;
    private StringPot stringPot;

    private final int maxDist = 25;
    private final double c_speed = 0.25;

    public Climber() {
        m_climber = new CANSparkMax(RobotMap.CLIMBER_ID, MotorType.kBrushless);
        m_climber.restoreFactoryDefaults();

        m_latcher = new Latcher();
        stringPot = new StringPot();

    }

    public void climb() {
        m_climber.set(c_speed);

        if (stringPot.getDist() >= maxDist){
            end();
        }
    }

    public void end() {
        m_climber.set(0);
        m_latcher.latch();
    }

}
