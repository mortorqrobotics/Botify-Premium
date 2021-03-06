package org.team1515.botifypremium;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.button.Button;

public class Controls {
    public static final Button SHOOT = new Button(OI.secondStick::getXButton);
    public static final Button OUTAKE = new Button(OI.secondStick::getLeftBumper);
    public static final Button INTAKE = new Button(OI.secondStick::getRightBumper);
    public static final Button MAGUP = new Button(OI.secondStick::getAButton);
    public static final Button MAGDOWN = new Button(OI.secondStick::getBButton);
    public static final Button RESET_SPEED = new Button(Controls::secondRightStickTrigger);

    public static final Button ROBOT_ALIGN = new Button(OI.mainStick::getStartButton);

    // Climber Buttons
    public static final Button EXPAND_VERTICAL = new Button(OI.mainStick::getRightBumper);
    public static final Button RETRACT_VERTICAL = new Button(Controls::getRightTrigger);
    public static final Button EXPAND_DIAGONAL = new Button(OI.mainStick::getYButton);
    public static final Button RETRACT_DIAGONAL = new Button(OI.mainStick::getXButton);

    public static final Button MANUAL_CLIMBER_L = new Button(OI.mainStick::getBButton);
    public static final Button MANUAL_CLIMBER_R = new Button(OI.mainStick::getAButton);

    public static final Button LEFT_DPAD = new Button(DPadButton.LEFT::getDPadButton);
    public static final Button RIGHT_DPAD = new Button(DPadButton.RIGHT::getDPadButton);
    public static final Button UP_DPAD = new Button(DPadButton.UP::getDPadButton);
    public static final Button DOWN_DPAD = new Button(DPadButton.DOWN::getDPadButton);

    public static final Button RESETGYRO = new Button(OI.mainStick::getBackButton);
    public static final BooleanSupplier DRIVE_ROBOT_ORIENTED = () -> OI.mainStick.getLeftBumper();
    // public static final BooleanSupplier DRIVE_ROBOT_ORIENTED = () -> false;

    public static boolean secondRightStickTrigger() {
        return OI.secondStick.getRightTriggerAxis() >= 0.250;
    }

    public static boolean getRightTrigger() {
        return OI.mainStick.getRightTriggerAxis() >= 0.250;
    }

    public static boolean getLeftTrigger() {
        return OI.mainStick.getLeftTriggerAxis() >= 0.250;
    }

    public enum DPadButton {
        UP (0),
        RIGHT (90),
        DOWN (180),
        LEFT (270);

        private final int angle;
        DPadButton(int direction) {
            this.angle = direction;
        }

        public boolean getDPadButton() {
            return OI.mainStick.getPOV() == angle;
        }
    }
}
