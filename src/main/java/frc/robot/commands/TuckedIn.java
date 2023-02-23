package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.PivotArmSubsystem;

public class TuckedIn extends CommandBase{
    private PivotArmSubsystem p_subs;

    public TuckedIn(PivotArmSubsystem subs){
        p_subs = subs;
        addRequirements(subs);
    }

    @Override
    public void initialize(){
    }

    @Override
    public void execute(){
        if(!p_subs.isTucked()){
        p_subs.resetEncoder();
        p_subs.newSetpoint(0);
        }
        else{
            p_subs.disablePid();
            p_subs.setManualSpeed(-.2);
           
        }
    }

    @Override
    public void end(boolean interrupted){
        p_subs.setManualSpeed(0);
        p_subs.enablePid();
    }
    
    @Override
    public boolean isFinished(){
        return !p_subs.isTucked();
    }
}
