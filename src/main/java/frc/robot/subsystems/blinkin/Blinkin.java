package frc.robot.subsystems.blinkin;

import static frc.robot.Constants.*;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DriverStation;

/*
 * super cool led controller
 * reference for blinkin setup and color code table:
 * https://www.revrobotics.com/content/docs/REV-11-1105-UM.pdf
 * 
 */
public class Blinkin extends SubsystemBase {

    private static Spark m_blinkin;

    public Blinkin(int pwmPort) {
        m_blinkin = new Spark(pwmPort);
    }

    public void setColor (double color){
        if ((color >= -1.0) && (color <= 1.0)) {
            m_blinkin.set(color);
          }
    }

    public void setRed () {
        this.setColor(LED_RED);
    }
    public void setBlue () {
        this.setColor(LED_BLUE);
    }
    public void setGreen () {
        this.setColor(LED_GREEN);
    }
    public void setDefault () {
        this.setColor(LED_WHITE);
    }
    public void setOff () {
        this.setColor(LED_BLACK);
    }
    public void letsParty () {
        this.setColor(LED_PARTY);
    }

    public void setAllianceColor() {
        var alliance = DriverStation.getAlliance();
        if (alliance.isPresent()) {
            if(alliance.get() == DriverStation.Alliance.Red){
                this.setRed();
            } else if (alliance.get() == DriverStation.Alliance.Blue) {
                this.setBlue();
            } else {
                this.setDefault();
            }
        } else {
            this.setDefault();
        }
    }

    @Override 
    public void periodic(){
        if (this.getCurrentCommand() !=null){
            Logger.recordOutput("Blinken/CurrentCommand", this.getCurrentCommand().getName());
        }
        else{
            Logger.recordOutput("Blinken/CurrentCommand","none");
        }
    }


}
