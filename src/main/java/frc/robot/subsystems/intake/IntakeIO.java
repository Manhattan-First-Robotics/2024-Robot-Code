package frc.robot.subsystems.intake;

import org.littletonrobotics.junction.AutoLog;

public interface IntakeIO {
    @AutoLog
    class IntakeIOInputs{
        public boolean isBrake;
        public double appliedOutputTop;
        public double appliedOutputBottom;
        public boolean beamBreak;
    }
    default void updateInputs(IntakeIOInputs inputs){}
    default void setPower(double topPower, double bottemPower){}
    default void setBrake(boolean isBrake){}
    default boolean getBeamBreak(){ return false;}
}
