package frc.robot.subsystems.intake;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;


public class IntakeRealIO implements IntakeIO{
   
    private VictorSPX topMotor;
    private VictorSPX bottomMotor;
    private boolean isBreak;

    public IntakeRealIO(int CanIdTop, int CanIdBottom){
        topMotor = new VictorSPX(CanIdTop);
        bottomMotor = new VictorSPX(CanIdBottom);

        topMotor.setNeutralMode(NeutralMode.Coast);
        bottomMotor.setNeutralMode(NeutralMode.Coast);

    }
    
    @Override
    public void updateInputs(IntakeIOInputs inputs){
        inputs.appliedOutputTop = topMotor.getMotorOutputPercent(); 
        inputs.appliedOutputBottom = topMotor.getMotorOutputPercent(); 
        inputs.beamBreak = false;
        inputs.isBrake = isBreak;
    }

    @Override
    public void setBrake(boolean isBreak){

    }
    @Override
    public void setPower(double top, double bottom){
        topMotor.set(VictorSPXControlMode.PercentOutput, top);
        topMotor.set(VictorSPXControlMode.PercentOutput, bottom);
    }

}
