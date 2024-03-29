package frc.robot.subsystems.arm;

import org.littletonrobotics.junction.Logger;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {

    private ArmIO io;
    public final ArmIOInputsAutoLogged inputs = new ArmIOInputsAutoLogged();

    private final double ARM_MAX_ANGLE = 294;
    private final double ARM_MIN_ANGLE = 95;


    public Arm(ArmIO io) {
       this.io = io;
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

        ArmRealIO.p = SmartDashboard.getNumber("P", ArmRealIO.p);
        ArmRealIO.i = SmartDashboard.getNumber("I", ArmRealIO.i);
        ArmRealIO.d = SmartDashboard.getNumber("D", ArmRealIO.d);
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
        setTargetAngle(getTargetAngleValue(pos));
    }

    public double getTargetAngleValue(ArmPos pos){
        switch (pos) {
            case START:
                return 180;
            case INTAKE:
                return 290;
            case CLIMB:
                return 190;
            case AMP:
                return 205; 
            case DRIVE:
                return 105;
            case SPEAKER:
                return 180;
            default:
                System.out.println("bad arm angle");
                return 180;}
    }

    public void setPower(double power){
        io.setPower(power);
    }
}
