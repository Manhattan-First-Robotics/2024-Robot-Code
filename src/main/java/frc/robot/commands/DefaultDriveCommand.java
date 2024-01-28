package frc.robot.commands;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.DifferentialDrive.WheelSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import java.util.function.DoubleSupplier;

import frc.robot.subsystems.drive.Drive;
import frc.robot.utility.Joystick;


public class DefaultDriveCommand extends Command {
  /** Creates a new DriveCommand. */
  private final Drive subsystem;
  private final DoubleSupplier xSpeed;
  private final DoubleSupplier zRotation;
  private WheelSpeeds wheelSpeeds;

  public DefaultDriveCommand(Drive subsystem, DoubleSupplier xSpeed, DoubleSupplier zRotation) {
    this.subsystem = subsystem;
    this.xSpeed = xSpeed;
    this.zRotation = zRotation;
    addRequirements(subsystem);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    subsystem.setPowers(0, 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    wheelSpeeds = DifferentialDrive.arcadeDriveIK(-Joystick.JoystickInput(xSpeed.getAsDouble(), 2, 0.02, .75),
        -Joystick.JoystickInput(zRotation.getAsDouble(), 2, 0.02, .75), false);
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