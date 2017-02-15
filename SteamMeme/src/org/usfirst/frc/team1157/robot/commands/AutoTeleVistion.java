package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * TODO: add distance sensor, end()
 */
public class AutoTeleVistion extends Command {

	NetworkTable table = Robot.table;
	double initialAngle;
	double strafeKp = 0.22;
	double setSpeed;
	boolean logger = true;
	double averageVoltage;
	double distance;
	double forwardSpeed;
	boolean finished;
	double offset = 0;
	double turnKp;

	public AutoTeleVistion() {

		// Use requires() here to declare subsystem dependencies
		requires(Robot.driveTrain);
		SmartDashboard.putNumber("strafeKp", 0.5);
		SmartDashboard.putNumber("offset", 0);
		setTimeout(60);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		initialAngle = Robot.gyro.getAngle();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
		turnKp = SmartDashboard.getNumber("Kp", 0.6);
		SmartDashboard.putNumber("distance", distance);
		
		double width = table.getNumber("width", -1);
		double r1cX = table.getNumber("r1cX", -1);
		double r2cX = table.getNumber("r2cX", -1);
		double screencenter = width/2;
		double midpoint = (r1cX + r2cX)/2;
		offset = SmartDashboard.getNumber("offset", 0);
		double error = (screencenter - midpoint + offset)/screencenter;
		
		strafeKp = SmartDashboard.getNumber("strafeKp", 0.001);
		double speedX = strafeKp * error;
		
		double delta = Math.abs(r1cX-r2cX);
		distance = 145.9746947*Math.pow(0.9883868325,delta);
		
		error = (Robot.gyro.getAngle() - initialAngle)/15.0;
		setSpeed = -turnKp * (error);

		if (!(Math.abs(Robot.gyro.getAngle() - initialAngle) >= 0.5)) {
			setSpeed = 0;
		}
		
		if(table.getBoolean("locked", false)) {
		    Robot.driveTrain.driveCartesianMecanum(speedX, 0.15, setSpeed, 0);
		    //Robot.driveTrain.driveCartesianMecanum(0, 0, setSpeed, 0);
		} else {
		    System.out.println("NOT LOCKED");
		    Robot.driveTrain.driveCartesianMecanum(0, 0, setSpeed, 0);
		}
		SmartDashboard.putNumber("∂", Math.abs(r1cX-r2cX));
		SmartDashboard.putNumber("distance", distance);
		SmartDashboard.putNumber("speedX", speedX);
		SmartDashboard.putNumber("turnSpeed", setSpeed);
		finished = isTimedOut();
		if(distance < 5) {
		    finished = true;
		    Robot.driveTrain.stop();
		}
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return finished;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrain.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
