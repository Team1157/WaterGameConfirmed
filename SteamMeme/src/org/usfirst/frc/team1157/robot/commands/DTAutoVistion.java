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
	double Kp = 0.45;
	double setSpeed;
	double error;
	double pti = 0;
	double width = 0, height = 0;
	double r1cX = 0, r1cY = 0;
	double r1h = 0, r1w = 0;
	double r2cX = 0, r2cY = 0;
	double r2h = 0, r2w = 0;
	double cX = 0, cY = 0;
	double tX = 0, tY = 0;
	double vX = 0, vY = 0;
	boolean logger = true;
	double averageVoltage;
	double distance;
	double forwardSpeed;
	boolean finished;
	
	
	AnalogInput distanceFinder = RobotMap.distanceFinder;

	public DTAutoVistion(boolean left) {
		if (left) {
			angle = 45;
		} else {
			angle = -45;
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
		
		
		
		pti = table.getNumber("pti", -1);
		width = table.getNumber("width", -1);
		height = table.getNumber("height", -1);
		r1cX = table.getNumber("r1cX", -1);
		r1cY = table.getNumber("r1cY", -1);
		r1h = table.getNumber("r1h", -1);
		r1w = table.getNumber("r1w", -1);
		r2cX = table.getNumber("r2cX", -1);
		r2cY = table.getNumber("r2cY", -1);
		r2h = table.getNumber("r2h", -1);
		r2w = table.getNumber("r2w", -1);

		tX = (r1cX + r2cX) / 2;
		// tY = (r1cY + r2cY) / 2;
		cX = width / 2;
		// cY = height/2;
		tX -= cX;
		// tY-=cX;
		vX = tX / cX;
		// vY = tY / cY;
		if (vX < -0.05 || 0.05 < vX)
			vX /= 2;
		else
			vX = 0;
		// if (vY < -0.05 || 0.05 < vY)
		// vY /= 2;
		// else
		// vY = 0;
		
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
			setSpeed = Kp * (error / angle);
		} else {
			setSpeed = -Kp * (error / angle);
		}
		if (!(Math.abs(Robot.gyro.getAngle() - angle) >= 2.5)) {
			setSpeed = 0;
		}
		SmartDashboard.putNumber("Auto drive: vX", vX);
		SmartDashboard.putNumber("Auto drive: tX", tX);
		SmartDashboard.putNumber("Auto drive: cX", cX);
		Robot.driveTrain.driveCartesianMecanum(vX, forwardSpeed, setSpeed, 0);
		
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
