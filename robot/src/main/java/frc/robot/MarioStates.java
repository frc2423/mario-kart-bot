package frc.robot;

import frc.robot.util.stateMachine.StateMachine;
import frc.robot.util.stateMachine.InitState;
import frc.robot.util.stateMachine.RunState;
import frc.robot.util.DriveHelper;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.Timer;

public class MarioStates extends StateMachine { 
    XboxController joystick = new XboxController(0);
    NeoMotor leftMotor = new NeoMotor(3);
    NeoMotor rightMotor = new NeoMotor(4);
    Timer StateTimer = new Timer();
    ColorSensor colorSensor = new ColorSensor();

    public MarioStates() {
        super("normal");
        colorSensor.setConfidence(0.8);
        colorSensor.addColor("Green", 0.25, .62, 0.13);
        colorSensor.addColor("Yellow", 0.47, 0.47, 0.06);
        colorSensor.addColor("Blue", 0, 0, 1);
        colorSensor.addColor("Purple", 1, 0, 1);
        colorSensor.addColor("Carpet", 0.33, 0.47, 0.2);
    }
    

    @InitState(name = "normal")
    public void initNormalState() {
        // initialization code for normal state
    
    }

    @RunState(name = "normal")
    public void runNormalState() {
        // code for normal state


        // transition code to other 
        double[] arcadeSpeeds = DriveHelper.getArcadeSpeeds(joystick.getY(), -joystick.getX(), true);
        double leftSpeed = DriveHelper.applyDeadband(arcadeSpeeds[0]);
        double rightSpeed = DriveHelper.applyDeadband(arcadeSpeeds[1]);
        leftMotor.setPercent(leftSpeed);
        rightMotor.setPercent(rightSpeed);
        boolean match = colorSensor.isColor("Green");
        if (match == true){
            setState("slow");
        }
    }


    @InitState(name = "slow")
    public void initSlowState(){
        StateTimer.reset();
        StateTimer.start();

    }

    @RunState(name = "slow")
    public void runSlowState(){
        double[] arcadeSpeeds = DriveHelper.getArcadeSpeeds(joystick.getY(), -joystick.getX(), true);
        double leftSpeed = DriveHelper.applyDeadband(arcadeSpeeds[0]);
        double rightSpeed = DriveHelper.applyDeadband(arcadeSpeeds[1]);
        leftSpeed = leftSpeed / 2;
        rightSpeed = rightSpeed / 2;
        leftMotor.setPercent(leftSpeed);
        rightMotor.setPercent(rightSpeed);
        if (StateTimer.get() > 5) {
            setState("normal");

        }
    }





}
