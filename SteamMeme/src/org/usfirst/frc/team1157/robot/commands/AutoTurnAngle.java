package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoTurnAngle extends Command {
	double Kp = 0.6;
	double setSpeed;
	double error;
	boolean finished;
	double turnAngle;
    public AutoTurnAngle(double angle) {
    	turnAngle = angle;
    	SmartDashboard.putNumber("Kp", Kp);
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
        finished = false;
        setTimeout(1);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() { 
    	SmartDashboard.putNumber("gyroAngle", Robot.gyro.getAngle());
    	Kp = SmartDashboard.getNumber("Kp", Kp);
    	error = (turnAngle - Robot.gyro.getAngle())/90.0;
    	setSpeed = Kp * (error);
    	
    	if (Math.abs(Robot.gyro.getAngle() - turnAngle) >= 2.5){
    		Robot.driveTrain.driveCartesianMecanum(0,0,setSpeed,0);
    	}
    	else {
    	    finished = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished  || isTimedOut();
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
