package org.team1515.botifypremium.Subsystems;

import org.team1515.botifypremium.Robot;
import java.util.*;

import com.ctre.phoenix.music.Orchestra;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Singer extends SubsystemBase {
    private static WPI_TalonFX [] fxes =  { new WPI_TalonFX(25, "rio"), new WPI_TalonFX(26, "rio"), new WPI_TalonFX(27, "rio"), new WPI_TalonFX(31, "rio"), new WPI_TalonFX(32, "rio"), new WPI_TalonFX(33, "rio"), new WPI_TalonFX(34, "rio")};
    private  Orchestra orchestra;
    private static String song = "song.chrp";
    public boolean isFinished;
    
    public Singer(){
        ArrayList<TalonFX> instruments = new ArrayList<TalonFX>();
        for (int i = 0; i < fxes.length; ++i) {
            instruments.add(fxes[i]);
        }
        orchestra = new Orchestra(instruments, song);
        isFinished = false;
    }

    public void sing(){
        orchestra.play();
        end();
    }

    public void end(){
        orchestra.stop();
        orchestra.clearInstruments();
        isFinished = true;
    }
}
