package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DTTurnAngle extends Command {
	double Kp;
	double setSpeed;
	double error;
	boolean finished;
	double targetAngle;
	double initialAngle;
    public DTTurnAngle(double angle) {
    	SmartDashboard.putNumber("Kp", 0.5);
    	Kp = SmartDashboard.getNumber("Kp", 0.5);
    	targetAngle = angle;
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
        finished = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initialAngle = Robot.gyro.getAngle();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	error = targetAngle - Robot.gyro.getAngle();
    	setSpeed = Kp * (error/targetAngle);
    	
    	if (Robot.gyro.getAngle()<= targetAngle){
    		Robot.driveTrain.turnWithSpeed(setSpeed);
    	}
    	else {
    		end();
    	}
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
    }
}
