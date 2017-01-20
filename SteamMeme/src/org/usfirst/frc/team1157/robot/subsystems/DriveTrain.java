package org.usfirst.frc.team1157.robot.subsystems;

import org.usfirst.frc.team1157.robot.RobotMap;
import org.usfirst.frc.team1157.robot.commands.DriveTrainJoystickDrive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	Talon frontRightMotor;
	Talon frontLeftMotor;
	Talon backRightMotor;
	Talon backLeftMotor;
	RobotDrive robotDrive;
	
	
	public DriveTrain() {
    	frontRightMotor = new Talon(RobotMap.frontRightMotor);
    	frontLeftMotor = new Talon(RobotMap.frontLeftMotor);
    	backRightMotor = new Talon(RobotMap.backRightMotor);
    	backLeftMotor = new Talon(RobotMap.backLeftMotor);
    	robotDrive = new RobotDrive(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor);
    	
	
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new DriveTrainJoystickDrive());
    	
    	
    	
    	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void driveWithJoystick(Joystick joystick) {
    	robotDrive.mecanumDrive_Cartesian(joystick.getX(), joystick.getY(), joystick.getTwist(), 0);
    	
    	
    }
    
}

