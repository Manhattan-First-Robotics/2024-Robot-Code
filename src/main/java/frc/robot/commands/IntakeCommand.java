package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.arm.Arm;
import frc.robot.subsystems.arm.ArmPos;
import frc.robot.subsystems.intake.Intake;

public class IntakeCommand extends Command {
    Intake intake;
    Arm arm;

    BooleanSupplier cancel;

    public IntakeCommand(BooleanSupplier cancel, Intake intake, Arm arm) {
        this.cancel = cancel;

        //addRequirements(intake);
        addRequirements(arm);

        //this.intake = intake;
        this.arm = arm;
    }

    @Override
    public void initialize() {
        //intake.setPower(-.8,-.8);
        arm.setPosition(ArmPos.INTAKE);
    }

    @Override
    public void execute() {
        
    }

    @Override
    public void end(boolean interrupted) {
        //intake.setPower(0,0);
         arm.setPosition(ArmPos.DRIVE);
    }

    @Override
    public boolean isFinished() {
        //return intake.getBeamBreak() | cancel.getAsBoolean();
        return cancel.getAsBoolean();
    }
}
