package org.team1515.botifypremium.Commands;

import org.team1515.botifypremium.Subsystems.Drivetrain;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveDist extends CommandBase {
    private Drivetrain m_drivetrainSubsystem;
    private double targetDist;
    private double angle;
    private double lastTime;

    private double distTraveled = 0.0;

    public DriveDist(Drivetrain drivetrainSubsystem, double targetDist, double angle) {
        this.m_drivetrainSubsystem = drivetrainSubsystem;
        this.targetDist = targetDist;
        this.angle = angle;

        addRequirements(drivetrainSubsystem);
    }

    @Override
    public void initialize() {
        this.lastTime = System.currentTimeMillis();
    }

    @Override
    public void execute() {
        double speed = 0.5 * Drivetrain.MAX_VELOCITY_METERS_PER_SECOND;
        SwerveModuleState frontLeftState = new SwerveModuleState(speed, Rotation2d.fromDegrees(angle));
        SwerveModuleState frontRightState = new SwerveModuleState(speed, Rotation2d.fromDegrees(angle));
        SwerveModuleState backLeftState = new SwerveModuleState(speed, Rotation2d.fromDegrees(angle));
        SwerveModuleState backRightState = new SwerveModuleState(speed, Rotation2d.fromDegrees(angle));

        distanceTraveled();
        ChassisSpeeds chassisSpeeds = m_drivetrainSubsystem.m_kinematics.toChassisSpeeds(frontLeftState, frontRightState, backLeftState, backRightState);
        m_drivetrainSubsystem.drive(chassisSpeeds);
    }

    /**
     * Gets the distance traveled by multiplying rate (drive velocity) and time (milliseconds)
     */
    public void distanceTraveled() {
        double deltaTime = System.currentTimeMillis() - lastTime;
        distTraveled += m_drivetrainSubsystem.m_frontLeftModule.getDriveVelocity() * (1/1000) * deltaTime;
        lastTime = System.currentTimeMillis();
    }

    @Override
    public boolean isFinished() {
        if(distTraveled >= targetDist) {
            return true;
        }
        return false;
    }
}
