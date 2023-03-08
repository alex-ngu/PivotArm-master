package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.PivotArmSubsystem;

public class PivotLowCmd extends CommandBase{
    private PivotArmSubsystem p_subs;

    public PivotLowCmd(PivotArmSubsystem subs){
        p_subs = subs;
        addRequirements(subs);
    }

    @Override
    public void initialize(){
    }

    @Override
    public void execute(){
        SmartDashboard.putNumber("Pivot Encoder: ", p_subs.getEncoder());
        p_subs.newSetpoint(38);
    }

    @Override
    public boolean isFinished(){
        return p_subs.isAtSetPoint();
    }

}
