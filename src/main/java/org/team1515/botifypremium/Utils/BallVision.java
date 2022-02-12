package org.team1515.botifypremium.Utils;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class BallVision {
    NetworkTable table;
    public static double distance;
    public static double offset;

    public BallVision(){
        table = NetworkTableInstance.getDefault().getTable("FRCMap");
    }

    public void GetValues(){
        distance = table.getEntry("distance").getDouble(9999.0);
        offset = table.getEntry("offset").getDouble(9999.0);
    }
}
