package frc.robot.subsystems.drive;


import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.ADIS16448_IMU;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkAbsoluteEncoder.Type;
import com.revrobotics.SparkAbsoluteEncoder;

import static frc.robot.Constants.*;

public class DriveIOReal implements DriveIO{

    private RelativeEncoder leftEncoder;
    private RelativeEncoder rightEncoder;

    private ADIS16448_IMU gyro;

    private int driveTrainCurrentLimmit = 20;

    //private final int NEO_TPR = 42;

    private CANSparkMax leftMotorFront;
    private CANSparkMax leftMotorBack;
    private CANSparkMax rightMotorFront;
    private CANSparkMax rightMotorBack;
    
    private boolean isBrake;
    public DriveIOReal(int IDleftMotor_1, int IDleftMotor_2, int IDrightMotor_1, int IDrightMotor_2, double wheelRadius, double driveRatio){
       
        leftMotorFront = new CANSparkMax(IDleftMotor_1, MotorType.kBrushless);
        leftMotorBack = new CANSparkMax(IDleftMotor_2, MotorType.kBrushless);
        rightMotorFront = new CANSparkMax(IDrightMotor_1, MotorType.kBrushless);
        rightMotorBack = new CANSparkMax(IDrightMotor_2, MotorType.kBrushless);

        leftEncoder = leftMotorFront.getEncoder();
        rightEncoder = rightMotorFront.getEncoder();
        
        gyro = new ADIS16448_IMU();

        gyro.calibrate();
        

        leftMotorFront.restoreFactoryDefaults();
        leftMotorBack.restoreFactoryDefaults();
        rightMotorFront.restoreFactoryDefaults();
        rightMotorBack.restoreFactoryDefaults();

        leftMotorBack.follow(leftMotorFront);
        rightMotorBack.follow(rightMotorFront);

        leftMotorFront.setInverted(false);
        rightMotorFront.setInverted(true);
        

        leftMotorFront.setSmartCurrentLimit(driveTrainCurrentLimmit);
        leftMotorBack.setSmartCurrentLimit(driveTrainCurrentLimmit);
        rightMotorFront.setSmartCurrentLimit(driveTrainCurrentLimmit);
        rightMotorBack.setSmartCurrentLimit(driveTrainCurrentLimmit);

        leftEncoder.setPositionConversionFactor(driveRatio*2*Math.PI);
        rightEncoder.setPositionConversionFactor(driveRatio*2*Math.PI);

        leftEncoder.setVelocityConversionFactor((driveRatio*2*Math.PI)/60);
        rightEncoder.setVelocityConversionFactor((driveRatio*2*Math.PI)/60);

        leftMotorFront.setIdleMode(CANSparkMax.IdleMode.kCoast);
        leftMotorBack.setIdleMode(CANSparkMax.IdleMode.kCoast);
        rightMotorFront.setIdleMode(CANSparkMax.IdleMode.kCoast);
        rightMotorBack.setIdleMode(CANSparkMax.IdleMode.kCoast);
        
       
        //DifferentialDrive difDrive = new DifferentialDrive(leftMotorFront,rightMotorFront);
    }

    public void updateInputs(DriveIOInputs inputs){
        inputs.isBrake = isBrake;
        inputs.leftCurent = leftMotorFront.getOutputCurrent()+ leftMotorBack.getOutputCurrent();
        inputs.rightCurent = rightMotorFront.getOutputCurrent()+ rightMotorBack.getOutputCurrent();
        inputs.leftPower = leftMotorFront.getAppliedOutput();
        inputs.rightPower = rightMotorFront.getAppliedOutput();
        inputs.leftPos = leftEncoder.getPosition();
        inputs.rightPos = rightEncoder.getPosition();
        inputs.leftVel = leftEncoder.getVelocity();
        inputs.rightVel = rightEncoder.getVelocity();
        inputs.heading = Rotation2d.fromDegrees(gyro.getGyroAngleZ());
    }

    private void setCoast(){
        leftMotorFront.setIdleMode(IdleMode.kCoast);
        leftMotorBack.setIdleMode(IdleMode.kCoast);
        rightMotorFront.setIdleMode(IdleMode.kCoast);
        rightMotorBack.setIdleMode(IdleMode.kCoast);
    }

     private void setBrake(){
        leftMotorFront.setIdleMode(IdleMode.kBrake); 
        leftMotorBack.setIdleMode(IdleMode.kBrake);
        rightMotorFront.setIdleMode(IdleMode.kBrake);
        rightMotorBack.setIdleMode(IdleMode.kBrake);
    }

    public void toggleMode() {
        if (isBrake) {
            setCoast();
        } else {
            setBrake();
        }
    }

     public void drive(double leftPower, double rightPower) {
        leftMotorFront.set(leftPower);
        rightMotorFront.set(rightPower);
    }
}
