package org.team1515.botifypremium.Subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class Climber extends SubsystemBase {

    private TalonFX m_climber;

    private int direction;

    public final double maxDist = 68.58; //27 inches, max extension of climber
    public final double minDist = 5.00;
    private final double c_speed = 0.85;

    public static double kP = 0.06;
    public static double kI = 0.0003;
    public static double kD = 0;
    public static double kF = 0;
    public static double iZone = 0;
    public static double peakOutput = 1.00;

    public static final int kPIDLoopIdx = 0;
    public static final int kTimeoutMs = 30;
    public static boolean kSensorPhase = true;

    public Climber(int climberID, int direction) {
        this.direction = direction; // Motor on one side is facing the opposite direction, needs to spin the other way to go up

        m_climber = new TalonFX(climberID);
        m_climber.setSelectedSensorPosition(0.0);
           
        configureMotor();
    }

    public void expand() {
        m_climber.set(ControlMode.PercentOutput, c_speed * direction);

    }

    public void retract() {
        m_climber.set(ControlMode.PercentOutput, -c_speed * direction);
    }

    public void end() {
        m_climber.set(ControlMode.PercentOutput, 0.0);
    }

    public void configureMotor() {
        m_climber.configFactoryDefault();
        m_climber.configNeutralDeadband(0.001);
        m_climber.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor,
                PIDMap.kPIDLoopIdx,
                PIDMap.kTimeoutMs);

        m_climber.setNeutralMode(NeutralMode.Brake);

        m_climber.configNominalOutputForward(0, kTimeoutMs);
        m_climber.configNominalOutputReverse(0, kTimeoutMs);
        m_climber.configPeakOutputForward(1, kTimeoutMs);
        m_climber.configPeakOutputReverse(-1, kTimeoutMs);
    }

    public double getPosition() {
        return m_climber.getSelectedSensorPosition();
    }
}
