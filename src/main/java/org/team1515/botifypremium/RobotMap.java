package org.team1515.botifypremium;

public class RobotMap {

    // Conversion factors
    public final static double INCHES_TO_METERS = 0.0254;
    public final static double METERS_TO_INCHES = 1.0/0.0254;
    public final static double FALCON_SENSOR_UNITS = 4096.0;
    
    // Shooter
    public final static int SHOOTER_ID = 27;

    // Climber
    public final static int RIGHT_VERTICAL_CLIMBER_ID = 31;
    public final static int RIGHT_DIAGONAL_CLIMBER_ID = 32;
    public final static int LEFT_VERTICAL_CLIMBER_ID = 33;
    public final static int LEFT_DIAGONAL_CLIMBER_ID = 34;

    public final static int STRING_RV = 0;
    public final static int STRING_LV = 1;
    public final static int STRING_LD = 2;
    public final static int STRING_RD = 3;


    // Intake
    public final static int INTAKE_ID = 25;
    
    // Magazine
    public final static int MAG_ID = 26;
    //public final static int ULTRA_ID = 0;

    // Limelight
    public final static double HEIGHT_OF_LIMELIGHT = 26;// * INCHES_TO_METERS;
    public final static double ANGLE_OF_LIMELIGHT = 40;
    public final static double HEIGHT_OF_TARGET = 104;// * INCHES_TO_METERS;
    public final static double DISTANCE_OFFSET = -9; // IN INCHES

    //Drivetrain

    /**
     * The left-to-right distance between the drivetrain wheels
     * Should be measured from center to center.
     */
    public static final double DRIVETRAIN_TRACKWIDTH_METERS = 21.5 * INCHES_TO_METERS;
    /**
    * The front-to-back distance between the drivetrain wheels.
    * Should be measured from center to center.
    */
    public static final double DRIVETRAIN_WHEELBASE_METERS = 21.5 * INCHES_TO_METERS;

    // Get the IDs from Phoenix Tuner
    public static final int FRONT_LEFT_MODULE_DRIVE_MOTOR = 15; 
    public static final int FRONT_LEFT_MODULE_STEER_MOTOR = 16; 
    public static final int FRONT_LEFT_MODULE_STEER_ENCODER = 21; 
    public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(107.7); 

    public static final int FRONT_RIGHT_MODULE_DRIVE_MOTOR = 17; 
    public static final int FRONT_RIGHT_MODULE_STEER_MOTOR = 18; 
    public static final int FRONT_RIGHT_MODULE_STEER_ENCODER = 22; 
    public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(117.3);

    public static final int BACK_LEFT_MODULE_DRIVE_MOTOR = 13;
    public static final int BACK_LEFT_MODULE_STEER_MOTOR = 14; 
    public static final int BACK_LEFT_MODULE_STEER_ENCODER = 20; 
    public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(232.5);

    public static final int BACK_RIGHT_MODULE_DRIVE_MOTOR = 11;
    public static final int BACK_RIGHT_MODULE_STEER_MOTOR = 12; 
    public static final int BACK_RIGHT_MODULE_STEER_ENCODER = 19;
    public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(346.4);   
}

