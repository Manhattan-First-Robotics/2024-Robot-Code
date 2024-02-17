package frc.robot.subsystems.arm;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkAbsoluteEncoder.Type;

import edu.wpi.first.math.util.Units;


public class ArmRealIO implements ArmIO {

    
    private CANSparkMax sparkMaxLeft;
    private CANSparkMax sparkMaxRight;

    private AbsoluteEncoder absoluteEncoder;
    
    private SparkPIDController pidController;

    private double p = 0;
    private double i = 0;
    private double d = 0;

    private double targetAngle;

    private final double ARM_OFSET = Units.degreesToRadians(360-180);

    public ArmRealIO(int CanIdLeft, int CanIdRight){
        sparkMaxLeft = new CANSparkMax(CanIdLeft, MotorType.kBrushless);
        sparkMaxRight = new CANSparkMax(CanIdRight, MotorType.kBrushless);

        sparkMaxLeft.restoreFactoryDefaults();
        sparkMaxRight.restoreFactoryDefaults();

        sparkMaxLeft.setIdleMode(IdleMode.kCoast);
        sparkMaxRight.setIdleMode(IdleMode.kCoast);

        sparkMaxLeft.setSmartCurrentLimit(40);
        sparkMaxRight.setSmartCurrentLimit(40);
        sparkMaxLeft.setInverted(true);

        sparkMaxRight.follow(sparkMaxLeft,true);

        absoluteEncoder = sparkMaxLeft.getAbsoluteEncoder(Type.kDutyCycle); 
        absoluteEncoder.setPositionConversionFactor(Math.PI*2);
        absoluteEncoder.setVelocityConversionFactor((Math.PI*2)/60);

        absoluteEncoder.setZeroOffset(ARM_OFSET);

        pidController = sparkMaxLeft.getPIDController();
        pidController.setFeedbackDevice(absoluteEncoder);
        pidController.setOutputRange(-1, 1);


        pidController.setP(p);
        pidController.setI(i);
        pidController.setD(d);
        
    }
    @Override
    public void updateInputs(ArmIOInputs inputs) {
        inputs.current = sparkMaxRight.getOutputCurrent()+sparkMaxLeft.getOutputCurrent();
        inputs.currentAngle = absoluteEncoder.getPosition();
        inputs.velocity = absoluteEncoder.getVelocity();
        inputs.targetAngle = targetAngle;
        inputs.appliedPower = sparkMaxLeft.getAppliedOutput();
        inputs.relitivePos1 = sparkMaxLeft.getEncoder().getPosition();
        inputs.relitivePos2 = sparkMaxRight.getEncoder().getPosition();
    }
    @Override
    public void setAngle(double angle){
        pidController.setReference(angle, ControlType.kPosition);
        targetAngle = angle;
    }
}
