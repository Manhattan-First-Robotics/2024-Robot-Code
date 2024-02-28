package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.arm.Arm;
import frc.robot.subsystems.arm.ArmPos;

public class ArmDriveCommand extends Command {
    Arm armSubsystem;
    BooleanSupplier cancelSupplier;

    public ArmDriveCommand(BooleanSupplier cancel, Arm arm){
        armSubsystem = arm;
        cancelSupplier = cancel;

        addRequirements(arm);
    }

    @Override
    public void initialize() {
        armSubsystem.setPosition(ArmPos.START);
    }

    @Override
    public void execute() {
        
    }

    @Override
    public void end(boolean interrupted) {
        armSubsystem.setPosition(ArmPos.DRIVE);
    }

    @Override
    public boolean isFinished() {
        return cancelSupplier.getAsBoolean();
    }
}
