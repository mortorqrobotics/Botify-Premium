// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.team1515.botifypremium;

import org.team1515.botifypremium.Utils.Limelight;
import org.team1515.botifypremium.Utils.Utilities;
import org.team1515.botifypremium.Subsystems.Climber;
import org.team1515.botifypremium.Subsystems.Drivetrain;
import org.team1515.botifypremium.Subsystems.Shooter;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */

  public static Climber climber;
  public static Limelight limelight;
  public static OI oi;
  public static PowerDistribution PDH;
  public static UsbCamera camera;

  public Command autoCommand;

  @Override
  public void robotInit() {
    Robot.limelight = new Limelight();
    PDH = new PowerDistribution(1, ModuleType.kRev);
    PDH.clearStickyFaults();
    
    oi = new OI();
    camera = CameraServer.startAutomaticCapture(); // Put usb camera on shuffleboard
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    SmartDashboard.putNumber("Robot angle", OI.gyro.getGyroscopeRotation().getDegrees());
    SmartDashboard.putNumber("distance to target (in)", limelight.getDistance());
    SmartDashboard.putBoolean("Is at shooting distance?", Utilities.deadband(144 - limelight.getDistance(), 24) == 0);
    SmartDashboard.putBoolean("Orange : Too close\nWhite: Too far", limelight.getDistance() < 144);

    SmartDashboard.putNumber("current angle", OI.gyro.getGyroscopeRotation().getDegrees());
  }

  @Override
  public void autonomousInit() {
     autoCommand = oi.getAutoCommand();

     if (autoCommand != null) {
      autoCommand.schedule();
    }  
  }

  @Override
  public void autonomousPeriodic() {
    OI.intake.startIntake(); // Continously run intake during auto
  }

  @Override
  public void teleopInit() {
    OI.intake.end();
    if (autoCommand != null) {
      autoCommand.cancel();
    }
    OI.gyro.offset += 0; // Change front or back of robot without messing up the auto
  }

  @Override
  public void teleopPeriodic() {}
}
