package org.team1515.botifypremium.Subsystems;

import org.team1515.botifypremium.Robot;
import org.team1515.botifypremium.RobotMap;
import org.team1515.botifypremium.Utils.ShooterDist;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;

public class Shooter {
    private TalonFX m_shoot;

    public Shooter() {
        m_shoot = new TalonFX(RobotMap.SHOOTER_ID);
        m_shoot.configFactoryDefault();
        m_shoot.configNeutralDeadband(0.001);
        m_shoot.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor,
                PIDMap.kPIDLoopIdx,
                PIDMap.kTimeoutMs);

        m_shoot.configNominalOutputForward(0, PIDMap.kTimeoutMs);
        m_shoot.configNominalOutputReverse(0, PIDMap.kTimeoutMs);
        m_shoot.configPeakOutputForward(1, PIDMap.kTimeoutMs);
        m_shoot.configPeakOutputReverse(-1, PIDMap.kTimeoutMs);

        m_shoot.config_kF(PIDMap.kPIDLoopIdx, PIDMap.kF, PIDMap.kTimeoutMs);
        m_shoot.config_kP(PIDMap.kPIDLoopIdx, PIDMap.kP, PIDMap.kTimeoutMs);
        m_shoot.config_kI(PIDMap.kPIDLoopIdx, PIDMap.kI, PIDMap.kTimeoutMs);
        m_shoot.config_kD(PIDMap.kPIDLoopIdx, PIDMap.kD, PIDMap.kTimeoutMs);
    }

    public void shoot() {
        double distance = Robot.limelight.getDistance();
        double speed = calcSpeed(distance);

        // u_speed converts from RPM to raw falcon sensor units
        double u_speed = speed * (4096.0 / 600.0);
        m_shoot.set(ControlMode.Velocity, u_speed);
    }

    /**
     * @param distance Distance from robot to target
     * @return double Gives approximate ideal distance based on the distance from the target
     */
    private double calcSpeed(double distance) {
        if (distance < 0 || distance > 12) {
            return 0.0;
        } else {
            // Distance between values
            return ShooterDist.map.floorEntry((int) Math.round(distance)).getValue();
        }
    }

    public void end() {
        m_shoot.set(ControlMode.PercentOutput, 0.0);
    }
}

class PIDMap {
    public static double kP = 0.1;
    public static double kI = 0.001;
    public static double kD = 5;
    public static double kF = 1023.0 / 20660.0;
    public static double iZone = 300;
    public static double peakOutput = 1.00;

    public static final int kPIDLoopIdx = 0;
    public static final int kTimeoutMs = 30;
    public static boolean kSensorPhase = true;
}