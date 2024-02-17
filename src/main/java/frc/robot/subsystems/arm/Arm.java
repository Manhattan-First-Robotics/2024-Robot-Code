package frc.robot.subsystems.arm;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkAbsoluteEncoder.Type;

import static frc.robot.Constants.*;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {

    private ArmIO io;
    public final ArmIOInputsAutoLogged inputs = new ArmIOInputsAutoLogged();

    public Arm() {
       //this.io = io;
    }

    @Override 
    public void periodic(){
        io.updateInputs(inputs);

        Logger.processInputs("Arm", inputs);

        if (this.getCurrentCommand() !=null){
            Logger.recordOutput("Arm/CurrentCommand", this.getCurrentCommand().getName());
        }
        else{
            Logger.recordOutput("Arm/CurrentCommand","none");
        }
    }
    public double getAngle(){
        return inputs.currentAngle;
    }

    public double getTargetAngle(){
        return inputs.targetAngle;
    }

    public void setTargetAngle(double angle){
       inputs.targetAngle = angle;
    }
}
