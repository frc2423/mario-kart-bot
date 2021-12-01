// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.util.Color;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.util.DriveHelper;

public class Robot extends TimedRobot {

  XboxController joystick = new XboxController(0);
  NeoMotor leftMotor = new NeoMotor(3);
  NeoMotor rightMotor = new NeoMotor(4);

  private static ColorSensor colorSensor = new ColorSensor();
  
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
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
    Robot.colorSensor.setConfidence(0.8);
    Robot.colorSensor.addColor("Green",0,1,0);
    Robot.colorSensor.addColor("Yellow",1,1,0);
    Robot.colorSensor.addColor("Blue",0,0,1);
    Robot.colorSensor.addColor("Purple",1,0,1);
    // register colors
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    // joystick.getY()
    double[] arcadeSpeeds = DriveHelper.getArcadeSpeeds(joystick.getY(), -joystick.getX(), false);
    leftMotor.setPercent(arcadeSpeeds[0]);
    rightMotor.setPercent(arcadeSpeeds[1]);
    if(Robot.colorSensor.isColor("Green")){
      // slow down!
    }
    else if(Robot.colorSensor.isColor("Yellow")){
      // spin!
    }
    else if(Robot.colorSensor.isColor("Blue")){
      // speed up!
    }
    else{
      // normal speed!
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
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}
}
