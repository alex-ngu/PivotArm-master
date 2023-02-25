// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.PivotArmSubsystem;

public class TopNodeCaseStatement extends CommandBase {
  PivotArmSubsystem pivotSub;
  ElevatorSubsystem elevSub;
  int count;
  public TopNodeCaseStatement(PivotArmSubsystem pivotsub, ElevatorSubsystem elevsub) {
    this.pivotSub = pivotsub;
    this.elevSub = elevsub;
    
    addRequirements(pivotsub, elevsub);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    count = 0;
    elevSub.init();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    elevSub.changeSetpoint(190);
    SmartDashboard.putNumber("Case Number", count);
    switch(count){
      case 0:
      SmartDashboard.putString("Position:", "High");
      
      if(elevSub.topPressed() || elevSub.isAtSetpoint()){
        if(elevSub.topPressed()){
          elevSub.changeSetpoint(elevSub.getEncoder() - 10);
        }
        SmartDashboard.putString("case 0", "done");
        count++;
      }
      break;



      case 1:
      pivotSub.newSetpoint(160);
      if(pivotSub.isAtSetPoint()){
        count++;
      }
      break;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return count > 1;
  }
}
