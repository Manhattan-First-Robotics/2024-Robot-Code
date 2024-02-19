package frc.robot.subsystems.blinken;

import static frc.robot.Constants.*;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/*
 * super cool led controller
 * reference for blinken setup and color code table:
 * https://www.revrobotics.com/content/docs/REV-11-1105-UM.pdf
 * 
 */
public class Blinken extends SubsystemBase {

    private static Spark m_blinken;

    public Blinken(int pwmPort) {
        m_blinken = new Spark(pwmPort);
    }

    public void setColor (double color){
        if ((color >= -1.0) && (color <= 1.0)) {
            m_blinken.set(color);
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
