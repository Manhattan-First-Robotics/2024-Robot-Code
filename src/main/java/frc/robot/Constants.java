// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;

public final class Constants {
  public static final boolean COMPETITION_MODE = false;

  // Xbox Controllers Port Indexes
  public static final int DRIVE_CONTROLLER_PORT = 0;
  public static final int OPERATOR_CONTROLLER_PORT = 1;
  public static final int TEST_CONTROLLER_PORT = 2;

  public static final int PDP_CAN_ID = 0;

  public static final double DRIVE_GEAR_RATIO = 1.0/8.45;
  public static final double DRIVE_WHEEL_RADIUS_STOCK = Units.inchesToMeters(3.0);
  public static final double TREAD_WARE_FACTOR = Units.inchesToMeters(0.0);
  public static final double DRIVE_WHEEL_RADIUS = DRIVE_WHEEL_RADIUS_STOCK - TREAD_WARE_FACTOR;

  public static final double TRACK_WIDTH = Units.inchesToMeters(19);

  public static final double DRIVE_MAX_SPEED = 3.0; // meters per second


public static final double DRIVE_MAX_ACCELERATION = 3.0; // meters per second squared
  public static final double DRIVE_MAX_ANGULAR_ACCELERATION = 3.0; // radians per second squared

  public static final class ArmConstants{
    public static final int sparkMaxCANID = 0;

    public static final double P = 0;
    public static final double I = 0;
    public static final double D = 0;
    public static final double maxOutput = 1;
    public static final double minOutput = -1;
    public static final int currentLimit = 80;

    public static final double intakeAngle = Math.toRadians(-10);
    public static final double driveAngle = Math.toRadians(90);
    public static final double ampShootAngle = Math.toRadians(60);
  }

  public static final class IntakeConstants {
    public static final int sparkMaxCANID = 0;
    
    public static final int breakBeamChannel = 0;

    public static final double intakeSpeed = 0.7;
    public static final double ampShootSpeed = 0.3;
  }
  
  public static final int LEFT_FRONT_MOTOR_CHANNEL = 1;
   public static final int LEFT_BACK_MOTOR_CHANNEL = 2;
    public static final int RIGHT_FRONT_MOTR_CHANNEL = 3;
     public static final int RIGHT_BACK_MOTOR_CHANNEL = 4;
}
