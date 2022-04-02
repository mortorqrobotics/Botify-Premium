package org.team1515.botifypremium.Subsystems;

import org.team1515.botifypremium.Robot;
import org.team1515.botifypremium.RobotMap;
import org.team1515.botifypremium.Utils.ShooterDist;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

import java.util.function.DoubleFunction;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;

public class Shooter extends SubsystemBase {
    private TalonFX m_shoot;

    public double speed = 9600;

    public static DoubleFunction<Double> firstEquation = (distance) -> -11.1*distance + 10333; // [93, 111) in inches
    public static DoubleFunction<Double> secondEquation = (distance) -> 13.6*distance + 7586; // [111, 177] in inches

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

    public void updateSpeed() {
        double distance = Robot.limelight.getDistance();
        speed = calcSpeed(distance);
    }

    public void shoot() {
        double distance = Robot.limelight.getDistance();
        // double speed = calcSpeed(distance);

        // // u_speed converts from RPM to raw falcon sensor units
        // double u_speed = speed * (RobotMap.FALCON_SENSOR_UNITS / 600.0);
        // m_shoot.set(ControlMode.Velocity, u_speed);
        SmartDashboard.putNumber("shooter velocity", m_shoot.getSelectedSensorVelocity());

        // double speed = calcSpeed(distance);
        m_shoot.set(ControlMode.Velocity, speed);
    }

    public void shoot(double speed) {
        m_shoot.set(ControlMode.Velocity, speed);
    }

    /**
     * @param distance Distance from robot to target
     * @return double Gives approximate ideal distance based on the distance from the target
     */
    private double calcSpeed(double distance) {
        double speed = 0;
        if(distance < 111) {
            speed = firstEquation.apply(distance);
        }
        else {
            speed = secondEquation.apply(distance);
        }

        speed = MathUtil.clamp(speed, 9000, 10000);
        return speed;
    }

    public void end() {
        m_shoot.set(ControlMode.PercentOutput, 0.0);
    }
}

class PIDMap {
    public static double kP = 0.27;
    public static double kI = 0;
    public static double kD = 0;
    public static double kF = 0.0506; // Use exact values later
    public static double iZone = 0;
    public static double peakOutput = 1.00;

    public static final int kPIDLoopIdx = 0;
    public static final int kTimeoutMs = 30;
    public static boolean kSensorPhase = true;
}