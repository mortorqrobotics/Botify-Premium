package org.team1515.botifypremium.Utils;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;

public class BallVision {
    NetworkTable table;

    public BallVision(){
        table = NetworkTableInstance.getDefault().getTable("FRCMap");

        setTeam(DriverStation.Alliance.Blue);
    }

    /**
     * @return distance in meters
     */
    public double getDistance() {
        return table.getEntry("Distance: ").getDouble(0.0);
    }

    /** 
     * @return horizontal offset in pixels
     */
    public double getOffset() {
        return table.getEntry("Offset: ").getDouble(0.0);
    }

    public void setTeam(DriverStation.Alliance alliance) {
        if(alliance == DriverStation.Alliance.Red) {
            table.getEntry("Alliance: ").setNumber(1);
        }
        else if(alliance == DriverStation.Alliance.Blue) {
            table.getEntry("Alliance: ").setNumber(2);
        }
    }

    public void setTeam() {
        setTeam(DriverStation.getAlliance());
    }

    public void enableTeleop() {
        table.getEntry("isAutonomous").setBoolean(false);
    }
}
