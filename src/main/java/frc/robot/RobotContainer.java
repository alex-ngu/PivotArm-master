package frc.robot;

import frc.robot.commands.ArmSafetyPosition;  
import frc.robot.commands.BringOut;
import frc.robot.commands.HighPosition;
import frc.robot.commands.LowPosition;
import frc.robot.commands.ManualElevatorDrive;
import frc.robot.commands.MidPosition;
import frc.robot.commands.PivotArmButtonCmd;
import frc.robot.commands.PivotArmJoystickCmd;
import frc.robot.commands.PivotStartCmd;
import frc.robot.commands.PivotMiddleCmd;
import frc.robot.commands.PivotHighCmd;
import frc.robot.commands.StartingConfiguration;
import frc.robot.commands.TopNode;
import frc.robot.commands.TuckedIn;
import frc.robot.commands.ZeroPosition;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.PivotArmSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {
  private final PivotArmSubsystem p_subsystem = new PivotArmSubsystem(); 
  private final ElevatorSubsystem elevator = new ElevatorSubsystem();
  private final ArmSafetyPosition arm = new ArmSafetyPosition(elevator);
  private final TuckedIn tucked = new TuckedIn(p_subsystem);
  private final PivotMiddleCmd mid = new PivotMiddleCmd(p_subsystem);
  private final ZeroPosition zero = new ZeroPosition(elevator);
  private final HighPosition high = new HighPosition(elevator);
  private final PivotArmButtonCmd manualUp = new PivotArmButtonCmd(p_subsystem, .3);
  private final PivotArmButtonCmd manualDown = new PivotArmButtonCmd(p_subsystem, -.3);

  //private final Joystick joystick1 = new Joystick(0);
  private final XboxController xController = new XboxController(0);

  public RobotContainer() {
   // p_subsystem.setDefaultCommand(new PivotArmJoystickCmd(p_subsystem, () -> xController.getLeftY()));


    configureBindings();
  }

  private void configureBindings() {
    new JoystickButton(xController, 1).onTrue(new TopNode()); // Button for setting it to a certain encoder
    new JoystickButton(xController, 2).onTrue(tucked); // Button for the starting position
    new JoystickButton(xController, 3).onTrue(new HighPosition(elevator)); // Button for the middle position
    new JoystickButton(xController, 4).onTrue(new PivotMiddleCmd(p_subsystem)); // Button for the high position
    new JoystickButton(xController, 5).whileTrue(manualUp); // Button for reseting the encoder position
    new JoystickButton(xController, 6).whileTrue(manualDown); // Button for driving the motor using the joystick
    //new JoystickButton(xController, 7).onTrue(new StartingConfiguration(tucked, arm)); // Button for the Starting Configuration
   // new JoystickButton(xController, 8).onTrue(new BringOut(mid, arm, zero)); // Button for bringing the arm out
    //new JoystickButton(xController, 9).onTrue(new TopNode(high, mid)); // Button for the top node
    new JoystickButton(xController, 10).onTrue(new LowPosition(elevator)); // Button for the low position of the elevator
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
