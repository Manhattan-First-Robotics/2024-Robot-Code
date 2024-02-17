package frc.robot.subsystems.intake;

import org.littletonrobotics.junction.AutoLog;

public interface IntakeIO {
    @AutoLog
    class IntakeIOInputs{
        public boolean isBrake;
        public double current;
        public double appliedOutput;
        public boolean beamBreak;
    }
    default void unpdateInputs(IntakeIOInputs inputs){}
    default void setPower(double topPower, double bottemPower){}
    default void isBrake(boolean brake){}
}
