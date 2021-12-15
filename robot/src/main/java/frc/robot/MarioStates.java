package frc.robot;

import frc.robot.util.stateMachine.StateMachine;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.util.DriveHelper;
import frc.robot.util.stateMachine.InitState;
import frc.robot.util.stateMachine.RunState;

public class MarioStates extends StateMachine {
    XboxController joystick = new XboxController(0);
    NeoMotor leftMotor = new NeoMotor(3);
    NeoMotor rightMotor = new NeoMotor(4);
    double greenmodifier = 2;
  

    @InitState(name = "normal")
    public void initNormalState() {
        // initialization code for normal state
    
    }

    @RunState(name = "normal")
    public void runNormalState() {
        // code for normal state


        // transition code to other states
    }


    @InitState(name = "slow")
    public void initSlowState() {
        leftMotor.setInverted(true);
    }


   @RunState(name = "slow")
   public void runSlowState() {
        //this block applies a deadband to the controler speeds and makes speed gradual (no wheelies)
        double[] arcadeSpeeds = DriveHelper.getArcadeSpeeds(joystick.getY(), -joystick.getX(), true);
        double leftSpeed = DriveHelper.applyDeadband(arcadeSpeeds[0]);
        double rightSpeed = DriveHelper.applyDeadband(arcadeSpeeds[1]);
        leftMotor.setPercent(leftSpeed/greenmodifier);
        rightMotor.setPercent(rightSpeed/greenmodifier);

   }


    @InitState(name = "speed")
    public void initSpeedState() {

    }


    @RunState(name = "speed")
    public void runSpeedState() {

    }



}
