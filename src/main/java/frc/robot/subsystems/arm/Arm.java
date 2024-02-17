package frc.robot.subsystems.arm;

import org.littletonrobotics.junction.Logger;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {

    private ArmIO io;
    public final ArmIOInputsAutoLogged inputs = new ArmIOInputsAutoLogged();

    private final double ARM_MAX_ANGLE = Units.degreesToRadians(283);
    private final double ARM_MIN_ANGLE = Units.degreesToRadians(35);


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

    private void setTargetAngle(double angle){
        if(angle > ARM_MAX_ANGLE){
            System.out.println("Arm Angle is too big");
        }
        else if(angle < ARM_MIN_ANGLE){
            System.out.println("Arm Angle is to small");
        }
        else{
            io.setAngle(angle);
        }
    }

    public void setPosition(ArmPos pos){
        
        switch (pos) {
            case START:
                setTargetAngle(Units.degreesToRadians(0));
                break;
            case INTAKE:
                setTargetAngle(Units.degreesToRadians(0));
                break;
            case CLIMB:
                setTargetAngle(Units.degreesToRadians(0));
                break;
            case AMP:
                setTargetAngle(Units.degreesToRadians(0));
                break;    
            default:
                System.out.println("bad arm angle");
                break;
        }
    }
}
