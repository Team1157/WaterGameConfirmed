package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.OI;
import org.usfirst.frc.team1157.robot.Robot;

//import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DTJoystickDrive extends Command {

	double twistDamp = 0.35;
	double speedDamp = 0.5;
	
    public DTJoystickDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    		twistDamp = SmartDashboard.getNumber("Twist Damp", twistDamp);
    		speedDamp = SmartDashboard.getNumber("Speed Damp", speedDamp);
    		Robot.driveTrain.displayEncoderVelocity();
    		if (OI.stick2.getTwist() > 0.1 || OI.stick2.getTwist() < -0.1 || OI.stick2.getX() > 0.1
    				|| OI.stick2.getX() < -0.1 || OI.stick2.getY() > 0.1 || OI.stick2.getY() < -0.1) {
    			Robot.driveTrain.driveCartesianMecanum(OI.stick2.getX() * speedDamp, OI.stick2.getY() * speedDamp,
    					OI.stick2.getTwist() * twistDamp, Robot.gyro.getAngle());

    		} else {
    			Robot.driveTrain.stop();
    		}
    		if(OI.stick2.getRawButton(1)) {
    			Robot.gyro.reset();
    		}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
