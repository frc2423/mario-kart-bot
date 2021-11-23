package frc.robot;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorSensorV3;
// import frc.team2423.devices.Device;
import edu.wpi.first.wpilibj.I2C;

public class ColorSensor {
    private ColorSensorV3 mySensor;

    public ColorSensor(){
        mySensor = new ColorSensorV3(I2C.Port.kOnboard);
    }

    public boolean isMatch(double r,double g, double b){
        // return whether or not the colors match, specifically if they match the current color of the sensor
        return true;
    }

    public void confidenceInterval(double interval){
        // set the confidence interval
    }
    
    
}