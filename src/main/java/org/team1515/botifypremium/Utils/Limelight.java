package org.team1515.botifypremium.Utils;

import org.team1515.botifypremium.RobotMap;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {

    private NetworkTable table;
    NetworkTableEntry tx;
    NetworkTableEntry ty;
    NetworkTableEntry ta;
    NetworkTableEntry pipeline;

    NetworkTableEntry xcorners, ycorners;

    private double txOffset = 0;

    public Limelight() {
        table = NetworkTableInstance.getDefault().getTable("limelight");
        tx = table.getEntry("tx");
        ty = table.getEntry("ty");
        ta = table.getEntry("ta");
        pipeline = table.getEntry("pipeline");

        xcorners = table.getEntry("tcornx");
        ycorners = table.getEntry("tcorny");
    }

    /** 
     * @return double Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees)
     */
    public double getTX() {
        return tx.getDouble(0.0) + txOffset;
    }
    
    /** 
     * @return double Target Area (0% of image to 100% of image)
     */
    public double getTA() {
        return ta.getDouble(0.0);
    }
    
    /** 
     * @return double Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees)
     */
    public double getTY() {
        return ty.getDouble(0.0);
    }

    /**
     * @return double Get distance in meters
     */
    public double getDistance() {
        double deltaHeight = RobotMap.HEIGHT_OF_TARGET - RobotMap.HEIGHT_OF_LIMELIGHT;
        return deltaHeight / Math.tan(Math.toRadians(getTY() + RobotMap.ANGLE_OF_LIMELIGHT));
    }

    public void setPipeline(int pipeline) {
        this.pipeline.setNumber(pipeline);
    }
}
