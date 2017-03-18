package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


/**
 *
 */
public class GearManipOpen extends Command {
	

    public GearManipOpen() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.hangGearSubsys);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
	Robot.hangGearSubsys.initializeCounter();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	Robot.hangGearSubsys.Open();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.hangGearSubsys.isSwitch1Set();
    }

    // Called once after isFinished returns true
    protected void end() {
	Robot.hangGearSubsys.Stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
	Robot.hangGearSubsys.Stop();
    }
}
