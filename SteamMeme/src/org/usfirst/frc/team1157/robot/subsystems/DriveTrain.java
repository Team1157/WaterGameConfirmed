package org.usfirst.frc.team1157.robot.subsystems;
import com.ctre.CANTalon;

import org.usfirst.frc.team1157.robot.Robot;
import org.usfirst.frc.team1157.robot.RobotMap;
import org.usfirst.frc.team1157.robot.commands.DriveTrainJoystickDrive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

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
	
	
	public DriveTrain() {
    	frontRightMotor = new CANTalon(RobotMap.frontRightMotor);
    	frontLeftMotor = new CANTalon(RobotMap.frontLeftMotor);
    	backRightMotor = new CANTalon(RobotMap.backRightMotor);
    	backLeftMotor = new CANTalon(RobotMap.backLeftMotor);
    	robotDrive = new RobotDrive(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor);
    	
	
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new DriveTrainJoystickDrive());
    	
    	
    	
    	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void driveWithJoysticks(Joystick joystick1, Joystick joystick2) {
    	robotDrive.mecanumDrive_Cartesian(joystick1.getX(), joystick1.getY(), joystick2.getTwist(), Robot.gyro.getAngle());
    	
    	
    }
    
}

