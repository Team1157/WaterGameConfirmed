package org.usfirst.frc.team1157.robot.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import org.usfirst.frc.team1157.robot.Robot;
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
	boolean usePID; 

	

	public DriveTrain() {
		usePID = false;
		frontRightMotor = createCANTalon(RobotMap.frontRightMotor, usePID);
		//new Motor.reverseSensor(true);
		frontLeftMotor = createCANTalon(RobotMap.frontLeftMotor, usePID);
		backRightMotor = createCANTalon(RobotMap.backRightMotor, usePID);
		backRightMotor.setInverted(true);
		backRightMotor.reverseSensor(true);
		backLeftMotor = createCANTalon(RobotMap.backLeftMotor, usePID);
		backLeftMotor.reverseSensor(true);
		robotDrive = new RobotDrive(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor);
		robotDrive.setSafetyEnabled(false);
	}
	
	private CANTalon createCANTalon(int motor, boolean usePID) {
		CANTalon newMotor = new CANTalon(motor);
		if(usePID){
			newMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
			newMotor.setProfile(0);
			newMotor.changeControlMode(TalonControlMode.Speed);
		}
		return newMotor;
	}

	public void initDefaultCommand() {
		setDefaultCommand(new DriveTrainJoystickDrive());

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	public void displayEncoderVelocity() {
		double velocityConstantFFR = frontRightMotor.getEncVelocity();
		SmartDashboard.putNumber("Encoder Velocity FR", velocityConstantFFR);
		double velocityConstantFFL = frontLeftMotor.getEncVelocity();
		SmartDashboard.putNumber("Encoder Velocity FL", velocityConstantFFL);
		double velocityConstantFBL = backLeftMotor.getEncVelocity();
		SmartDashboard.putNumber("Encoder Velocity BL", velocityConstantFBL);
		double velocityConstantFBR = backRightMotor.getEncVelocity();
		SmartDashboard.putNumber("Encoder Velocity BR", velocityConstantFBR);
	}
	
	//public void driveWithJoysticks(Joystick joystick1, Joystick joystick2) {
		//twistDamp = SmartDashboard.getNumber("Twist Damp", 0.5);
		//speedDamp = SmartDashboard.getNumber("Speed Damp", 0.5);
		//displayEncoderVelocity();
		//if (joystick2.getTwist() > 0.1 || joystick2.getTwist() < -0.1 || joystick2.getX() > 0.1
				//|| joystick2.getX() < -0.1 || joystick2.getY() > 0.1 || joystick2.getY() < -0.1) {
			//robotDrive.mecanumDrive_Cartesian(joystick2.getX() * speedDamp, joystick2.getY() * speedDamp,
			//		joystick2.getTwist() * twistDamp, Robot.gyro.getAngle());

		//} else {
			//stop();
		//}

	//}
	public void driveCartesianMecanum(double x, double y, double rotation, double gyroAngle) {
	    robotDrive.mecanumDrive_Cartesian(x, y, rotation, gyroAngle);
	}
	
	public void driveForwardConstant() {
		displayEncoderVelocity();
		double constantForward = SmartDashboard.getNumber("Forward Speed", 500);
		frontRightMotor.set(constantForward);
		frontLeftMotor.set(constantForward);
		backRightMotor.set(constantForward);
		backLeftMotor.set(constantForward);
	}

	public void driveBackwardConstant() {
		displayEncoderVelocity();
		double constantBackward = SmartDashboard.getNumber("Backward Speed", -500);
		frontRightMotor.set(constantBackward);
		frontLeftMotor.set(constantBackward);
		backLeftMotor.set(constantBackward);
		backRightMotor.set(constantBackward);
	}

	public void driveLeftConstant() {
		displayEncoderVelocity();
		double constantLeft = SmartDashboard.getNumber("Left Speed", 500);
		frontRightMotor.set(constantLeft);
		frontLeftMotor.set(-1 * constantLeft);
		backLeftMotor.set(constantLeft);
		backRightMotor.set(-1 * constantLeft);
	}

	public void driveRightConstant() {
		displayEncoderVelocity();
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
	
	public void autoDriveForward() {
		double speedDrive = SmartDashboard.getNumber("AutoSpeed", 0.5);
		frontRightMotor.set(speedDrive);
		frontLeftMotor.set(speedDrive);
		backLeftMotor.set(speedDrive);
		backRightMotor.set(speedDrive);
	}
}
