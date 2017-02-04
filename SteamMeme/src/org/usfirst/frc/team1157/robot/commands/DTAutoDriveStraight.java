package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DTAutoDriveStraight extends Command {
	double speedTime;

    public DTAutoDriveStraight() {
    	SmartDashboard.putNumber("AutoDriveF Time", 3);
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	speedTime = SmartDashboard.getNumber("AutoDriveF Time", 3);
        setTimeout(speedTime);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.autoDriveForward();
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
