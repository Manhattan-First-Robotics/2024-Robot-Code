package frc.robot.subsystems.intake;


public class IntakeSimIO implements IntakeIO{

    private boolean isBreak;
    private double topPower;
    private double bottomPower;

    public IntakeSimIO(int CanIdTop, int CanIdBottom){
      

    }
    
    @Override
    public void updateInputs(IntakeIOInputs inputs){
        inputs.appliedOutputTop = topPower; 
        inputs.appliedOutputBottom = bottomPower; 
        inputs.beamBreak = false;
        inputs.isBrake = isBreak;
    }

    @Override
    public void setBrake(boolean isBreak){
        
    }
    @Override
    public void setPower(double top, double bottom){
       topPower = top;
       bottomPower = bottom;
    }
}
