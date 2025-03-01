// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.DoubleSupplier;


import com.revrobotics.spark.SparkMax;                 
import com.revrobotics.spark.SparkBase.*;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

public class DriveTrain extends SubsystemBase {
  SparkMax leftFront, leftRear, rightFront, rightRear;
  SparkMaxConfig leftFrontConfig, leftRearConfig, rightFrontConfig, rightRearConfig;


  /** Creates a new DriveTrain. */
  public DriveTrain() {
    leftFront = new SparkMax(11, MotorType.kBrushed);    // I changed these numbers (0-3) to match their RIO placement  Egeler 2/3/25
    leftRear = new SparkMax(12, MotorType.kBrushed);
    rightFront = new SparkMax(13, MotorType.kBrushed);
    rightRear = new SparkMax(14, MotorType.kBrushed);

    leftFrontConfig = new SparkMaxConfig();
    leftRearConfig = new SparkMaxConfig();
    rightFrontConfig = new SparkMaxConfig();
    rightRearConfig = new SparkMaxConfig();

    
    leftFront.configure(leftFrontConfig.
      inverted(false).                            // Check to make sure Inverted True is forward
      idleMode(IdleMode.kBrake).disableFollowerMode(), 
      ResetMode.kNoResetSafeParameters, 
      PersistMode.kPersistParameters);

    leftRear.configure(leftRearConfig.
      idleMode(IdleMode.kBrake).
      follow(11),
      ResetMode.kNoResetSafeParameters, 
      PersistMode.kPersistParameters);

    rightFront.configure(rightFrontConfig.
      inverted(true).                           // Check to make sure Inverted False is backward
      idleMode(IdleMode.kBrake), 
      ResetMode.kNoResetSafeParameters, 
      PersistMode.kPersistParameters);

    rightRear.configure(rightRearConfig.
      idleMode(IdleMode.kBrake). 
      follow(13),
      ResetMode.kNoResetSafeParameters, 
      PersistMode.kPersistParameters);

  }


 
  public Command driveTank(DoubleSupplier left, DoubleSupplier right) {
    // Inline construction of command goes here.
    return run(
        () -> {
          // SmartDashboard.putNumber("Left", left);
          // SmartDashboard.putNumber("Right", right);
          leftFront.set(left.getAsDouble()*0.52);
          rightFront.set(right.getAsDouble()*0.5);
        });
  }

  public Command arcadeDrive(DoubleSupplier forward, DoubleSupplier turn) {
    // Inline construction of command goes here.
    return run(
        () -> {
          // SmartDashboard.putNumber("Left", left);
          // SmartDashboard.putNumber("Right", right);
          leftFront.set(0.52*(forward.getAsDouble()-turn.getAsDouble()));
          rightFront.set(0.5*(forward.getAsDouble()+turn.getAsDouble()));
        });
  }

  public Command moveStraight(Double velocity) {
    return run(
      () -> {
        leftFront.set(velocity);
        rightFront.set(velocity);
      });
  }

  public Command turn(Double velocity) {
    return run(
      () -> {
        leftFront.set(velocity);
        rightFront.set(-1 * velocity);
      });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
