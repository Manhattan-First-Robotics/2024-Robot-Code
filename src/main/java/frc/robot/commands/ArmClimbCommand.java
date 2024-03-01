package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.arm.Arm;
import frc.robot.subsystems.arm.ArmPos;
import frc.robot.subsystems.intake.Intake;

public class ArmClimbCommand extends Command {
    Intake intake;
    Arm arm;

    public ArmClimbCommand(Intake intake, Arm arm){
        addRequirements(intake);
        addRequirements(arm);

        this.intake = intake;
        this.arm = arm;
    }

    @Override
    public void initialize(){
        arm.setPosition(ArmPos.CLIMB);
    }

    @Override
    public void end(boolean interrupted){
        arm.setPosition(ArmPos.INTAKE);
    }
}
