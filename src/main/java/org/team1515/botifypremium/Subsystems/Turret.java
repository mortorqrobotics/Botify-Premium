package org.team1515.botifypremium.Subsystems;

import org.team1515.botifypremium.RobotMap;
import org.team1515.botifypremium.Utils.Utilities;
import org.team1515.botifypremium.OI;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Turret {
    private CANSparkMax m_turret;
    private SparkMaxPIDController m_pidController;
    private XboxController m_joystick = OI.mainStick;

    // private final double kP = 0.1; 
    // private final double kI = 1e-4;
    // private final double kD = 1; 
    // private final double kIz = 0; 
    // private final double kFF = 0; 
    // private final double kMaxOutput = 1; 
    // private final double kMinOutput = -1;

    public Turret() {
        m_turret = new CANSparkMax(RobotMap.TURRET_ID, MotorType.kBrushless);
        m_turret.restoreFactoryDefaults();

        // m_pidController = m_turret.getPIDController();
        // m_pidController.setP(kP);
        // m_pidController.setI(kI);
        // m_pidController.setD(kD);
        // m_pidController.setIZone(kIz);
        // m_pidController.setFF(kFF);
        // m_pidController.setOutputRange(kMinOutput, kMaxOutput);
    }

    public void turretPeriodic() {
        m_turret.set(Utilities.deadband(m_joystick.getX()));
    }
}
