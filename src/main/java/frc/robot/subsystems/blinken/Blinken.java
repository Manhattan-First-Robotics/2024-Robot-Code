package frc.robot.subsystems.blinken;

import static frc.robot.Constants.*;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Blinken extends SubsystemBase {

    private static Spark m_blinken;

    public Blinken(int pwmPort) {
        m_blinken = new Spark(pwmPort);
    }

    public void setColor (double color){
        m_blinken.set(color);


    }

    @Override 
    public void periodic(){
        if (this.getCurrentCommand() !=null){
            Logger.recordOutput("Blinken/CurrentCommand", this.getCurrentCommand().getName());
        }
        else{
            Logger.recordOutput("Blinken/CurrentCommand","none");
        }
    }


}
