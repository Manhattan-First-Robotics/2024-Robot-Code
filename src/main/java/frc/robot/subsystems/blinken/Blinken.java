package frc.robot.subsystems.blinken;

import static frc.robot.Constants.*;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Blinken extends SubsystemBase {


    public Blinken() {
       //this.io = io;
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
