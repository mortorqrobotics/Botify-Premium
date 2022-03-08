package org.team1515.botifypremium.Commands;

import org.team1515.botifypremium.Robot;
import org.team1515.botifypremium.Subsystems.Drivetrain;
import org.team1515.botifypremium.Utils.Limelight;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.kinematics.ChassisSpeeds;

public class AutoAlign extends CommandBase {
    private Drivetrain m_drivetrainSubsystem;
    private Limelight m_limelight;
    private PIDController angleController;
    private double maxSpeed = 0.75 * Drivetrain.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND;

    public AutoAlign(Drivetrain drivetrainSubsystem, Limelight limelight) {
        this.m_drivetrainSubsystem = drivetrainSubsystem;
        this.m_limelight = limelight;

        angleController = new PIDController(5, 8, 0);
        angleController.setTolerance(0.01);
        angleController.enableContinuousInput(-Math.PI, Math.PI);
        angleController.setSetpoint(0.0);

        addRequirements(m_drivetrainSubsystem);
    }

    @Override
    public void execute() {
        double error = Math.toRadians(Robot.limelight.getTX());
        // System.out.println(error);
        if (error == 0)
            this.end(true);
        double speed = MathUtil.clamp(angleController.calculate(error, 0.0), -maxSpeed, maxSpeed);

        ChassisSpeeds speeds = new ChassisSpeeds(0.0, 0.0, speed);
        m_drivetrainSubsystem.drive(speeds);
    }

    @Override
    public boolean isFinished() {
        return angleController.atSetpoint();
    }
}
