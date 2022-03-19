package org.team1515.botifypremium.Subsystems;

import org.team1515.botifypremium.RobotMap;
import org.team1515.botifypremium.Utils.StringPot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Climber extends SubsystemBase {

    private TalonFX m_climber;
    private RelativeEncoder m_encoder;
    // public StringPot stringPot;
    public SparkMaxPIDController m_pidController;

    private int climberID;
    // private int stringPotID;
    private int direction;

    // public final double maxDist = 68.58; //27 inches, max extension of climber
    // public final double minDist = 5.00;
    private final double c_speed = 0.25;
    public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, allowedErr;
    private double setPoint = 0;
    private double processVariable = 0;

    public Climber(int climberID, int stringPotID, int direction) {
        this.climberID = climberID;
        // this.stringPotID = stringPotID;
        this.direction = direction;

        m_climber = new TalonFX(climberID);
        m_climber.configFactoryDefault();
        m_climber.setNeutralMode(NeutralMode.Brake);

        m_climber.configNeutralDeadband(0.001);
        m_climber.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor,
                CPIDMap.kPIDLoopIdx,
                CPIDMap.kTimeoutMs);
   
        // stringPot = new StringPot(stringPotID);
        // reset();

        m_climber.setSelectedSensorPosition(0);

    }

    // public void reset(){
    //     while (stringPot.getDist() > minDist){
    //         m_climber.set(-c_speed * direction);
    //     }
    // }

    public void expand() {
        setPoint += 1.2;
    }

    public void retract() {
        setPoint -= 1.2;
    }

    public void climberPeriodic() {
        m_climber.set(ControlMode.Position, setPoint * direction);
        processVariable = m_encoder.getPosition(); 
    }

    public double getPosition() {
        return processVariable;
    }

    public void setPID(){
        m_climber.configNominalOutputForward(0, CPIDMap.kTimeoutMs);
        m_climber.configNominalOutputReverse(0, CPIDMap.kTimeoutMs);
        m_climber.configPeakOutputForward(1, CPIDMap.kTimeoutMs);
        m_climber.configPeakOutputReverse(-1, CPIDMap.kTimeoutMs);

        m_climber.config_kF(CPIDMap.kPIDLoopIdx, CPIDMap.kF, CPIDMap.kTimeoutMs);
        m_climber.config_kP(CPIDMap.kPIDLoopIdx, CPIDMap.kP, CPIDMap.kTimeoutMs);
        m_climber.config_kI(CPIDMap.kPIDLoopIdx, CPIDMap.kI, CPIDMap.kTimeoutMs);
        m_climber.config_kD(CPIDMap.kPIDLoopIdx, CPIDMap.kD, CPIDMap.kTimeoutMs);
    }
}

class CPIDMap {
    public static double kP = 0.06;
    public static double kI = 0.0003;
    public static double kD = 0;
    public static double kF = 0;
    public static double iZone = 0;
    public static double peakOutput = 1.00;

    public static final int kPIDLoopIdx = 0;
    public static final int kTimeoutMs = 30;
    public static boolean kSensorPhase = true;
}
