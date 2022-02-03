package org.team1515.botifypremium.Subsystems;

import org.team1515.botifypremium.OI;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModuleState;

public class Odometry {
    private SwerveDriveOdometry m_odometry;
    private Pose2d m_pose;
    public static double initalX = 0.0;
    public static double initalY = 0.0;
    public static Rotation2d initalRot = new Rotation2d();

    public Odometry(SwerveDriveKinematics kinematics) {
        m_odometry = new SwerveDriveOdometry(kinematics, OI.gyro.getGyroscopeRotation(), new Pose2d(initalX, initalY, initalRot));
    }

    public void update(Rotation2d gyroRotation, SwerveModuleState frontLeftModule, SwerveModuleState frontRightModule, SwerveModuleState backLeftModule, SwerveModuleState backRightModule) {
        m_pose = m_odometry.update(gyroRotation, frontLeftModule, frontRightModule, backLeftModule, backRightModule);
    }

    public Pose2d getPose() {
        return m_pose;
    }

    public Rotation2d angleToObject(Pose2d objectPose) {
        double deltaX = Math.abs(m_pose.getX() - objectPose.getX());
        double deltaY = Math.abs(m_pose.getY() - objectPose.getY());
    
        return new Rotation2d(Math.atan(deltaY / deltaX));
    }

    /**
     * @param objectPose pose of the target object
     * @return double distance to the target object in <b>meters</b>
     */
    public double distanceToObject(Pose2d objectPose) {
        double deltaX = Math.abs(m_pose.getX() - objectPose.getX());
        double deltaY = Math.abs(m_pose.getY() - objectPose.getY());

        return Math.hypot(deltaX, deltaY);
    }

    public Rotation2d faceAngleToObject(Pose2d objectPose) {
        double angle = m_pose.getRotation().minus(objectPose.getRotation()).getRadians();
        angle = Math.copySign(angle % (2 * Math.PI), angle);
        return new Rotation2d(angle);
    }
}
