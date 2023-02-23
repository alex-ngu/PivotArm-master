package frc.robot.commands;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.PivotArmSubsystem;

public class SetIdleMode extends CommandBase{
    private PivotArmSubsystem p_subs;

    public SetIdleMode(PivotArmSubsystem subs){
        p_subs = subs;
        addRequirements(subs);
    }

    @Override
    public void initialize(){
    }

    @Override
    public void execute(){
    }

    @Override
    public void end(boolean interrupted){
    }
    
    @Override
    public boolean isFinished(){
        return false;
    }
}
