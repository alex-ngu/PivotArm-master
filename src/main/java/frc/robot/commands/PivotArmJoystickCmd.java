// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.PivotArmSubsystem;

public class PivotArmJoystickCmd extends CommandBase {
  /** Creates a new Test. */
  PivotArmSubsystem pivotarmsubsystem;
  DoubleSupplier speed;
  public PivotArmJoystickCmd(PivotArmSubsystem pivotSub, DoubleSupplier speed) {
    pivotarmsubsystem = pivotSub;
    this.speed = speed;
    addRequirements(pivotarmsubsystem);
  }

  @Override
  public void initialize() {
    pivotarmsubsystem.disablePid();
  }

  @Override
  public void execute() {
    pivotarmsubsystem.setManualSpeed(speed.getAsDouble());
    SmartDashboard.getNumber("Manual Speed", speed.getAsDouble());
  }

  @Override
  public void end(boolean interrupted) {
    pivotarmsubsystem.enablePid();
    pivotarmsubsystem.currentEncValtoSetpoint();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}