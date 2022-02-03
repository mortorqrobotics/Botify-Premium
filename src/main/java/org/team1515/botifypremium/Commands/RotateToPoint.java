package org.team1515.botifypremium.Commands;

import org.team1515.botifypremium.Subsystems.Drivetrain;
import org.team1515.botifypremium.Subsystems.Odometry;
import org.team1515.botifypremium.Utils.Utilities;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RotateToPoint extends CommandBase {
    private Drivetrain drivetrain;
    private Pose2d targetPose;
    private Odometry odometry;

    private static double kP = 0.0;
    private static double deadband = 0.002;

    public RotateToPoint(Drivetrain drivetrain, Pose2d targetPose) {
        this.drivetrain = drivetrain;
        this.targetPose = targetPose;
        this.odometry = drivetrain.m_odometry;
        
        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        Rotation2d angleOffset = odometry.angleToObject(targetPose).minus(odometry.getPose().getRotation());
        double angleSpeed = angleOffset.getRadians() * kP;
        ChassisSpeeds speeds = new ChassisSpeeds(0.0, 0.0, angleSpeed);
        drivetrain.drive(speeds);
    }

    @Override
    public boolean isFinished() {
        double error = odometry.angleToObject(targetPose) - odometry.getPose().getRotation();
        return Utilities.deadband(error, deadband) == 0.0; 
    }
}
