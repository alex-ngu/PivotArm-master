package frc.robot;
 

import frc.robot.commands.HighPosition;
import frc.robot.commands.LowPickup;
import frc.robot.commands.LowPosition;
import frc.robot.commands.MidPosition;
import frc.robot.commands.PivotArmButtonCmd;
import frc.robot.commands.PivotHighCmd;
import frc.robot.commands.PivotLowCmd;
import frc.robot.commands.TopNode;
import frc.robot.commands.TuckedFromBottom;
import frc.robot.commands.TuckedFromTop;
import frc.robot.commands.TuckedIn;
import frc.robot.commands.ZeroPosition;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.PivotArmSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {
  private final PivotArmSubsystem p_subsystem = new PivotArmSubsystem(); 
  private final ElevatorSubsystem elevator = new ElevatorSubsystem();
  private final TuckedIn tucked = new TuckedIn(p_subsystem);

  //private final Joystick joystick1 = new Joystick(0);
  private final XboxController xController = new XboxController(0);

  public RobotContainer() {
   // p_subsystem.setDefaultCommand(new PivotArmJoystickCmd(p_subsystem, () -> xController.getLeftY()));


    configureBindings();
  }

  private void configureBindings() {
    new JoystickButton(xController, 1).onTrue(new LowPickup(p_subsystem, elevator));
    new JoystickButton(xController, 2).onTrue(new TuckedFromBottom(p_subsystem, elevator));
    new JoystickButton(xController, 3).onTrue(new TopNode(p_subsystem, elevator)); // Button for the middle position
    new JoystickButton(xController, 4).onTrue(new TuckedFromTop(p_subsystem, elevator)); // Button for the high position
    new JoystickButton(xController, 5).onTrue(new MidPosition(elevator)); 
    new JoystickButton(xController, 6).onTrue(new ZeroPosition(elevator)); // Button for driving the motor using the joystick
    new JoystickButton(xController, 7).whileTrue(new PivotArmButtonCmd(p_subsystem, .3));
    new JoystickButton(xController, 8).whileTrue(new PivotArmButtonCmd(p_subsystem, -.3));
  }

  

  public Command getAutonomosCommand() {
    return null;
  }
}
