package frc.robot.subsystems.winch;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Winch extends SubsystemBase {
    private WinchIO io;
    public final WinchIOInputsAutoLogged inputs = new WinchIOInputsAutoLogged();

    public Winch(WinchIO io){
        this.io = io;
    }

    @Override
    public void periodic() {
        io.updateInputs(inputs);
        Logger.processInputs("Winch", inputs);

        if (this.getCurrentCommand() !=null){
            Logger.recordOutput("Winch/CurrentCommand", this.getCurrentCommand().getName());
        }
        else{
            Logger.recordOutput("Winch/CurrentCommand","none");
        }
    }

    public void setPower(double power){
        io.setPower(power);
    }
}
