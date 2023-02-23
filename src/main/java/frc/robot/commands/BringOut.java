package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class BringOut extends CommandBase{
    private PivotMiddleCmd mid;
    private ArmSafetyPosition elevator;
    private ZeroPosition zero;

    public BringOut(PivotMiddleCmd mposition, ArmSafetyPosition sposition, ZeroPosition zposition){
        mid = mposition;
        elevator = sposition;
        zero = zposition;
        addRequirements();
    }

    @Override
    public void initialize(){
    }

    @Override
    public void execute(){
        elevator.andThen(mid).andThen(zero);
    }

    @Override
    public void end(boolean interrupted){
    }
    
    @Override
    public boolean isFinished(){
        return true;
    }
}
