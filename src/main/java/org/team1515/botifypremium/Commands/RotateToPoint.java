package org.team1515.botifypremium.Commands;

import org.team1515.botifypremium.Subsystems.Drivetrain;
import org.team1515.botifypremium.Subsystems.Odometry;
import org.team1515.botifypremium.Utils.Utilities;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RotateToPoint extends CommandBase {
    private Drivetrain drivetrain;
    private Pose2d targetPose;
    private Odometry odometry;
    private PIDController angleController;

    private double maxSpeed = 0.25 * Drivetrain.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND;

    public RotateToPoint(Drivetrain drivetrain, Pose2d targetPose) {
        this.drivetrain = drivetrain;
        this.targetPose = targetPose;
        this.odometry = drivetrain.m_odometry;

        angleController = new PIDController(3, 5, 0);
        angleController.setTolerance(0.05);
        angleController.enableContinuousInput(-Math.PI, Math.PI);
        angleController.setSetpoint(0.0);

        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        double angleOffset = odometry.angleToObject(targetPose).minus(odometry.getPose().getRotation()).getRadians();
        double speed = MathUtil.clamp(angleController.calculate(angleOffset, 0.0), -maxSpeed, maxSpeed);

        ChassisSpeeds speeds = new ChassisSpeeds(0.0, 0.0, speed);
        drivetrain.drive(speeds);
    }

    @Override
    public boolean isFinished() {
        return angleController.atSetpoint();
    }
}
