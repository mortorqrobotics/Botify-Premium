package org.team1515.botifypremium.Commands;

import org.team1515.botifypremium.OI;
import org.team1515.botifypremium.Subsystems.Drivetrain;
import org.team1515.botifypremium.Utils.Utilities;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveDist extends CommandBase {
    private Drivetrain m_drivetrainSubsystem;
    private double targetDist;
    private double direction;
    private double lastTime;

    private double distTraveled = 0.0;
    private double maxSpeed;
    private Rotation2d startGyroAngle;

    public DriveDist(Drivetrain drivetrainSubsystem, double targetDist, double speed) {
        this.m_drivetrainSubsystem = drivetrainSubsystem;
        this.targetDist = targetDist;
        this.direction = 1;
        this.maxSpeed = speed * Drivetrain.MAX_VELOCITY_METERS_PER_SECOND;

        SmartDashboard.putNumber("target dist", targetDist);
        addRequirements(drivetrainSubsystem);
    }

    public DriveDist(Drivetrain drivetrainSubsystem, double targetDist, double direction, double speed) {
        this.m_drivetrainSubsystem = drivetrainSubsystem;
        this.targetDist = targetDist;
        this.direction = direction;
        this.maxSpeed = speed * Drivetrain.MAX_VELOCITY_METERS_PER_SECOND;


        SmartDashboard.putNumber("target dist", targetDist);
        addRequirements(drivetrainSubsystem);
    }

    @Override
    public void initialize() {
        this.lastTime = System.currentTimeMillis();
        this.distTraveled = 0.0;
        this.startGyroAngle = OI.gyro.getGyroscopeRotation();
    }

    @Override
    public void execute() {
        distanceTraveled();
        ChassisSpeeds speeds = new ChassisSpeeds(
                maxSpeed * direction,
                0.0,
                Utilities.deadband(startGyroAngle.minus(OI.gyro.getGyroscopeRotation()).getRadians(), 0.01) // Use gyro to prevent robot from turning (doesn't really work)
        );
        m_drivetrainSubsystem.drive(speeds);
    }

    @Override
    public void end(boolean interrupted) {
        m_drivetrainSubsystem.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
    }

    /**
     * Gets the distance traveled by multiplying rate (drive velocity) and time
     * (milliseconds)
     */
    public void distanceTraveled() {
        double deltaTime = System.currentTimeMillis() - lastTime;
        distTraveled += Math.abs(m_drivetrainSubsystem.m_frontLeftModule.getDriveVelocity()) * (1.0 / 1000) * deltaTime;
        lastTime = System.currentTimeMillis();
    }

    @Override
    public boolean isFinished() {
        return distTraveled >= targetDist;
    }
}
