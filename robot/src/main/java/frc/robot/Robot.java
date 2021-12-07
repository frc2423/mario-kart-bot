// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.util.Color;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.util.DriveHelper;
import frc.robot.util.NtHelper;

public class Robot extends TimedRobot {

  XboxController joystick = new XboxController(0);
  NeoMotor leftMotor = new NeoMotor(3);
  NeoMotor rightMotor = new NeoMotor(4);
  double lap = 0;
  int lastseen = 1; // pink = 0 orange = 1

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
    colorSensor.addColor("Pink", .75, 0, 0);
    colorSensor.addColor("Orange", .75, .25, 0);
    // register colors
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {

    System.out.println("color " + colorSensor.getRawColor().red + "  " + colorSensor.getRawColor().green + "  "
        + colorSensor.getRawColor().blue);

    if (colorSensor.isColor("Green")) {
      System.out.println("Green");
    } else if (colorSensor.isColor("Yellow")) {
      System.out.println("Yellow");
    } else if (colorSensor.isColor("Carpet")) {
      System.out.println("Carpet");
    }


    if (colorSensor.isColor("Pink")) {
     if (lastseen==1){
       lap++;
       NtHelper.setDouble("/robot/marioCart", lap);
       lastseen = 0;
     }
    } else if (colorSensor.isColor("Orange")) {
      if (lastseen==0) lastseen = 1;
    }

    // joystick.getY()
    double[] arcadeSpeeds = DriveHelper.getArcadeSpeeds(joystick.getY(), -joystick.getX(), false);
    leftMotor.setPercent(arcadeSpeeds[0]);
    rightMotor.setPercent(arcadeSpeeds[1]);

    System.out.println("color " + colorSensor.getRawColor().red + "  " + colorSensor.getRawColor().green + "  "
        + colorSensor.getRawColor().blue);

    if (colorSensor.isColor("Green")) {
      leftMotor.setPercent(.25);
      rightMotor.setPercent(.25);
      System.out.println("Green");
    } else if (colorSensor.isColor("Yellow")) {
      System.out.println("Yellow");
    } else if (colorSensor.isColor("Carpet")) {
      System.out.println("Carpet");
    }

    if (Robot.colorSensor.isColor("Green")) {
      // slow down!
    } else if (Robot.colorSensor.isColor("Yellow")) {
      // spin!
    } else if (Robot.colorSensor.isColor("Blue")) {
      // speed up!
    } else {
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
  public void testPeriodic() {
  }

  @Override
  public void simulationInit() {
  }
}
