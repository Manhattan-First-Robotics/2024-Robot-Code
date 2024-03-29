// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static frc.robot.Constants.DRIVE_CONTROLLER_PORT;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.AmpShootCommand;
import frc.robot.commands.ArmClimbCommand;
import frc.robot.commands.ArmDriveCommand;
import frc.robot.commands.AutoCommandConfig;
import frc.robot.commands.ClimbCommand;
import frc.robot.commands.DefaultDriveCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.ManualArmCommand;
import frc.robot.commands.SpeakerCommand;
import frc.robot.subsystems.arm.Arm;
import frc.robot.subsystems.drive.Drive;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.winch.Winch;
import frc.robot.utility.AutoCommandChooser;
import frc.robot.utility.RobotIdentity;
import frc.robot.utility.SubsystemFactory;

public class RobotContainer {

  private RobotIdentity identity;

  private Drive driveSubsystem;
  private Arm armSubsystem;
  private Intake intakeSubSystem;
  private Winch winchSubsystem;

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
    winchSubsystem = SubsystemFactory.createWinch(identity);

    AutoCommandConfig.init(driveSubsystem);

  }

  private void createCommands() {
    defaultDriveCommand = new DefaultDriveCommand(driveSubsystem,
        () -> driverController.getLeftTriggerAxis() - driverController.getRightTriggerAxis(),
        () -> -driverController.getLeftX(), 
        new DoubleSupplier() {
          @Override
          public double getAsDouble() {
              if (driverController.leftBumper().getAsBoolean()) {
                return 90;
              }

              if (driverController.rightBumper().getAsBoolean()){
                return Math.round(driveSubsystem.getPose().getRotation().getDegrees() / 90.0) * 90.0;
              }

              return 0;
          }
        },
        driverController.leftBumper().or(driverController.rightBumper())
        );
    driveSubsystem.setDefaultCommand(defaultDriveCommand);
  }

  private void configureButtonBindings() {
    if (identity == RobotIdentity.SIMULATION) {
      driverController.start().onTrue(new InstantCommand(() -> driveSubsystem.resetPose()));
      driverController.back().onTrue(new InstantCommand(() -> driveSubsystem.resetGyro()));
    }
    
    if (identity == RobotIdentity.ROBOT_2024) {
      driverController.a().onTrue(new IntakeCommand(driverController.b(), intakeSubSystem, armSubsystem));
      driverController.x().onTrue(new AmpShootCommand(driverController.b(), intakeSubSystem, armSubsystem));
      driverController.y().onTrue(new ArmDriveCommand(driverController.b(), armSubsystem));
      driverController.povLeft().onTrue(new ManualArmCommand(driverController.povLeft().negate(), armSubsystem, () -> driverController.getRightY()));
      driverController.povDown().onTrue(new ClimbCommand(driverController.b(), winchSubsystem, 0.5));
      driverController.povUp().onTrue(new SpeakerCommand(driverController.povUp().negate(), intakeSubSystem, armSubsystem));
      driverController.start().toggleOnTrue(new ArmClimbCommand(intakeSubSystem, armSubsystem));
    }
  }

  private void setupAutoChooser() {
    NamedCommands.registerCommand("intake", new IntakeCommand(new BooleanSupplier() {
      @Override
      public boolean getAsBoolean() {
          return false;
      }
    }, intakeSubSystem, armSubsystem));
    NamedCommands.registerCommand("shoot", new AmpShootCommand(new BooleanSupplier() {
      @Override
      public boolean getAsBoolean() {
        return false;
      }
    }, intakeSubSystem, armSubsystem));
    
    autoChooser = new AutoCommandChooser();

    // Register all the supported auto commands
    autoChooser.registerDefaultCreator("Do Nothing", null);
    autoChooser.registerCreator("Do Nothing", null);
    autoChooser.registerCreator("sp2Note1ReallyWide", () -> new PathPlannerAuto("sp2Note1ReallyWide"));
    autoChooser.registerCreator("sp2Note1Wide", () -> new PathPlannerAuto("sp2Note1Wide"));
    autoChooser.registerCreator("sp1Note1", () -> new PathPlannerAuto("sp1Note1"));
    autoChooser.registerCreator("sp2Note1", () -> new PathPlannerAuto("sp2Note1"));
    autoChooser.registerCreator("sp1Note4", () -> new PathPlannerAuto("sp1Note4"));
    autoChooser.registerCreator("sp1Note1Note4", () -> new PathPlannerAuto("sp1Note1Note4"));
    autoChooser.registerCreator("sp1Amp", () -> new PathPlannerAuto("sp1Amp"));
    

    // Setup the chooser in shuffleboard
    autoChooser.setup("Driver", 0, 0, 3, 1);
}

  public Command getAutonomousCommand() {
    return autoChooser.getAutonomousCommand();
  }
}
