package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.arm.Arm;
import frc.robot.subsystems.arm.ArmPos;
import frc.robot.subsystems.intake.Intake;

public class SpeakerCommand extends Command {
    Intake intakeSubSystem;
    Arm armSubsystem;

    BooleanSupplier cancel;

    public SpeakerCommand(BooleanSupplier cancelSupplier, Intake intake, Arm arm){
        intakeSubSystem = intake;
        armSubsystem = arm;
        cancel = cancelSupplier;

        addRequirements(intake);
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        armSubsystem.setPosition(ArmPos.SPEAKER);
    }

    @Override
    public void execute() {
        if (Math.abs( armSubsystem.getAngle() - armSubsystem.getTargetAngleValue(ArmPos.INTAKE)) < 3) {
            intakeSubSystem.setPower(1,1);
        }
    }

    @Override
    public void end(boolean interrupted) {
        armSubsystem.setPosition(ArmPos.DRIVE);
    }

    @Override
    public boolean isFinished() {
        return cancel.getAsBoolean();
    }
}
