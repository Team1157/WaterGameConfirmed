package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.Robot;
import org.usfirst.frc.team1157.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * TODO: add distance sensor, end()
 */
public class DTAutoVistion extends Command {

	NetworkTable table = Robot.table;
	double angle;
	double turnKp = 0.45;
	double strafeKp = 0.22;
	double setSpeed;
	boolean logger = true;
	double averageVoltage;
	double distance;
	double forwardSpeed;
	boolean finished;
	
	
	AnalogInput distanceFinder = RobotMap.distanceFinder;

	public DTAutoVistion(boolean left) {
		if (left) {
			angle = 60;
		} else {
			angle = -60;
		}
		// Use requires() here to declare subsystem dependencies
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
		averageVoltage = distanceFinder.getAverageVoltage();
		SmartDashboard.putNumber("distance", distanceFinder.getAverageVoltage());
		distance = 0.0393701*(averageVoltage/0.977);
		double width = table.getNumber("width", -1);
		double height = table.getNumber("height", -1);
		double r1cX = table.getNumber("r1cX", -1);
		double r2cX = table.getNumber("r2cX", -1);
		double screencenter = width/2;
		double midpoint = (r1cX + r2cX)/2;
		double error = screencenter - midpoint;
		double speedX = strafeKp * error;		
		
		if (distance > 12) {
			forwardSpeed = SmartDashboard.getNumber("ForwardSpeedSlug", 0.25);
		}
		else if(distance <= 12 && distance > 3){
			forwardSpeed = SmartDashboard.getNumber("ForwardSpeedVroom", 0.75);
		}
		else {
			Robot.driveTrain.stop();
			finished = true;
		}
	

		error = angle - Robot.gyro.getAngle();
		// TODO:should this be error instead of angle?
		if (angle >= 0) {
			setSpeed = turnKp * (error / angle);
		} else {
			setSpeed = -turnKp * (error / angle);
		}
		if (!(Math.abs(Robot.gyro.getAngle() - angle) >= 2.5)) {
			setSpeed = 0;
		}
		
		Robot.driveTrain.driveCartesianMecanum(speedX, forwardSpeed, setSpeed, 0);
		
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
