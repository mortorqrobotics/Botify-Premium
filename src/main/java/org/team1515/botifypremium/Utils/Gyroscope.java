package org.team1515.botifypremium.Utils;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.SPI;

public class Gyroscope {
    public final AHRS m_navx;

    public Gyroscope() {
        m_navx = new AHRS(SPI.Port.kMXP, (byte) 200);
    }

    public Rotation2d getGyroscopeRotation() {  
        if (m_navx.isMagnetometerCalibrated()) {
           // We will only get valid fused headings if the magnetometer is calibrated
          return Rotation2d.fromDegrees(m_navx.getFusedHeading());
        }
     
         // We have to invert the angle of the NavX so that rotating the robot counter-clockwise makes the angle increase.
        return Rotation2d.fromDegrees(360.0 - m_navx.getYaw());
    }
}
