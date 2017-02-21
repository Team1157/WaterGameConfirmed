package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GOCRAZYANDDESTRYSTUFF extends Command {

	double xd;
	double yd;
	double rot;
	double gyro;
	double time = Timer.getFPGATimestamp();
	double timeTo = 0;

	public GOCRAZYANDDESTRYSTUFF() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.driveTrain);
		setTimeout(60);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		time = Timer.getFPGATimestamp();
		timeTo = time + 5;
		double xd = (Math.random() - .5) * 1;
		double yd = (Math.random() - .5) * 1;
		double rot = (Math.random() - .5) * 1;
		double gyro = Math.random() * 360;
		Robot.driveTrain.driveCartesianMecanum(xd, yd, rot, gyro);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		time = Timer.getFPGATimestamp();
		if (time > timeTo) {
			time = Timer.getFPGATimestamp();
			xd = (Math.random() - .5) * 1;
			yd = (Math.random() - .5) * 1;
			rot = (Math.random() - .5) * 1;
			gyro = Math.random() * 360;
			timeTo = time + 5;
		}
		Robot.driveTrain.driveCartesianMecanum(xd, yd, rot, gyro);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut();
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
