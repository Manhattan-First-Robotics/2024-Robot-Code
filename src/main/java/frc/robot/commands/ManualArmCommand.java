package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.arm.Arm;
import frc.robot.utility.Joystick;

public class ManualArmCommand extends Command {
    BooleanSupplier cancel;
    Arm armSubsytem;
    DoubleSupplier armSpeed;


    public ManualArmCommand(BooleanSupplier cancelSupplier, Arm arm, DoubleSupplier speedInput){
        armSubsytem = arm;

        addRequirements(arm);
        
        cancel = cancelSupplier;

        armSpeed = speedInput;
    }

    @Override
    public void initialize() {
        
    }

    @Override
    public void execute() {
        armSubsytem.setPower(-Joystick.JoystickInput(armSpeed.getAsDouble(), 3, 0.02, 0.75));
    }

    @Override
    public void end(boolean interrupted) {
        armSubsytem.setPower(0);
    }

    @Override
    public boolean isFinished() {
        return cancel.getAsBoolean();
    }
}
