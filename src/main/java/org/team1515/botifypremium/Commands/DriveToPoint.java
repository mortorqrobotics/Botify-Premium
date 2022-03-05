package org.team1515.botifypremium.Commands;

import org.team1515.botifypremium.OI;
import org.team1515.botifypremium.Subsystems.Drivetrain;
import org.team1515.botifypremium.Subsystems.Odometry;
import org.team1515.botifypremium.Utils.Utilities;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveToPoint extends CommandBase {
    private Drivetrain drivetrain;
    private Odometry odometry;
    private Pose2d pointPose;

    private double maxSpeed = 0.2 * Drivetrain.MAX_VELOCITY_METERS_PER_SECOND;
    private double deadband = 0.5;

    public DriveToPoint(Drivetrain drivetrain, Pose2d pointPose) {
        this.drivetrain = drivetrain;
        this.pointPose = pointPose;
        this.odometry = drivetrain.m_odometry;

        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        double angleToTarget = odometry.angleToObject(pointPose).getRadians();

        System.out.println(angleToTarget);

        double ySpeed = Math.sin(angleToTarget) * maxSpeed;
        double xSpeed = Math.cos(angleToTarget) * maxSpeed;

        // System.out.println(Utilities.deadband(odometry.getPose().getX() - pointPose.getX(), deadband));
        // System.out.println(Utilities.deadband(odometry.getPose().getY() - pointPose.getY(), deadband));

        ChassisSpeeds speeds = ChassisSpeeds.fromFieldRelativeSpeeds(
                ySpeed,
                xSpeed,
                0.0,
                OI.gyro.getGyroscopeRotation());
        drivetrain.drive(speeds);
    }

    @Override
    public boolean isFinished() {
        return Utilities.deadband(odometry.getPose().getX() - pointPose.getX(), deadband) == 0 
                && Utilities.deadband(odometry.getPose().getY() - pointPose.getY(), deadband) == 0;     
    }
}
