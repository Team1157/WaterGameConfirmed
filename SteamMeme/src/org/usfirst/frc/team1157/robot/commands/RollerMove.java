package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RollerMove extends Command {

	double speed;

	/*
	 * @param time the amount of time the roller should move
	 * 
	 * @param speed the speed from -1 to 1
	 */
	public RollerMove(double time, double speed) {
		this.speed = speed;
		requires(Robot.roller);
		setTimeout(time);
	}
	// Use requires() here to declare subsystem dependencies
	// eg. requires(chassis);

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.roller.move(speed);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.roller.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
