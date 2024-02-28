package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.winch.Winch;

public class ClimbCommand extends Command {
    Winch winch;
    BooleanSupplier cancel;

    double multiplier;
    
    public ClimbCommand(BooleanSupplier cancel, Winch winchSubsystem, double multiplier){
        winch = winchSubsystem;

        this.addRequirements(winchSubsystem);

        this.cancel = cancel;
    }

    @Override
    public void initialize() {
        winch.setPower(multiplier);
    }

    @Override
    public void end(boolean interrupted) {
        winch.setPower(0);
    }

    @Override
    public boolean isFinished() {
        return cancel.getAsBoolean();
    }
}
