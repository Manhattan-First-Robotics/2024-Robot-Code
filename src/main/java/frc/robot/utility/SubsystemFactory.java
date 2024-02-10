package frc.robot.utility;

import static frc.robot.Constants.*;

import frc.robot.subsystems.drive.Drive;
import frc.robot.subsystems.drive.DriveIOReal;
import frc.robot.subsystems.drive.DriveIOSim;

public final class SubsystemFactory {

    public static Drive createDriveTrain(RobotIdentity identity) {
        switch (identity) {
            case SIMULATION:
                return new Drive(new DriveIOSim(DRIVE_GEAR_RADIO, DRIVE_WHEEL_RADIUS), TRACK_WIDTH, DRIVE_MAX_SPEED);
            case ROBOT_2024:
                return new Drive(new DriveIOReal(LEFT_FRONT_MOTOR_CHANNEL,LEFT_BACK_MOTOR_CHANNEL,RIGHT_FRONT_MOTR_CHANNEL,RIGHT_BACK_MOTOR_CHANNEL, DRIVE_WHEEL_RADIUS, DRIVE_TRAIN_RATIO), TRACK_WIDTH, DRIVE_MAX_SPEED);
            default:
                return null;
        }
    }
}
