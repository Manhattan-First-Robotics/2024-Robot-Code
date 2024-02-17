package frc.robot.subsystems.intake;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.IntakeConstants;

public class Intake extends SubsystemBase {
    private CANSparkMax sparkMax;

    private DigitalInput breakBeam;

    public Intake(){
        sparkMax = new CANSparkMax(IntakeConstants.sparkMaxCANID, CANSparkMax.MotorType.kBrushless);

        breakBeam = new DigitalInput(IntakeConstants.breakBeamChannel);
    }

    public void setPower(double input){
        sparkMax.set(input);
    }

    public boolean getBreakBeamValue(){
        return breakBeam.get();
    }
}
