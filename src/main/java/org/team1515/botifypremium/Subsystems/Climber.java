package org.team1515.botifypremium.Subsystems;

import org.team1515.botifypremium.RobotMap;
import org.team1515.botifypremium.Utils.StringPot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
<<<<<<< Updated upstream
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
=======
>>>>>>> Stashed changes

public class Climber extends SubsystemBase {

    private TalonFX m_climber;
    public StringPot stringPot;

    private int stringPotID;
    private int direction;

    public final double maxDist = 68.58; //27 inches, max extension of climber
    public final double minDist = 5.00;
    // private final double c_speed = 0.25;
    private double setPoint = 0;

    public static double kP = 0.06;
    public static double kI = 0.0003;
    public static double kD = 0;
    public static double kF = 0;
    public static double iZone = 0;
    public static double peakOutput = 1.00;

    public static final int kPIDLoopIdx = 0;
    public static final int kTimeoutMs = 30;
    public static boolean kSensorPhase = true;

    public Climber(int climberID, int stringPotID, int direction) {
        this.stringPotID = stringPotID;
        this.direction = direction;

        m_climber = new TalonFX(climberID);
        m_climber.setSelectedSensorPosition(0.0);
           
        // stringPot = new StringPot(stringPotID);
        // reset();
        setPID();
    }

    // public void reset(){
    //     while (stringPot.getDist() > minDist){
    //         m_climber.set(-c_speed * direction);
    //     }
    // }

    public void expand() {
        // setPoint += 1.2 * 10;
        m_climber.set(ControlMode.PercentOutput, 0.5 * direction);

    }

    public void retract() {
        // setPoint -= 1.2 * 10;
        m_climber.set(ControlMode.PercentOutput, -0.5 * direction);
    }

    public void climberPeriodic() {
        // m_climber.set(ControlMode.Position, setPoint * direction);
    }

    public void end() {
        m_climber.set(ControlMode.PercentOutput, 0.0);
    }

    public void setPID() {
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

        // m_climber.config_kF(kPIDLoopIdx, kF, kTimeoutMs);
        // m_climber.config_kP(kPIDLoopIdx, kP, kTimeoutMs);
        // m_climber.config_kI(kPIDLoopIdx, kI, kTimeoutMs);
        // m_climber.config_kD(kPIDLoopIdx, kD, kTimeoutMs);
    }

    public double getPosition() {
        return m_climber.getSelectedSensorPosition();
    }
}
