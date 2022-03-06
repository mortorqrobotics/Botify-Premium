package org.team1515.botifypremium.Subsystems;

import org.team1515.botifypremium.RobotMap;
import org.team1515.botifypremium.Utils.StringPot;

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

    public final double maxDist = 68.58; //27 inches, max extension of climber
    public final double minDist = 5.00;
    private final double c_speed = 0.25;
    public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, allowedErr;
    private double setPoint, processVariable;

    public Climber(int climberID, int stringPotID) {
        this.climberID = climberID;
        this.stringPotID = stringPotID;

        m_climber = new CANSparkMax(climberID, MotorType.kBrushless);
        m_climber.restoreFactoryDefaults();
        m_climber.setIdleMode(IdleMode.kBrake);

        m_encoder = m_climber.getEncoder();
   
        stringPot = new StringPot(stringPotID);

        reset();

        m_encoder.setPosition(0);

        kP = 0; 
        kI = 0;
        kD = 0; 
        kIz = 0; 
        kFF = 0; 
        kMaxOutput = 1; 
        kMinOutput = -1;

        setPID();
    }
    public void reset(){
        while (stringPot.getDist() > minDist){
        m_climber.set(-c_speed);
        }
    }

    public void expand() {
        m_pidController.setReference(setPoint, CANSparkMax.ControlType.kSmartMotion);
        processVariable = m_encoder.getPosition();    
    }

    public void retract(){
        m_pidController.setReference(setPoint, CANSparkMax.ControlType.kSmartMotion);
        processVariable = m_encoder.getPosition();    
    }

    public void end() {
        m_climber.set(0);
    }

    public void setPID(){
        m_pidController.setP(kP);
        m_pidController.setI(kI);
        m_pidController.setD(kD);
        m_pidController.setIZone(kIz);
        m_pidController.setFF(kFF);
        m_pidController.setOutputRange(kMinOutput, kMaxOutput);
    }

    public double getPosition(){
        return processVariable;
    }
}
