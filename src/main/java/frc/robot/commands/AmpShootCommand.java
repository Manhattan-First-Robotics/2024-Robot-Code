package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.IntakeConstants;
import frc.robot.subsystems.arm.Arm;
import frc.robot.subsystems.intake.Intake;

public class AmpShootCommand extends Command {
    Intake intake;
    Arm arm;

    BooleanSupplier cancel;

    public AmpShootCommand(BooleanSupplier cancel, Intake intake, Arm arm){
        this.cancel = cancel;

        addRequirements(intake);
        addRequirements(arm);

        this.intake = intake;
        this.arm = arm;
    }

    @Override
    public void initialize() {
        intake.setPower(IntakeConstants.ampShootSpeed);
        arm.setTargetAngle(ArmConstants.ampShootAngle);
    }

    @Override
    public void execute() {
        
    }

    @Override
    public void end(boolean interrupted) {
        intake.setPower(0);
        arm.setTargetAngle(ArmConstants.driveAngle);
    }

    @Override
    public boolean isFinished() {
        return cancel.getAsBoolean();
    }
}