package frc.robot;
import frc.robot.util.stateMachine.StateMachine;
import frc.robot.util.stateMachine.InitState;
import frc.robot.util.stateMachine.RunState;
import frc.robot.util.DriveHelper;
import edu.wpi.first.wpilibj.XboxController;

public class MarioStates extends StateMachine { 
    XboxController joystick = new XboxController(0);
    NeoMotor leftMotor = new NeoMotor(3);
    NeoMotor rightMotor = new NeoMotor(4);

    public MarioStates() {
        super("normal");
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
    }






}
