package org.team1515.botifypremium.Utils;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;

public class Field {
    public static Pose2d STARTING_POSE = new Pose2d(Units.feetToMeters(24.79), Units.feetToMeters(20.83), Rotation2d.fromDegrees(90));
    public static Pose2d HUB_POSE = new Pose2d(8.23, 4.11, new Rotation2d());
    public static Pose2d BALL_ONE_POSE = new Pose2d(Units.feetToMeters(25.09), Units.feetToMeters(26.08), new Rotation2d());
    public static Pose2d BALL_TWO_POSE = new Pose2d(Units.feetToMeters(16.72), Units.feetToMeters(20.77), new Rotation2d());
}
