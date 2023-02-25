// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.PivotArmSubsystem;


public class lowPickup extends CommandBase {
  PivotArmSubsystem pivotSub;
  ElevatorSubsystem elevSub;
  int count;
  /** Creates a new lowPickup. */
  public lowPickup(PivotArmSubsystem pivotsub, ElevatorSubsystem elevsub) {
    this.pivotSub = pivotsub;
    this.elevSub = elevsub;
    count = 0;
    addRequirements(pivotsub, elevsub);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    elevSub.init();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    switch(count){
      case 0:
      elevSub.changeSetpoint(120);
      SmartDashboard.putString("Position:", "Mid");

      if(elevSub.topPressed() || elevSub.bottomPressed()){
        if(elevSub.topPressed()){
          elevSub.changeSetpoint(elevSub.getEncoder());
        }
      }
      if(elevSub.isAtSetpoint()){
        count++;
      }
      break;




      case 1:
      pivotSub.newSetpoint(63);
      if(pivotSub.isAtSetPoint()){
        count++;
      }
      break;



      
      case 2:
      elevSub.changeSetpoint(6);
      SmartDashboard.putString("Position:", "Low");

      if(elevSub.bottomPressed()){
         elevSub.changeSetpoint(elevSub.getEncoder() + 10);
      }
      
      if(elevSub.isAtSetpoint()){
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
    return count > 2;
  }
}
