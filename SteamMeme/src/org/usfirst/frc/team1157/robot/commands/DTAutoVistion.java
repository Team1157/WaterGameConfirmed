package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class DTAutoVistion extends Command {

	NetworkTable table = Robot.table;
	boolean left;
	double pti = 0;
	double width = 0, height = 0;
	double r1cX = 0, r1cY = 0;
	double r1h = 0, r1w = 0;
	double r2cX = 0, r2cY = 0;
	double r2h = 0, r2w = 0;
	double cX = 0, cY = 0;
	double tX = 0, tY = 0;
	double vX = 0, vY = 0;

	public DTAutoVistion(boolean left) {
		this.left = left;
		// Use requires() here to declare subsystem dependencies
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
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
		tX = tX - cX;
		double pvX = tX / cX;
		pvX/=2;
		if (-0.025 > pvX || pvX > 0.025) {
			Robot.driveTrain.driveCartesianMecanum(pvX, 0, 0, 0);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
