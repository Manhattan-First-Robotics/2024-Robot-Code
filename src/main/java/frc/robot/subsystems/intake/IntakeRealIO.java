package frc.robot.subsystems.intake;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;


public class IntakeRealIO implements IntakeIO{
   
    private VictorSPX topMotor;
    private VictorSPX bottomMotor;
    private boolean isBreak;
    private DigitalInput beamBreak;

    public IntakeRealIO(int CanIdTop, int CanIdBottom){
        topMotor = new VictorSPX(CanIdTop);
        bottomMotor = new VictorSPX(CanIdBottom);
        
        topMotor.setNeutralMode(NeutralMode.Coast);
        bottomMotor.setNeutralMode(NeutralMode.Coast);

        topMotor.setInverted(true);

        beamBreak = new DigitalInput(0);
    }
    
    @Override
    public void updateInputs(IntakeIOInputs inputs){
        inputs.appliedOutputTop = topMotor.getMotorOutputPercent(); 
        inputs.appliedOutputBottom = topMotor.getMotorOutputPercent(); 
        inputs.beamBreak = beamBreak.get();
        inputs.isBrake = isBreak;
    }

    @Override
    public void setBrake(boolean isBreak){

    }
    @Override
    public void setPower(double top, double bottom){
        topMotor.set(VictorSPXControlMode.PercentOutput, top);
        bottomMotor.set(VictorSPXControlMode.PercentOutput, bottom);
    }
    @Override
    public boolean getBeamBreak() {
        return beamBreak.get();
    }
}
