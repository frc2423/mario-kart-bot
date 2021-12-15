// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
// import edu.wpi.first.wpilibj.util.Color;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.util.DriveHelper;
import frc.robot.util.NtHelper;

public class Robot extends TimedRobot {

  XboxController joystick = new XboxController(0);
  NeoMotor leftMotor = new NeoMotor(3);
  NeoMotor rightMotor = new NeoMotor(4);
  MarioStates marioStates = new MarioStates();

  private static ColorSensor colorSensor = new ColorSensor();

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {

    leftMotor.setInverted(true);
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {

  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {

  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    System.out.println("Teleop init");
    // set confidence interval
    colorSensor.setConfidence(0.8);
    colorSensor.addColor("Green", 0.25, .62, 0.13);
    colorSensor.addColor("Yellow", 0.47, 0.47, 0.06);
    colorSensor.addColor("Blue", 0, 0, 1);
    colorSensor.addColor("Purple", 1, 0, 1);
    colorSensor.addColor("Carpet", 0.33, 0.47, 0.2);
    // register colors
  }

/** This function is called periodically during operator control. */
// s
  

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {

    marioStates.run();

    System.out.println("color " + colorSensor.getRawColor().red + "  " + colorSensor.getRawColor().green + "  "
        + colorSensor.getRawColor().blue);


    //this block applies a deadband to the controler speeds and makes speed gradual (no wheelies)
    double[] arcadeSpeeds = DriveHelper.getArcadeSpeeds(joystick.getY(), -joystick.getX(), true);
    double leftSpeed = DriveHelper.applyDeadband(arcadeSpeeds[0]);
    double rightSpeed = DriveHelper.applyDeadband(arcadeSpeeds[1]);
    leftMotor.setPercent(leftSpeed);
    rightMotor.setPercent(rightSpeed);


    System.out.println("color " + colorSensor.getRawColor().red + "  " + colorSensor.getRawColor().green + "  "
        + colorSensor.getRawColor().blue);

    Color bubble =  colorSensor.getRawColor();
    NtHelper.setDouble("/robot/red", bubble.red);

    if (colorSensor.isColor("Green")){
      //** This function slows down motor speed to a quarter when the color green is spotted. */
      leftMotor.setPercent(.25);
      rightMotor.setPercent(.25);  
    }else if (colorSensor.isColor("Carpet")){
      leftMotor.setPercent(.50);
      rightMotor.setPercent(.50);
    }

    
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {

  }

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {
  }

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }

  @Override
  public void simulationInit() {
  }
}
//whenseegreenslowLISTENTOMEEEEEPLEASE