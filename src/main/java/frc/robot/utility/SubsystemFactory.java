package frc.robot.utility;

import static frc.robot.Constants.*;
 
import frc.robot.subsystems.arm.Arm;
import frc.robot.subsystems.arm.ArmRealIO;
import frc.robot.subsystems.drive.Drive;
import frc.robot.subsystems.drive.DriveIOReal;
import frc.robot.subsystems.drive.DriveIOSim;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.intake.IntakeRealIO;

public final class SubsystemFactory {

    public static Drive createDriveTrain(RobotIdentity identity) {
        switch (identity) {
            case SIMULATION:
                return new Drive(new DriveIOSim(DRIVE_GEAR_RATIO, DRIVE_WHEEL_RADIUS), TRACK_WIDTH, DRIVE_MAX_SPEED);
            case ROBOT_2024:
                return new Drive(new DriveIOReal(LEFT_FRONT_MOTOR_CHANNEL,LEFT_BACK_MOTOR_CHANNEL,RIGHT_FRONT_MOTR_CHANNEL,RIGHT_BACK_MOTOR_CHANNEL, DRIVE_WHEEL_RADIUS, DRIVE_GEAR_RATIO), TRACK_WIDTH, DRIVE_MAX_SPEED);
            default:
                return null;
        }
    }

    public static Arm createarm(RobotIdentity identity) {
        switch (identity) {
            case SIMULATION:

            case ROBOT_2024:
                return new Arm(new ArmRealIO(ARM_LEFT_CANID, ARM_RIGHT_CANID));
            default:
                return null;
        }
    }


    
    public static Blinken createBlinken(RobotIdentity identity) {
        switch (identity) {
            case ROBOT_2024:
                return new Blinken();
            default:
                return null;
        }
    }

    public static Intake createIntake(RobotIdentity identity) {
        switch (identity) {
            case ROBOT_2024:
                return new Intake(new IntakeRealIO(INTAKE_TOP_CANID,INTAKE_BOTTOM_CANID));
            default:
                return null;
        }
    }
}
