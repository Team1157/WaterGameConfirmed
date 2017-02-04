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
	double turnAngle;
	double initialAngle;
    public DTTurnAngle(double angle) {
    	turnAngle = angle;
    	SmartDashboard.putNumber("Kp", 0.45);
    	SmartDashboard.putNumber("TurnAngle", 45);
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
        finished = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	turnAngle = SmartDashboard.getNumber("TurnAngle", 45);
    	initialAngle = Robot.gyro.getAngle();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() { 
    	SmartDashboard.putNumber("gyroAngle", Robot.gyro.getAngle());
    	Kp = SmartDashboard.getNumber("Kp", 1.5);
    	targetAngle = initialAngle-turnAngle;
    	error = targetAngle - Robot.gyro.getAngle();
    	if(targetAngle>=0){
    		setSpeed = Kp * (error/targetAngle);
    	}
    	else{
    		setSpeed=-Kp * (error/targetAngle); 
    	}
    	if (Math.abs(Robot.gyro.getAngle() - targetAngle) >= 2.5){
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
