package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.PivotArmSubsystem;

public class TopNode extends CommandBase{
    private ElevatorSubsystem elevSub;
    private PivotArmSubsystem pivotSub;
    private HighPosition high;
    private PivotMiddleCmd pivotParallel;
    private int count;

    public TopNode(){
        count = 0;
        high = new HighPosition(elevSub);
        pivotParallel = new PivotMiddleCmd(pivotSub);
        addRequirements();
    }

    @Override
    public void initialize(){
    }

    @Override
    public void execute(){ // Add Elevator Command
        high.andThen(pivotParallel);
    }

    @Override
    public void end(boolean interrupted){
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}
