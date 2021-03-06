
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.team1515.botifypremium.Subsystems;


import com.swervedrivespecialties.swervelib.Mk4ModuleConfiguration;
import com.swervedrivespecialties.swervelib.Mk4SwerveModuleHelper;
import com.swervedrivespecialties.swervelib.SdsModuleConfigurations;
import com.swervedrivespecialties.swervelib.SwerveModule;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import org.team1515.botifypremium.OI;
import org.team1515.botifypremium.RobotMap;

public class Drivetrain extends SubsystemBase {

  /**
   * The maximum voltage that will be delivered to the drive motors.
   * <p>
   * This can be reduced to cap the robot's maximum speed. Typically, this is useful during initial testing of the robot.
   */
  public static final double MAX_VOLTAGE = 12.0;

  //  The formula for calculating the theoretical maximum velocity is:
  //   <Motor free speed RPM> / 60 * <Drive reduction> * <Wheel diameter meters> * pi
  //  By default this value is setup for a Mk3 standard module using Falcon500s to drive.
  //  An example of this constant for a Mk4 L2 module with NEOs to drive is:
  //   5880.0 / 60.0 / SdsModuleConfigurations.MK4_L2.getDriveReduction() * SdsModuleConfigurations.MK4_L2.getWheelDiameter() * Math.PI
  /**
   * The maximum velocity of the robot in meters per second.
   * <p>
   * This is a measure of how fast the robot should be able to drive in a straight line.
   */
  public static final double MAX_VELOCITY_METERS_PER_SECOND = 6380.0 / 60.0 *
          SdsModuleConfigurations.MK4_L2.getDriveReduction() *
          SdsModuleConfigurations.MK4_L2.getWheelDiameter() * Math.PI;
  /**
   * The maximum angular velocity of the robot in radians per second.
   * <p>
   * This is a measure of how fast the robot can rotate in place.
   */
  // Here we calculate the theoretical maximum angular velocity. You can also replace this with a measured amount.
  public static final double MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND = MAX_VELOCITY_METERS_PER_SECOND /
          Math.hypot(RobotMap.DRIVETRAIN_TRACKWIDTH_METERS / 2.0, RobotMap.DRIVETRAIN_WHEELBASE_METERS / 2.0);

  public final SwerveDriveKinematics m_kinematics = new SwerveDriveKinematics(
          // Front left
          new Translation2d(RobotMap.DRIVETRAIN_TRACKWIDTH_METERS / 2.0, RobotMap.DRIVETRAIN_WHEELBASE_METERS / 2.0),
          // Front right
          new Translation2d(RobotMap.DRIVETRAIN_TRACKWIDTH_METERS / 2.0, -RobotMap.DRIVETRAIN_WHEELBASE_METERS / 2.0),
          // Back left
          new Translation2d(-RobotMap.DRIVETRAIN_TRACKWIDTH_METERS / 2.0, RobotMap.DRIVETRAIN_WHEELBASE_METERS / 2.0),
          // Back right
          new Translation2d(-RobotMap.DRIVETRAIN_TRACKWIDTH_METERS / 2.0, -RobotMap.DRIVETRAIN_WHEELBASE_METERS / 2.0)
  );

  // These are our modules. We initialize them in the constructor.
  public final SwerveModule m_frontLeftModule;
  public final SwerveModule m_frontRightModule;
  public final SwerveModule m_backLeftModule;
  public final SwerveModule m_backRightModule;

  private ChassisSpeeds m_chassisSpeeds = new ChassisSpeeds(0.0, 0.0, 0.0);

