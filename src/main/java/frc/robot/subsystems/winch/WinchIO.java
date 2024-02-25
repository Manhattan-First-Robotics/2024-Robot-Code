package frc.robot.subsystems.winch;

import org.littletonrobotics.junction.AutoLog;

public interface WinchIO {
    @AutoLog
    class WinchIOInputs{
        public double current;
        public double appliedOutput;
        public double velocity;
        public double relativePosition;
    }

    default void updateInputs(WinchIOInputs inputs){

    }
    default void setPower(double power){
        
    }
}
