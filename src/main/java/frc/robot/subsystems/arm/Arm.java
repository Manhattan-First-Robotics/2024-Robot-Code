package frc.robot.subsystems.arm;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkAbsoluteEncoder.Type;

import static frc.robot.Constants.ArmConstants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
    private CANSparkMax sparkMax;

    private AbsoluteEncoder encoder;

    private SparkPIDController pidController;

    double desiredAngle;
    
    public Arm() {
        sparkMax = new CANSparkMax(ArmConstants.sparkMaxCANID, MotorType.kBrushless);

        sparkMax.restoreFactoryDefaults();

        encoder = sparkMax.getAbsoluteEncoder(Type.kDutyCycle);

        pidController = sparkMax.getPIDController();

        pidController.setFeedbackDevice(encoder);

        pidController.setP(ArmConstants.P);
        pidController.setI(ArmConstants.I);
        pidController.setD(ArmConstants.D);
        pidController.setOutputRange(ArmConstants.minOutput, ArmConstants.maxOutput);

        encoder.setPositionConversionFactor(Math.PI * 2);
        encoder.setVelocityConversionFactor(Math.PI * 2 / 60);

        sparkMax.setSmartCurrentLimit(ArmConstants.currentLimit);
        
        desiredAngle = getAngle();
    }

    public double getAngle(){
        return encoder.getPosition();
    }

    public double getDesiredAngle(){
        return desiredAngle;
    }

    public void setTargetAngle(double angle){
        desiredAngle = angle;

        pidController.setReference(angle, ControlType.kPosition);
    }
}
