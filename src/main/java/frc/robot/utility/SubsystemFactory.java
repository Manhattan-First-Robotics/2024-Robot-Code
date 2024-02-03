package frc.robot.utility;

import static frc.robot.Constants.*;

import frc.robot.subsystems.arm.Arm;
import frc.robot.subsystems.drive.Drive;
import frc.robot.subsystems.drive.DriveIOSim;
import frc.robot.subsystems.intake.Intake;

public final class SubsystemFactory {

    public static Drive createDriveTrain(RobotIdentity identity) {
        switch (identity) {
            case SIMULATION:
                return new Drive(new DriveIOSim(DRIVE_GEAR_RADIO, DRIVE_WHEEL_RADIUS), TRACK_WIDTH, DRIVE_MAX_SPEED);
            default:
                return null;
        }
    }

    public static Arm createarm(RobotIdentity identity) {
        switch (identity) {
            case ROBOT_2024:
                return new Arm();
            default:
                return null;
        }
    }

    public static Intake createIntake(RobotIdentity identity) {
        switch (identity) {
            case ROBOT_2024:
                return new Intake();
            default:
                return null;
        }
    }
}
