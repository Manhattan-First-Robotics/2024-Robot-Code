package frc.robot.subsystems.winch;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class WinchIOReal implements WinchIO {
    private CANSparkMax winchMotor;

    public WinchIOReal(int CANId){
        winchMotor = new CANSparkMax(CANId, MotorType.kBrushless);

        winchMotor.restoreFactoryDefaults();

        winchMotor.setIdleMode(IdleMode.kBrake);

        winchMotor.getEncoder().setPositionConversionFactor(360);
        winchMotor.getEncoder().setVelocityConversionFactor(360/60);
    }

    @Override
    public void updateInputs(WinchIOInputs inputs) {
        inputs.current = winchMotor.getOutputCurrent();
        inputs.appliedOutput = winchMotor.getAppliedOutput();
        inputs.velocity = winchMotor.getEncoder().getVelocity();
        inputs.relativePosition = winchMotor.getEncoder().getPosition();
    }

    public void setPower(double power){
        winchMotor.set(power);
    }
}
