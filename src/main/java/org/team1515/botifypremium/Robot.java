// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.team1515.botifypremium;

import org.team1515.botifypremium.Utils.Limelight;
import org.team1515.botifypremium.Utils.UltraSensor;
import org.team1515.botifypremium.Utils.Utilities;
import org.team1515.botifypremium.Subsystems.Climber;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
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

  private UltraSensor ultraSensor;

  public Command autoCommand;

  @Override
  public void robotInit() {
    Robot.limelight = new Limelight();
    ultraSensor = new UltraSensor();
    PDH = new PowerDistribution(1, ModuleType.kRev);

    PDH.clearStickyFaults();
    
    oi = new OI();
    SmartDashboard.putNumber("shooter speed", 10600);
    camera = CameraServer.startAutomaticCapture();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    SmartDashboard.putNumber("distance to target (in)", limelight.getDistance());
    SmartDashboard.putBoolean("Is at shooting distance?", Utilities.deadband(144 - limelight.getDistance(), 5) == 0);
    SmartDashboard.putBoolean("Orange : Too close\nWhite: Too far", limelight.getDistance() < 144);

    SmartDashboard.putNumber("current angle", OI.gyro.getGyroscopeRotation().getDegrees())

    // if (ultraSensor.itemDetected()){
    //   oi.magazine.end();
    // }
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
    OI.intake.startIntake();
  }

  @Override
  public void teleopInit() {
    OI.intake.end();
    if (autoCommand != null) {
      autoCommand.cancel();
    }

  }

  @Override
  public void teleopPeriodic() {
    SmartDashboard.putNumber("RV Climber Position", OI.climberRV.getPosition());
    SmartDashboard.putNumber("LV Climber Position", OI.climberLV.getPosition());
    // SmartDashboard.putNumber("RD Climber Position", OI.climberRD.getPosition());
    // SmartDashboard.putNumber("RL Climber Position", OI.climberLD.getPosition());

    OI.climberLV.climberPeriodic();
    OI.climberRV.climberPeriodic();
    // OI.climberLD.climberPeriodic();
    // OI.climberRD.climberPeriodic();
  }
}
