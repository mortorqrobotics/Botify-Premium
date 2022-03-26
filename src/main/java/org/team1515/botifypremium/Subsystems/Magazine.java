package org.team1515.botifypremium.Subsystems;
 
import org.team1515.botifypremium.RobotMap;
// import org.team1515.botifypremium.Utils.UltraSensor;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
 
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
 
public class Magazine extends SubsystemBase{
    private CANSparkMax m_mag;
    private final double speed = 0.5; // TODO increase this speed
 
    public Magazine() {
        m_mag = new CANSparkMax(RobotMap.MAG_ID, MotorType.kBrushless);
        m_mag.restoreFactoryDefaults();
    }

    public void goUp() {
        m_mag.set(speed);
    }
 
    public void goDown() {
        m_mag.set(-speed);
    }
 
    public void end() {
        m_mag.set(0.0);
    }
}