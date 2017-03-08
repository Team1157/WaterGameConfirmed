package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * TODO: add distance sensor, end()
 */
public class AutoVistion extends Command {

	NetworkTable table = Robot.table;
	double angle = 0;
	double turnKp = 0.45;
	double strafeKp = 0.3;
	double turnSpeed;
	double distance;
	double forwardSpeed;
	boolean finished;
	int tickker = 0;

	public AutoVistion(double angle) {
	    	this.angle = angle;
//		if (left) {
//			angle = 60;
//		} else {
//			angle = -60;
//		}
		// Use requires() here to declare subsystem dependencies
		requires(Robot.driveTrain);
		SmartDashboard.putNumber("strafeKp", strafeKp);
		setTimeout(15);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.hangGearSubsys.initializeCounter();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
		
		SmartDashboard.putNumber("distance", distance);
		
		double width = table.getNumber("width", -1);
		double r1cX = table.getNumber("r1cX", -1);
		double r2cX = table.getNumber("r2cX", -1);
		double screencenter = width/2;
		double midpoint = (r1cX + r2cX)/2;
		double error = (screencenter - midpoint)/screencenter;
		
		strafeKp = SmartDashboard.getNumber("strafeKp", strafeKp);
		double speedX = strafeKp * error;
		
		double delta = Math.abs(r1cX-r2cX);
		distance = 145.9746947*Math.pow(0.9883868325,delta);
		
		
//		if (distance > 12) {
//			forwardSpeed = SmartDashboard.getNumber("ForwardSpeedSlug", 0.25);
//		}
//		else if(distance <= 12 && distance > 3){
//			forwardSpeed = SmartDashboard.getNumber("ForwardSpeedVroom", 0.75);
//		}
//		else {
//			Robot.driveTrain.stop();
//			finished = true;
//		}
	

		error = (angle - Robot.gyro.getAngle())/15.0;
		turnSpeed = turnKp * (error);

		if (!(Math.abs(Robot.gyro.getAngle() - angle) >= 0.5)) {
			turnSpeed = 0;
		}
		
		if(table.getBoolean("locked", false)) {
		    SmartDashboard.putBoolean("locked", true);
		    Robot.driveTrain.driveCartesianMecanum(speedX, 0.25, turnSpeed, 0);
		    tickker = 0;
		} else {
		    SmartDashboard.putBoolean("locked", false);
		    System.out.println("NOT LOCKED");
		    if(tickker > 25 ) {
			Robot.driveTrain.driveCartesianMecanum(0, 0.1, turnSpeed, 0);
		    } else {
			Robot.driveTrain.driveCartesianMecanum(speedX, 0.25, turnSpeed, 0);
		    }
		    tickker++;
		}
		SmartDashboard.putNumber("Gyro Angle", Robot.gyro.getAngle());
		SmartDashboard.putNumber("∂", Math.abs(r1cX-r2cX));
		SmartDashboard.putNumber("distance", distance);
		SmartDashboard.putNumber("speedX", speedX);
		SmartDashboard.putNumber("turnSpeed", turnSpeed);
		finished = isTimedOut();
		if(Robot.hangGearSubsys.isSwitchSet()) {
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
