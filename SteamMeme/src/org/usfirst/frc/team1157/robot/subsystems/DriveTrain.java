package org.usfirst.frc.team1157.robot.subsystems;
import com.ctre.CANTalon;

import org.usfirst.frc.team1157.robot.RobotMap;
import org.usfirst.frc.team1157.robot.commands.DriveTrainJoystickDrive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	CANTalon frontRightMotor;
	CANTalon frontLeftMotor;
	CANTalon backRightMotor;
	CANTalon backLeftMotor;
	RobotDrive robotDrive;
	
	double twistDamp = 0.5;
	double speedDamp = 0.5;
	
	
	public DriveTrain() {
    	frontRightMotor = new CANTalon(RobotMap.frontRightMotor);
    	frontLeftMotor = new CANTalon(RobotMap.frontLeftMotor);
    	backRightMotor = new CANTalon(RobotMap.backRightMotor);
    	backLeftMotor = new CANTalon(RobotMap.backLeftMotor);
    	robotDrive = new RobotDrive(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor);
    	robotDrive.setSafetyEnabled(false);
	
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new DriveTrainJoystickDrive());
    	
    	
    	
    	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void driveWithJoysticks(Joystick joystick1, Joystick joystick2) {
    	twistDamp = SmartDashboard.getNumber("Twist Damp", 0.5);
    	speedDamp = SmartDashboard.getNumber("Speed Damp", 0.5);
    	
    	double velocityFR = frontRightMotor.getEncVelocity();
    	SmartDashboard.putNumber("Encoder Velocity FR", velocityFR);
    	double velocityFL = frontLeftMotor.getEncVelocity();
    	SmartDashboard.putNumber("Encoder Velocity FL", velocityFL);
    	double velocityBL = backLeftMotor.getEncVelocity();
    	SmartDashboard.putNumber("Encoder Velocity BL", velocityBL);
    	double velocityBR = backRightMotor.getEncVelocity();
    	SmartDashboard.putNumber("Encoder Velocity BR", velocityBR); 
    	if(joystick2.getTwist()>0.2||joystick2.getTwist()<-0.2||joystick1.getX()>0.1||joystick1.getX()<-0.1||joystick1.getY()>0.1||joystick1.getY()<-0.1){
    		robotDrive.mecanumDrive_Cartesian(joystick1.getX()*speedDamp, joystick1.getY()*speedDamp, joystick2.getTwist()*twistDamp, 0);
    	
    	}
    	
    }
    public void driveForwardConstant() {
    	double velocityConstantFFR = frontRightMotor.getEncVelocity();
    	SmartDashboard.putNumber("Encoder Velocity", velocityConstantFFR);
    	double velocityConstantFFL = frontLeftMotor.getEncVelocity();
    	SmartDashboard.putNumber("Encoder Velocity", velocityConstantFFL);
    	double velocityConstantFBL = backLeftMotor.getEncVelocity();
    	SmartDashboard.putNumber("Encoder Velocity", velocityConstantFBL);
    	double velocityConstantFBR = backRightMotor.getEncVelocity();
    	SmartDashboard.putNumber("Encoder Velocity", velocityConstantFBR);
    	double constantForward = SmartDashboard.getNumber("Forward Speed", 0.5);
    	frontRightMotor.set(constantForward);
    	frontLeftMotor.set(constantForward);
    	backRightMotor.set(constantForward);
    	backLeftMotor.set(constantForward);
    }
    
    public void driveBackwardConstant() {
    	double velocityConstantBFR = frontRightMotor.getEncVelocity();
    	SmartDashboard.putNumber("Encoder Velocity", velocityConstantBFR);
    	double velocityConstantBFL = frontLeftMotor.getEncVelocity();
    	SmartDashboard.putNumber("Encoder Velocity", velocityConstantBFL);
    	double velocityConstantBBL = backLeftMotor.getEncVelocity();
    	SmartDashboard.putNumber("Encoder Velocity", velocityConstantBBL);
    	double velocityConstantBBR = backRightMotor.getEncVelocity();
    	SmartDashboard.putNumber("Encoder Velocity", velocityConstantBBR);
    	double constantBackward = SmartDashboard.getNumber("Backward Speed", -0.5);
    	frontRightMotor.set(constantBackward);
    	frontLeftMotor.set(constantBackward);
    	backLeftMotor.set(constantBackward);
    	backRightMotor.set(constantBackward);
    }
    
    public void stop() {
    	frontRightMotor.set(0);
    	frontLeftMotor.set(0);
    	backLeftMotor.set(0);
    	backRightMotor.set(0);
    }

    
    public void autoDrive(double x, double y) {
    	robotDrive = new RobotDrive(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor);
    	robotDrive.mecanumDrive_Cartesian(x, y, 0, 0);
    
 
	}
}



