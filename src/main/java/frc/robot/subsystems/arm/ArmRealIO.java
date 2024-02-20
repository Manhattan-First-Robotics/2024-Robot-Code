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

    public static double p = 0.0025;
    public static double i = 0;
    public static double d = 0;

    private double targetAngle;

    private final double ARM_OFSET = Units.radiansToDegrees(2.351404666900635);

    public ArmRealIO(int CanIdLeft, int CanIdRight){
        sparkMaxLeft = new CANSparkMax(CanIdLeft, MotorType.kBrushless);
        sparkMaxRight = new CANSparkMax(CanIdRight, MotorType.kBrushless);

        sparkMaxLeft.restoreFactoryDefaults();
        sparkMaxRight.restoreFactoryDefaults();

        sparkMaxLeft.setIdleMode(IdleMode.kCoast);
        sparkMaxRight.setIdleMode(IdleMode.kCoast);

        sparkMaxLeft.setSmartCurrentLimit(40);
        sparkMaxRight.setSmartCurrentLimit(40);

        sparkMaxLeft.follow(sparkMaxRight,true);

        sparkMaxRight.setInverted(true);

        sparkMaxLeft.getEncoder().setPositionConversionFactor((360)/180);
        sparkMaxRight.getEncoder().setPositionConversionFactor((360)/180);

        sparkMaxLeft.getEncoder().setVelocityConversionFactor(((360)/180)/60);
        sparkMaxLeft.getEncoder().setVelocityConversionFactor(((360)/180)/60);

        absoluteEncoder = sparkMaxRight.getAbsoluteEncoder(Type.kDutyCycle); 
        absoluteEncoder.setPositionConversionFactor(360);
        absoluteEncoder.setVelocityConversionFactor((360)/60);

        absoluteEncoder.setZeroOffset(ARM_OFSET);

        pidController = sparkMaxRight.getPIDController();
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
