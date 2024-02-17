package frc.robot.subsystems.intake;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.motorcontrol.Victor;

public class IntakeRealIO implements IntakeIO{
   
    private VictorSPX topMotor;
    private VictorSPX bottomMotor;

    public IntakeRealIO(int CanIdTop, int CanIdBottom){
        topMotor = new VictorSPX(CanIdTop);
        bottomMotor = new VictorSPX(CanIdBottom);

        topMotor.setNeutralMode(NeutralMode.Coast);
        bottomMotor.setNeutralMode(NeutralMode.Coast);

    }
    
    //@Override
    //public void updateInputs();
}
