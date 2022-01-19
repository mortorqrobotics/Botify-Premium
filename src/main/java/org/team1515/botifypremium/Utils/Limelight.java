package org.team1515.botifypremium.Utils;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {

    private NetworkTable table;
    NetworkTableEntry tx;
    NetworkTableEntry ty;
    NetworkTableEntry ta;

    NetworkTableEntry xcorners, ycorners;

    private double txOffset = 1;

    public Limelight() {
        
        table = NetworkTableInstance.getDefault().getTable("limelight");
        tx = table.getEntry("tx");
        ty = table.getEntry("ty");
        ta = table.getEntry("ta");

        xcorners = table.getEntry("tcornx");
        ycorners = table.getEntry("tcorny");
    }

    public double getTX() {
        return tx.getDouble(0.0) + txOffset;
    }

    public double getTA() {
        return ta.getDouble(0.0);
    }

    public double getTY() {
        return ty.getDouble(0.0);
    }

    public double getDistance() {
        return 0.0;
    }
}
