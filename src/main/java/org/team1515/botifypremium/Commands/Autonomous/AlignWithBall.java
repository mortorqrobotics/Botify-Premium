package org.team1515.botifypremium.Commands.Autonomous;

import org.team1515.botifypremium.OI;
import org.team1515.botifypremium.Robot;
import org.team1515.botifypremium.Subsystems.Drivetrain;
import org.team1515.botifypremium.Utils.BallVision;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AlignWithBall extends CommandBase {
    private Drivetrain drivetrain;
    private PIDController angleController;

    private double maxSpeed = 0.25 * Drivetrain.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND;

    public AlignWithBall(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;

        angleController = new PIDController(0.25, 0.8, 0);
        angleController.setTolerance(30);
        angleController.enableContinuousInput(-Math.PI, Math.PI);
        angleController.setSetpoint(0);

        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        double offset = Robot.ballCam.getOffset();
        double speed = MathUtil.clamp(angleController.calculate(offset, 0.0), -maxSpeed, maxSpeed);
        if (offset == 0)
            this.end(true);
        SmartDashboard.putNumber("pixel offset", offset);

        ChassisSpeeds speeds = new ChassisSpeeds(0.0, 0.0, speed);
        drivetrain.drive(speeds);
    }

    @Override
    public boolean isFinished() {
        return angleController.atSetpoint();
    }
}
