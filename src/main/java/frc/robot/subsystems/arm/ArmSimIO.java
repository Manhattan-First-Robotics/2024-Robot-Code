package frc.robot.subsystems.arm;

public class ArmSimIO implements ArmIO{
   
    private double targetAngle;
    private double power;

    public ArmSimIO(int CanIdLeft, int CanIdRight){
       
    }
    @Override
    public void updateInputs(ArmIOInputs inputs) {
        inputs.current = 0;
        inputs.currentAngle = 0;
        inputs.velocity = 0;
        inputs.targetAngle = targetAngle;
        inputs.appliedPower = power;
        inputs.relitivePos1 = 0;
        inputs.relitivePos2 = 0;
    }
    @Override
    public void setAngle(double angle){
        targetAngle = angle;
    }
    public void setPower(double power){
        this.power = power;
    }
}
