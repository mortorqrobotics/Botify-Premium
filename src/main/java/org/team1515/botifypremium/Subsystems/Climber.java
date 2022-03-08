package org.team1515.botifypremium.Subsystems;

import org.team1515.botifypremium.RobotMap;
import org.team1515.botifypremium.Utils.StringPot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Climber extends SubsystemBase {

    private CANSparkMax m_climber;
    private RelativeEncoder m_encoder;
    public StringPot stringPot;
    public SparkMaxPIDController m_pidController;

    private int climberID;
    private int stringPotID;
    private int direction;

    public final double maxDist = 68.58; //27 inches, max extension of climber
    public final double minDist = 5.00;
    private final double c_speed = 0.25;
    public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, allowedErr;
    private double setPoint = 0;
    private double processVariable = 0;

    public Climber(int climberID, int stringPotID, int direction) {
        this.climberID = climberID;
        this.stringPotID = stringPotID;
        this.direction = direction;

        m_climber = new CANSparkMax(climberID, MotorType.kBrushless);
        m_climber.restoreFactoryDefaults();
        m_climber.setIdleMode(IdleMode.kBrake);

        m_encoder = m_climber.getEncoder();
        m_pidController = m_climber.getPIDController();
   
        // stringPot = new StringPot(stringPotID);
        // reset();

        m_encoder.setPosition(0);

        this.kP = 0.06; 
        this.kI = 0.0003;
        this.kD = 0; 
        this.kIz = 0; 
        this.kFF = 0; 
        this.kMaxOutput = 1; 
        this.kMinOutput = -1;

        setPID();
    }

    // public void reset(){
    //     while (stringPot.getDist() > minDist){
    //         m_climber.set(-c_speed * direction);
    //     }
    // }

    public void expand() {
        setPoint += 0.2;
    }

    public void retract() {
        setPoint -= 0.2;
    }

    public void climberPeriodic() {
        m_pidController.setReference(setPoint * direction, CANSparkMax.ControlType.kPosition);
        processVariable = m_encoder.getPosition(); 
    }

    public void setPID() {
        m_pidController.setP(this.kP);
        m_pidController.setI(this.kI);
        m_pidController.setD(this.kD);
        m_pidController.setIZone(this.kIz);
        m_pidController.setFF(this.kFF);
        m_pidController.setOutputRange(this.kMinOutput, this.kMaxOutput);
    }

    public double getPosition() {
        return processVariable;
    }
}
