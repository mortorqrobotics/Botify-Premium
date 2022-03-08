package org.team1515.botifypremium.Commands;

import org.team1515.botifypremium.OI;
import org.team1515.botifypremium.Subsystems.Drivetrain;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveDist extends CommandBase {
    private Drivetrain m_drivetrainSubsystem;
    private double targetDist;
    private double angle;
    private double lastTime;

    private double distTraveled = 0.0;
    private double maxSpeed = 0.2 * Drivetrain.MAX_VELOCITY_METERS_PER_SECOND;

    public DriveDist(Drivetrain drivetrainSubsystem, double targetDist, double angle) {
        this.m_drivetrainSubsystem = drivetrainSubsystem;
        this.targetDist = targetDist;
        this.angle = Math.toRadians(angle);

        SmartDashboard.putNumber("target dist", targetDist);
        addRequirements(drivetrainSubsystem);
    }

    @Override
    public void initialize() {
        this.lastTime = System.currentTimeMillis();
        this.distTraveled = 0.0;
    }

    @Override
    public void execute() {
        double ySpeed = Math.sin(angle) * maxSpeed;
        double xSpeed = Math.cos(angle) * maxSpeed;

        distanceTraveled();
        // ChassisSpeeds speeds = ChassisSpeeds.fromFieldRelativeSpeeds(
        //         ySpeed,
        //         xSpeed,
        //         0.0,
        //         OI.gyro.getGyroscopeRotation());
        ChassisSpeeds speeds = new ChassisSpeeds(
                maxSpeed,
                0.0,
                0.0
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
