package org.team1515.botifypremium.Subsystems;

import org.team1515.botifypremium.OI;
import org.team1515.botifypremium.Utils.Field;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Odometry {
    private SwerveDriveOdometry m_odometry;
    private Pose2d m_pose;
    public static double initalX = 0.0;
    public static double initalY = 0.0;
    private final Field2d m_field = new Field2d();
    public static Rotation2d initalRot = new Rotation2d(Math.PI / 2);

    public Odometry(SwerveDriveKinematics kinematics) {
        m_odometry = new SwerveDriveOdometry(kinematics, OI.gyro.getGyroscopeRotation(), Field.STARTING_POSE);
        SmartDashboard.putData("Field", m_field);
    }

    public void update(Rotation2d gyroRotation, SwerveModuleState frontLeftModule, SwerveModuleState frontRightModule, SwerveModuleState backLeftModule, SwerveModuleState backRightModule) {
        m_pose = m_odometry.update(gyroRotation, frontLeftModule, frontRightModule, backLeftModule, backRightModule);
        m_field.setRobotPose(m_odometry.getPoseMeters());
    }

    public void resetPosition(Pose2d robotPose, Rotation2d gyroRotation) {
        m_odometry.resetPosition(robotPose, gyroRotation);
    }

    /**
     * @return pose of the robot (includes x y position and rotation)
     */
    public Pose2d getPose() {
        return m_pose;
    }

    /**
     * @param objectPose location of the target object
     * @return angle to object from robot position
     */
    public Rotation2d angleToObject(Pose2d objectPose) {
        double deltaX = objectPose.getX() - m_pose.getX();
        double deltaY = objectPose.getY() - m_pose.getY();
    
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
