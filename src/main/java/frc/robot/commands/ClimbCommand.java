package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.arm.Arm;
import frc.robot.subsystems.winch.Winch;

public class ClimbCommand extends Command {
    Arm arm;
    Winch winch;
    BooleanSupplier cancel;

    public ClimbCommand(BooleanSupplier cancel, Arm armSubsystem, Winch winchSubsystem){
        arm = armSubsystem;
        winch = winchSubsystem;

        this.addRequirements(armSubsystem);
        this.addRequirements(winchSubsystem);

        this.cancel = cancel;
    }

    @Override
    public void initialize() {
        winch.setPower(0.5);
        arm.setPower(0);
    }

    @Override
    public void end(boolean interrupted) {
        winch.setPower(0);
        arm.setPower(0);
    }

    @Override
    public boolean isFinished() {
        return cancel.getAsBoolean();
    }
}
