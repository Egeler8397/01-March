// Copyright (c) FIRST and other WPILib contributors.
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/** * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static class ArmConstants {
      // Define Arm position constants
      public static Double positionIntakeCoral      = -15.0;
      public static Double positionClimbEnd         = -0.368;
      public static Double positionIntakeAlgae      = -20.0;
      public static Double positionRemoveAlgaeLow   = -10.0;
      public static Double positionClimbStart       = -0.233;
      public static Double positionRemoveAlgaeHigh  = -5.0;

      // Define Arm position limits
      public static Double armFrontLimit            = 0.0;
      public static Double armRearLimit             = -20.0;

      // Define Arm velocity limit
      public static Double armVelocityLimit         = 0.15;

      // Define Arm PID constants
      public static Double armkP                    = 17.5;
      public static Double armkI                    = 0.0;
      public static Double armkD                    = 0.8;
  }
}
