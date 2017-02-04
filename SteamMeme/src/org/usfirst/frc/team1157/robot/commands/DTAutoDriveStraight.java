package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DTAutoDriveStraight extends Command {
	double speedTime;
	boolean finished;

    public DTAutoDriveStraight() {
    	speedTime = SmartDashboard.getNumber("AutoDriveF Time", 3);
    	finished = false;
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
        setTimeout(speedTime);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.autoDriveForward();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	finished = true;
    	Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
