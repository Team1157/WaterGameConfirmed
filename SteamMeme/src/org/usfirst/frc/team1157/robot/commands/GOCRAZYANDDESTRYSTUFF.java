package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GOCRAZYANDDESTRYSTUFF extends Command {
    
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
	Robot.driveTrain.driveCartesianMecanum(Math.random(), Math.random(), Math.random(), Math.random());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	time = Timer.getFPGATimestamp();
	if (time > timeTo) {
	    Robot.driveTrain.driveCartesianMecanum(Math.random(), Math.random(), Math.random(), Math.random());
	    timeTo = time + 5;
	}
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
