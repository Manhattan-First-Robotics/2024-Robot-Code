package frc.robot.subsystems.intake;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
    private IntakeIO io;
    public final IntakeIOInputsAutoLogged inputs = new IntakeIOInputsAutoLogged();

    public Intake(IntakeIO io){
        this.io = io;
    }
    @Override
    public void periodic() {
        io.updateInputs(inputs);
        Logger.processInputs("Intake", inputs);

        if (this.getCurrentCommand() !=null){
            Logger.recordOutput("Intake/CurrentCommand", this.getCurrentCommand().getName());
        }
        else{
            Logger.recordOutput("Intake/CurrentCommand","none");
        }
    }
    public void setPower(double top, double bottem){
        io.setPower(top, bottem);
    }
    public boolean getBeamBreak(){
        return inputs.beamBreak;
    }
}
