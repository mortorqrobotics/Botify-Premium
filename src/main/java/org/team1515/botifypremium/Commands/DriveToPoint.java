package org.team1515.botifypremium.Commands;

import org.team1515.botifypremium.Subsystems.Drivetrain;
import org.team1515.botifypremium.Subsystems.Odometry;
import org.team1515.botifypremium.Utils.Utilities;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveToPoint extends CommandBase {
    private Drivetrain drivetrain;
    private Odometry odometry;
    private Pose2d pointPose;

    private static double kP = 0.5;
    private static double deadband = 0.2;

    public DriveToPoint(Drivetrain drivetrain, Pose2d pointPose) {
        this.drivetrain = drivetrain;
        this.pointPose = pointPose;
        this.odometry = drivetrain.m_odometry;

        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        double distanceToTarget = odometry.distanceToObject(pointPose);
        Rotation2d angleToTarget = odometry.angleToObject(pointPose);

        double speed = distanceToTarget * kP;
        SwerveModuleState frontLeftState = new SwerveModuleState(speed, angleToTarget);
        SwerveModuleState frontRightState = new SwerveModuleState(speed, angleToTarget);
        SwerveModuleState backLeftState = new SwerveModuleState(speed, angleToTarget);
        SwerveModuleState backRightState = new SwerveModuleState(speed, angleToTarget);

        ChassisSpeeds chassisSpeeds = drivetrain.m_kinematics.toChassisSpeeds(frontLeftState, frontRightState, backLeftState, backRightState);
        drivetrain.drive(chassisSpeeds);
    }

    @Override
    public boolean isFinished() {
        return (Utilities.deadband(odometry.getPose().getX() - pointPose.getX(), deadband) == 0 &&
                Utilities.deadband(odometry.getPose().getY() - pointPose.getY(), deadband) == 0);
    }
}
