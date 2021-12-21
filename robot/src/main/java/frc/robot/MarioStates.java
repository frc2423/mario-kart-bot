package frc.robot;

import frc.robot.util.stateMachine.StateMachine;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.util.DriveHelper;
import frc.robot.util.stateMachine.InitState;
import frc.robot.util.stateMachine.RunState;

public class MarioStates extends StateMachine {
    XboxController joystick = new XboxController(0);
    NeoMotor leftMotor = new NeoMotor(3);
    NeoMotor rightMotor = new NeoMotor(4);
    double yellowmodifier = 1.5;
    ColorSensor colorSensor;
    Timer timer;
    
    public MarioStates(){
        colorSensor = new ColorSensor();
        timer = new Timer();

        colorSensor.setConfidence(0.8);
        colorSensor.addColor("Green", 0.25, .62, 0.13);
        colorSensor.addColor("Yellow", 0.47, 0.47, 0.06);
        colorSensor.addColor("Blue", 0, 0, 1);
        colorSensor.addColor("Purple", 1, 0, 1);
        colorSensor.addColor("Carpet", 0.33, 0.47, 0.2);

        this.setState("normal");
        leftMotor.setInverted(true);

    }

    @InitState(name = "normal")
    public void initNormalState() {
        // initialization code for normal state
        
    
    }

    @RunState(name = "normal")
    public void runNormalState() {
        // code for normal state
        double[] arcadeSpeeds = DriveHelper.getArcadeSpeeds(joystick.getY(), -joystick.getX(), true);
        double leftSpeed = DriveHelper.applyDeadband(arcadeSpeeds[0]);
        double rightSpeed = DriveHelper.applyDeadband(arcadeSpeeds[1]);
        leftMotor.setPercent(leftSpeed*0.5);
        rightMotor.setPercent(rightSpeed*0.5);

        
    System.out.println("color " + colorSensor.getRawColor().red + "  " + colorSensor.getRawColor().green + "  "
    + colorSensor.getRawColor().blue);
        // transition code to other states
        if (colorSensor.isColor("Yellow")){
            this.setState("speed");
        }
    }


    @InitState(name = "slow")
    public void initSlowState() {
        leftMotor.setInverted(true);
    }


   @RunState(name = "slow")
   public void runSlowState() {
        

   }


    @InitState(name = "speed")
    public void initSpeedState() {
        timer.reset();
    }


    @RunState(name = "speed")
    public void runSpeedState() {
        //this block applies a deadband to the controler speeds and makes speed gradual (no wheelies)
        double[] arcadeSpeeds = DriveHelper.getArcadeSpeeds(joystick.getY(), -joystick.getX(), true);
        double leftSpeed = DriveHelper.applyDeadband(arcadeSpeeds[0]);
        double rightSpeed = DriveHelper.applyDeadband(arcadeSpeeds[1]);
        leftMotor.setPercent(leftSpeed);
        rightMotor.setPercent(rightSpeed);

        if (timer.get() > 7) {
            this.setState("normal");
        }

    }



}
