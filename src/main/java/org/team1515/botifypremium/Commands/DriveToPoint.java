package org.team1515.botifypremium.Commands;

import org.team1515.botifypremium.OI;
import org.team1515.botifypremium.Subsystems.Drivetrain;
import org.team1515.botifypremium.Subsystems.Odometry;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveToPoint extends CommandBase {
    private Drivetrain drivetrain;
    private Odometry odometry;
    private Pose2d pointPose;
    private PIDController positionController;

    private double maxSpeed = 0.25 * Drivetrain.MAX_VELOCITY_METERS_PER_SECOND;

    public DriveToPoint(Drivetrain drivetrain, Pose2d pointPose) {
        this.drivetrain = drivetrain;
        this.pointPose = pointPose;
        this.odometry = drivetrain.m_odometry;

        positionController = new PIDController(0.5, 0.1, 0);
        positionController.setTolerance(0.05);
        positionController.setSetpoint(0.0);

        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        double distanceToTarget = odometry.distanceToObject(pointPose);
        double angleToTarget = odometry.angleToObject(pointPose).getRadians();

        double speedOffset = positionController.calculate(distanceToTarget, 0.0);
        speedOffset = MathUtil.clamp(speedOffset, -maxSpeed, maxSpeed);

        double ySpeed = Math.sin(angleToTarget) * speedOffset;
        double xSpeed = Math.cos(angleToTarget) * speedOffset;

        ChassisSpeeds speeds = ChassisSpeeds.fromFieldRelativeSpeeds(
                ySpeed,
                xSpeed,
                0.0,
                OI.gyro.getGyroscopeRotation());
        drivetrain.drive(speeds);
    }

    @Override
    public boolean isFinished() {
        return positionController.atSetpoint();
    }
}
