package org.team1515.botifypremium.Commands.Autonomous;

import org.team1515.botifypremium.OI;
import org.team1515.botifypremium.Subsystems.Drivetrain;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RotateToAngle extends CommandBase {
    private Drivetrain drivetrain;
    private Rotation2d targetAngle;
    private PIDController angleController;

    private double maxSpeed = 0.25 * Drivetrain.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND;
    private double setpoint = 0;
    private double deadband = 0.2;

    public RotateToAngle(Drivetrain drivetrain, Rotation2d targetAngle) {
        this.drivetrain = drivetrain;
        this.targetAngle = targetAngle;

        angleController = new PIDController(1, 2, 0);
        angleController.setTolerance(deadband);
        // angleController.enableContinuousInput(0, 2*Math.PI);
        angleController.setSetpoint(setpoint);

        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        double angleOffset = OI.gyro.getGyroscopeRotation().minus(targetAngle).getRadians();
        double speed = MathUtil.clamp(angleController.calculate(angleOffset, setpoint), -maxSpeed, maxSpeed);
        SmartDashboard.putNumber("angle offset", Math.toDegrees(angleOffset));

        ChassisSpeeds speeds = new ChassisSpeeds(0.0, 0.0, speed);
        drivetrain.drive(speeds);
    }

    @Override
    public boolean isFinished() {
        return angleController.atSetpoint();
    }
}
