// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static frc.robot.Constants.DRIVE_CONTROLLER_PORT;

import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.AmpShootCommand;
import frc.robot.commands.AutoCommandConfig;
import frc.robot.commands.DefaultDriveCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.subsystems.arm.Arm;
import frc.robot.subsystems.drive.Drive;
import frc.robot.subsystems.intake.Intake;
import frc.robot.utility.AutoCommandChooser;
import frc.robot.utility.RobotIdentity;
import frc.robot.utility.SubsystemFactory;

public class RobotContainer {

  private RobotIdentity identity;

  private Drive driveSubsystem;
  private Arm armSubsystem;
  private Intake intakeSubSystem;

  private DefaultDriveCommand defaultDriveCommand;

  private AutoCommandChooser autoChooser;

  // The robot's subsystems and commands are defined here...

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController driverController = new CommandXboxController(DRIVE_CONTROLLER_PORT);

  public RobotContainer() {
    identity = RobotIdentity.getIdentity();

    createSubsystems(); // Create our subsystems.
    createCommands(); // Create our commands
    configureButtonBindings(); // Configure the button bindings
    setupAutoChooser(); // Setup the auto chooser
  }

  private void createSubsystems() {
    driveSubsystem = SubsystemFactory.createDriveTrain(identity);
    armSubsystem = SubsystemFactory.createarm(identity);
    intakeSubSystem = SubsystemFactory.createIntake(identity);

    AutoCommandConfig.init(driveSubsystem);

  }

  private void createCommands() {
    defaultDriveCommand = new DefaultDriveCommand(driveSubsystem,
        () -> driverController.getLeftTriggerAxis() - driverController.getRightTriggerAxis(),
        () -> -driverController.getLeftX());
    driveSubsystem.setDefaultCommand(defaultDriveCommand);
  }

  private void configureButtonBindings() {
    driverController.start().onTrue(new InstantCommand(() -> driveSubsystem.resetPose()));
    driverController.back().onTrue(new InstantCommand(() -> driveSubsystem.resetGyro()));
    
    if (identity == RobotIdentity.ROBOT_2024) {
      driverController.a().onTrue(new IntakeCommand(driverController.b(), intakeSubSystem, armSubsystem));
      driverController.x().onTrue(new AmpShootCommand(driverController.x().negate(), intakeSubSystem, armSubsystem));
    }
  }

  private void setupAutoChooser() {
    autoChooser = new AutoCommandChooser();

    // Register all the supported auto commands
    autoChooser.registerDefaultCreator("Do Nothing", null);

    // Test auto commands that we only register with the chooser if we are not running in competition
    if (!Constants.COMPETITION_MODE) {
        autoChooser.registerCreator("Test", () -> new PathPlannerAuto("TestAuto"));
    }

    // Setup the chooser in shuffleboard
    autoChooser.setup("Driver", 0, 0, 3, 1);
}

  public Command getAutonomousCommand() {
    return autoChooser.getAutonomousCommand();
  }
}
