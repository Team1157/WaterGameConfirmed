package org.usfirst.frc.team1157.robot.subsystems;
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

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
    	frontRightMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	frontRightMotor.setProfile(0);
    	frontRightMotor.changeControlMode(TalonControlMode.Speed);
    	frontRightMotor.reverseSensor(true);
    	frontLeftMotor = new CANTalon(RobotMap.frontLeftMotor);
    	frontLeftMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	frontLeftMotor.setProfile(0);
    	frontLeftMotor.changeControlMode(TalonControlMode.Speed);
    	frontLeftMotor.reverseSensor(true);
    	frontLeftMotor.setInverted(true);
    	backRightMotor = new CANTalon(RobotMap.frontRightMotor);
    	backRightMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	backRightMotor.setProfile(0);
    	backRightMotor.changeControlMode(TalonControlMode.Speed);
    	backRightMotor.reverseSensor(true);
    	backRightMotor = new CANTalon(RobotMap.backRightMotor);
    	backLeftMotor = new CANTalon(RobotMap.frontRightMotor);
    	backLeftMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	backLeftMotor.setProfile(0);
    	backLeftMotor.changeControlMode(TalonControlMode.Speed);
    	backLeftMotor.reverseSensor(true);
    	backLeftMotor = new CANTalon(RobotMap.backLeftMotor);
    	backLeftMotor.setInverted(true);
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
    		robotDrive.mecanumDrive_Cartesian(joystick1.getX()*speedDamp, joystick1.getY()*speedDamp, -joystick2.getTwist()*twistDamp, 0);
    	
    	}
    	
    }
    public void driveForwardConstant() {
    	double velocityConstantFFR = frontRightMotor.getEncVelocity();
    	SmartDashboard.putNumber("Encoder Velocity FR", velocityConstantFFR);
    	double velocityConstantFFL = frontLeftMotor.getEncVelocity();
    	SmartDashboard.putNumber("Encoder Velocity FL", velocityConstantFFL);
    	double velocityConstantFBL = backLeftMotor.getEncVelocity();
    	SmartDashboard.putNumber("Encoder Velocity BL", velocityConstantFBL);
    	double velocityConstantFBR = backRightMotor.getEncVelocity();
    	SmartDashboard.putNumber("Encoder Velocity BR", velocityConstantFBR);
    	double constantForward = SmartDashboard.getNumber("Forward Speed", 500);
    	frontRightMotor.set(constantForward);
    	frontLeftMotor.set(constantForward);
    	backRightMotor.set(constantForward);
    	backLeftMotor.set(constantForward);
    }
    
    public void driveBackwardConstant() {
    	double velocityConstantBFR = frontRightMotor.getEncVelocity();
    	SmartDashboard.putNumber("Encoder Velocity FR", velocityConstantBFR);
    	double velocityConstantBFL = frontLeftMotor.getEncVelocity();
    	SmartDashboard.putNumber("Encoder Velocity FL", velocityConstantBFL);
    	double velocityConstantBBL = backLeftMotor.getEncVelocity();
    	SmartDashboard.putNumber("Encoder Velocity BL", velocityConstantBBL);
    	double velocityConstantBBR = backRightMotor.getEncVelocity();
    	SmartDashboard.putNumber("Encoder Velocity BR", velocityConstantBBR);
    	double constantBackward = SmartDashboard.getNumber("Backward Speed", -500);
    	frontRightMotor.set(constantBackward);
    	frontLeftMotor.set(constantBackward);
    	backLeftMotor.set(constantBackward);
    	backRightMotor.set(constantBackward);
    }
    
    public void driveLeftConstant() {
    	double velocityConstantLFR = frontRightMotor.getEncVelocity();
    	SmartDashboard.putNumber("Encoder Velocity FR", velocityConstantLFR);
    	double velocityConstantLFL = frontLeftMotor.getEncVelocity();
    	SmartDashboard.putNumber("Encoder Velocity FL", velocityConstantLFL);
    	double velocityConstantLBL = backLeftMotor.getEncVelocity();
    	SmartDashboard.putNumber("Encoder Velocity BL", velocityConstantLBL);
    	double velocityConstantLBR = backRightMotor.getEncVelocity();
    	SmartDashboard.putNumber("Encoder Velocity BR", velocityConstantLBR);
    	double constantLeft = SmartDashboard.getNumber("Left Speed", 500);
    	frontRightMotor.set(constantLeft);
    	frontLeftMotor.set(-1 * constantLeft);
    	backLeftMotor.set(constantLeft);
    	backRightMotor.set(-1 * constantLeft);
    }
    public void driveRightConstant() {
    	double velocityConstantRFR = frontRightMotor.getEncVelocity();
    	SmartDashboard.putNumber("Encoder Velocity FR", velocityConstantRFR);
    	double velocityConstantRFL = frontLeftMotor.getEncVelocity();
    	SmartDashboard.putNumber("Encoder Velocity FL", velocityConstantRFL);
    	double velocityConstantRBL = backLeftMotor.getEncVelocity();
    	SmartDashboard.putNumber("Encoder Velocity BL", velocityConstantRBL);
    	double velocityConstantRBR = backRightMotor.getEncVelocity();
    	SmartDashboard.putNumber("Encoder Velocity BR", velocityConstantRBR);
    	double constantRight = SmartDashboard.getNumber("Right Speed", 500);
    	frontRightMotor.set(-1 * constantRight);
    	frontLeftMotor.set(constantRight);
    	backLeftMotor.set(-1 * constantRight);
    	backRightMotor.set(constantRight);
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



