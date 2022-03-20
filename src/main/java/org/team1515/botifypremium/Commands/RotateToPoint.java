package org.team1515.botifypremium.Commands;

import org.team1515.botifypremium.Subsystems.Drivetrain;
import org.team1515.botifypremium.Subsystems.Odometry;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RotateToPoint extends CommandBase {
    private Drivetrain drivetrain;
    private Pose2d targetPose;
    private Odometry odometry;
    private PIDController angleController;

    private double maxSpeed = 0.25 * Drivetrain.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND;
    private double setPoint = Math.PI;
    private double deadband = 0.2;

    public RotateToPoint(Drivetrain drivetrain, Pose2d targetPose) {
        this.drivetrain = drivetrain;
        this.targetPose = targetPose;
        this.odometry = drivetrain.m_odometry;

        angleController = new PIDController(0.25, 0.8, 0);
        angleController.setTolerance(deadband);
        // angleController.enableContinuousInput(0, 2*Math.PI);
        angleController.setSetpoint(setPoint);

        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        double angleOffset = odometry.angleToObject(targetPose).minus(odometry.getPose().getRotation()).getRadians();
        double speed = MathUtil.clamp(angleController.calculate(angleOffset, setPoint), -maxSpeed, maxSpeed);
        SmartDashboard.putNumber("angle offset", Math.toDegrees(angleOffset));

        ChassisSpeeds speeds = new ChassisSpeeds(0.0, 0.0, speed);
        drivetrain.drive(speeds);
    }

    @Override
    public boolean isFinished() {
        return angleController.atSetpoint();
    }
}
