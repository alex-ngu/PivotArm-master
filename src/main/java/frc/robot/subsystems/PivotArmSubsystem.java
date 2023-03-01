package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;

public class PivotArmSubsystem extends SubsystemBase{ // Pivot Arm Subsystem

    ///////////////// 
   //  Variables  //
  /////////////////
    private final CANSparkMax canspark = new CANSparkMax(14, MotorType.kBrushless);
    private final DigitalInput limitSwitch = new DigitalInput(2);
    private final RelativeEncoder rEnc;
    private final PIDController pid = new PIDController(0.1, 0, 0);
    private double before;
    private double setpoint;
    private boolean pidOn = true;
    private double manualSpeed = 0;
    private double encoderValue;


    
    /////////////////////////////////////////
   ///  Pivot Arm Subsystem Constructor  ///
  /////////////////////////////////////////
    public PivotArmSubsystem(){ // Instantiates the Talon Encoder variable and sets the tolerance for the PID
        canspark.setIdleMode(IdleMode.kBrake);
        canspark.setInverted(true);
        rEnc = canspark.getEncoder();
        setpoint = rEnc.getPosition();
    }

    
    public void enablePid(){
        pidOn = true; 
      }
    
      public void disablePid(){
        pidOn = false;
      }
      public boolean isPIDOn(){
        return pidOn;
      }
      public void newSetpoint(double setpoint){
        this.setpoint = setpoint;
    }
    /////////////////////////
   ///  Encoder Methods  ///
  /////////////////////////
    public double getEncoder(){ // Return the Encoder Value
        return rEnc.getPosition();
    }

    public void resetEncoder(){ // Resets the Encoder to a Position of 0
        rEnc.setPosition(0);
    }
    public void currentEncValtoSetpoint(){ //Change the current encoder count to the setpoint, so that the pid stays
        setpoint = getEncoder();
      }

                
    public boolean isTucked(){ // Returns if the limit switch is pressed or not
        return limitSwitch.get();
    }

    public boolean isAtSetPoint(){
        double error = setpoint - rEnc.getPosition();

        return Math.abs(error) < 3;
    }
    public void setManualSpeed(double inputSpeed){
        manualSpeed = inputSpeed;
      }



    //////////////////////////
   /// Pivot PID Methods  ///
  //////////////////////////
    public void compareErrors(){ // Resets the Integral Term if it reaches a certain limit
        double after = pid.getPositionError();
        if(before > 0 && after < 0){ // If the error changes from a positive to a negative, reset the previous error and the I term
            pid.reset();
        }
        else if(before < 0 && after > 0){ // If the error changes from a negative to a positive, reset the previous error and the I term
            pid.reset();
        }
        before = pid.getPositionError(); 
    }


    public void smartdashboard(){
        SmartDashboard.putNumber("[P] Encoder: ", getEncoder());
        SmartDashboard.putBoolean("[P] Limit Switch: ", limitSwitch.get());
        SmartDashboard.putNumber("[P] setpoint PIVOT", setpoint );
        SmartDashboard.putBoolean("[P] pid", isPIDOn());
    }



  
    
    ////////////////////////
   ///  Printing Method ///
  ////////////////////////
  
    public void periodic(){
        smartdashboard();
        encoderValue = getEncoder();
        compareErrors();
        double calcSpeed = 0;
      
        if(pidOn){
          calcSpeed = pid.calculate(encoderValue, setpoint); // SETS MOTOR SPEED TO CALCULATED PID SPEED 
        }
        else{
          calcSpeed = manualSpeed;
        }
        
        
        
        if(calcSpeed > .6){ 
          calcSpeed = .6;
        }
        else if(calcSpeed < -0.4){ 
          calcSpeed = -0.4;
        }
        canspark.set(calcSpeed);

       
    }
}