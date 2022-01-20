// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.team1515.botifypremium;

import org.team1515.botifypremium.Subsystems.Shooter;
import org.team1515.botifypremium.Subsystems.Turret;
import org.team1515.botifypremium.Subsystems.Climber;

import edu.wpi.first.wpilibj.TimedRobot;

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

  private Turret turret;
  public static Climber climber;
  public static Shooter shooter;

  @Override
  public void robotInit() {
    turret = new Turret();
    shooter = new Shooter();
    climber = new Climber();
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    turret.turretPeriodic();
  }
}
