package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DTAutoDriveStraight extends Command {
	double m_speedTime;
	double m_speedSide;
	double m_speedDrive;
	boolean m_relativeAngle;
	double initialAngle;

    public DTAutoDriveStraight(double speedSide, double speedDrive, double speedTime, boolean relativeAngle) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
        m_relativeAngle = relativeAngle;
        m_speedTime = speedTime;
        m_speedSide = speedSide;
        m_speedDrive = speedDrive;
        

    }

    // Called just before this Command runs the first time
    protected void initialize() {
        setTimeout(m_speedTime);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(m_relativeAngle == false) {
    		initialAngle = Robot.gyro.getAngle();
    	}else{
    		initialAngle = 0;
    	}
		
		Robot.driveTrain.driveCartesianMecanum(0, m_speedDrive, 0, 0);
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