  public Drivetrain() {
    ShuffleboardTab tab = Shuffleboard.getTab("Drivetrain");

    // Set a current limit to limit the robot's acceleration
    // Helps prevent tipping over
    Mk4ModuleConfiguration currentLimit = new Mk4ModuleConfiguration();
    currentLimit.setDriveCurrentLimit(48); // TODO update this value

    m_frontLeftModule = Mk4SwerveModuleHelper.createFalcon500(
            // This parameter is optional, but will allow you to see the current state of the module on the dashboard.
            tab.getLayout("Front Left Module", BuiltInLayouts.kList)
                    .withSize(2, 4)
                    .withPosition(0, 0),
            currentLimit,
            // This can either be STANDARD or FAST depending on your gear configuration
            Mk4SwerveModuleHelper.GearRatio.L2,
            // This is the ID of the drive motor
            RobotMap.FRONT_LEFT_MODULE_DRIVE_MOTOR,
            // This is the ID of the steer motor
            RobotMap.FRONT_LEFT_MODULE_STEER_MOTOR,
            // This is the ID of the steer encoder
            RobotMap.FRONT_LEFT_MODULE_STEER_ENCODER,
            // This is how much the steer encoder is offset from true zero (In our case, zero is facing straight forward)
            RobotMap.FRONT_LEFT_MODULE_STEER_OFFSET
    );

    // We will do the same for the other modules
    m_frontRightModule = Mk4SwerveModuleHelper.createFalcon500(
            tab.getLayout("Front Right Module", BuiltInLayouts.kList)
                    .withSize(2, 4)
                    .withPosition(2, 0),
            currentLimit,
            Mk4SwerveModuleHelper.GearRatio.L2,
            RobotMap.FRONT_RIGHT_MODULE_DRIVE_MOTOR,
            RobotMap.FRONT_RIGHT_MODULE_STEER_MOTOR,
            RobotMap.FRONT_RIGHT_MODULE_STEER_ENCODER,
            RobotMap.FRONT_RIGHT_MODULE_STEER_OFFSET
    );

    m_backLeftModule = Mk4SwerveModuleHelper.createFalcon500(
            tab.getLayout("Back Left Module", BuiltInLayouts.kList)
                    .withSize(2, 4)
                    .withPosition(4, 0),
            currentLimit,
            Mk4SwerveModuleHelper.GearRatio.L2,
            RobotMap.BACK_LEFT_MODULE_DRIVE_MOTOR,
            RobotMap.BACK_LEFT_MODULE_STEER_MOTOR,
            RobotMap.BACK_LEFT_MODULE_STEER_ENCODER,
            RobotMap.BACK_LEFT_MODULE_STEER_OFFSET
    );

    m_backRightModule = Mk4SwerveModuleHelper.createFalcon500(
            tab.getLayout("Back Right Module", BuiltInLayouts.kList)
                    .withSize(2, 4)
                    .withPosition(6, 0),
            currentLimit,
            Mk4SwerveModuleHelper.GearRatio.L2,
            RobotMap.BACK_RIGHT_MODULE_DRIVE_MOTOR,
            RobotMap.BACK_RIGHT_MODULE_STEER_MOTOR,
            RobotMap.BACK_RIGHT_MODULE_STEER_ENCODER,
            RobotMap.BACK_RIGHT_MODULE_STEER_OFFSET
    );

  }

  /**
   * Sets the gyroscope angle to zero. This can be used to set the direction the robot is currently facing to the
   * 'forwards' direction.
   */
  public void zeroGyroscope() {
    OI.gyro.m_navx.zeroYaw();
  }

  public void drive(ChassisSpeeds chassisSpeeds) {
    m_chassisSpeeds = chassisSpeeds;
  }

  public SwerveModuleState getState(SwerveModule module) {
    return new SwerveModuleState(module.getDriveVelocity(), new Rotation2d(module.getSteerAngle()));
  }

  @Override
  public void periodic() {
    SwerveModuleState[] states = m_kinematics.toSwerveModuleStates(m_chassisSpeeds);
    SwerveDriveKinematics.desaturateWheelSpeeds(states, MAX_VELOCITY_METERS_PER_SECOND);

    m_frontLeftModule.set(states[0].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE, states[0].angle.getRadians());
    m_frontRightModule.set(states[1].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE, states[1].angle.getRadians());
    m_backLeftModule.set(states[2].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE, states[2].angle.getRadians());
    m_backRightModule.set(states[3].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE, states[3].angle.getRadians());
  }
}