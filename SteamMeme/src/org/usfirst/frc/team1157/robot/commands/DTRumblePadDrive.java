package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.OI;
import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DTRumblePadDrive extends Command {
	
	double twistDamp = 0.35;
	double speedDamp = 0.5;
	XboxController rubmlePad = OI.rumblePad;

    public DTRumblePadDrive() {
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
		if (rubmlePad.getRawAxis(0) > 0.1 || rubmlePad.getRawAxis(0) < -0.1 || rubmlePad.getRawAxis(1) > 0.1
				|| rubmlePad.getRawAxis(1) < -0.1 || rubmlePad.getRawAxis(2) > 0.1 || rubmlePad.getRawAxis(2) < -0.1) {
			Robot.driveTrain.driveCartesianMecanum(rubmlePad.getRawAxis(1) * speedDamp, rubmlePad.getRawAxis(0) * speedDamp, rubmlePad.getRawAxis(2) * twistDamp, Robot.gyro.getAngle());
		}
		if(rubmlePad.getRawButton(6)) {
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
