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

    /**
     * @return pose of the robot (includes x y position and rotation)
     */
    public Pose2d getPose() {
        return m_pose;
    }

    /**
     * @param objectPose location of the target objcet
     * @return angle to object from robot position
     */
    public Rotation2d angleToObject(Pose2d objectPose) {
        double deltaX = m_pose.getX() - objectPose.getX();
        double deltaY = m_pose.getY() - objectPose.getY();
    
        return new Rotation2d(getRadians(deltaX, deltaY));
    }

    /**
     * @param x coordinate
     * @param y coordinate
     * @return returns angle to coordinate pair between 0 and 2 pi
     */
    private double getRadians(double x, double y) {
        return (Math.atan2(y, x) + 2*Math.PI) % (2*Math.PI);
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
}
