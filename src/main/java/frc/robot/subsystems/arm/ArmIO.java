package frc.robot.subsystems.arm;

import org.littletonrobotics.junction.AutoLog;

public interface ArmIO {

    @AutoLog
    class ArmIOInputs{
        public double current;
        public double currentAngle;
        public double velocity;
        public double targetAngle;
        public double appliedPower;
        public double relitivePos1;
        public double relitivePos2;
    }

    default void updateInputs(ArmIOInputs inputs) {
    }

    default void setAngle(double angle) {
    }
}