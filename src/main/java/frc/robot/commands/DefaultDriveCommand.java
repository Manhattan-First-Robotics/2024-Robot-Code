package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.DifferentialDrive.WheelSpeeds;
import edu.wpi.first.wpilibj2.command.Command;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import frc.robot.subsystems.drive.Drive;
import frc.robot.utility.Joystick;


public class DefaultDriveCommand extends Command {
  /** Creates a new DriveCommand. */
  private final Drive subsystem;
  private final DoubleSupplier xSpeed;
  private final DoubleSupplier zRotation;
  private WheelSpeeds wheelSpeeds;
  private DoubleSupplier targetRotation;
  private BooleanSupplier autoRotate;
  private PIDController pidController;

  public DefaultDriveCommand(Drive subsystem, DoubleSupplier xSpeed, DoubleSupplier zRotation, DoubleSupplier targetRotation, BooleanSupplier autoRotate) {
    this.subsystem = subsystem;
    this.xSpeed = xSpeed;
    this.zRotation = zRotation;
    this.targetRotation = targetRotation;
    this.autoRotate = autoRotate;
    addRequirements(subsystem);

    pidController = new PIDController(0.1, 0, 0);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    subsystem.setPowers(0, 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (autoRotate.getAsBoolean()) {
      // subsystem.setPowers(pidController.calculate(subsystem.getPose().getRotation().getDegrees(), targetRotation.getAsDouble()),
      // -pidController.calculate(subsystem.getPose().getRotation().getDegrees(), targetRotation.getAsDouble()));

      wheelSpeeds = DifferentialDrive.arcadeDriveIK(-Joystick.JoystickInput(xSpeed.getAsDouble(), 2, 0.02, .75),
        Joystick.JoystickInput(pidController.calculate(subsystem.getPose().getRotation().getDegrees(), targetRotation.getAsDouble()), 2, 0.02, .75), false);
      subsystem.setPowers(wheelSpeeds.left, wheelSpeeds.right);

      return;
      
    }

    wheelSpeeds = DifferentialDrive.arcadeDriveIK(-Joystick.JoystickInput(xSpeed.getAsDouble(), 2, 0.02, .75),
        Joystick.JoystickInput(zRotation.getAsDouble(), 2, 0.02, .75), false);
    subsystem.setPowers(wheelSpeeds.left, wheelSpeeds.right);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    subsystem.setPowers(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}