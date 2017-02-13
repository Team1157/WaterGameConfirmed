package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.Robot;
import org.usfirst.frc.team1157.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DTAutoLazer extends Command {

    NetworkTable table = Robot.table;
    double angle = 0;
    double turnKp = 0.45;
    double moveKp = 0.22;
    double turnSpeed;
    double forwardSpeed;
    boolean finished;

    AnalogInput distanceFinder = RobotMap.distanceFinder;

    public DTAutoLazer(double angle) {
	this.angle = angle;
	requires(Robot.driveTrain);
	setTimeout(60);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		
		double width = table.getNumber("width", -1);
		double height = table.getNumber("height", -1);
		double cX = table.getNumber("cX", -1);
		double cY = table.getNumber("cY", -1);
		
		double xError = ((width/2) - cX)/(width/2);
		double yError = ((height/2) - cY)/(height/2);
		
		double speedX = moveKp * xError;
		double speedY = moveKp * yError;
		
		double tError = (Robot.gyro.getAngle() - angle)/90.0;
		turnSpeed = -turnKp * (tError);

		if (!(Math.abs(Robot.gyro.getAngle() - angle) >= 0.5)) {
			turnSpeed = 0;
		}
		
		if(table.getBoolean("locked", false)) {
		    Robot.driveTrain.driveCartesianMecanum(speedX, speedY, turnSpeed, 0);
		} else {
		    System.out.println("NOT LOCKED");
		    Robot.driveTrain.driveCartesianMecanum(0, 0, turnSpeed, 0);
		}
		SmartDashboard.putNumber("speedX", speedX);
		SmartDashboard.putNumber("speedY", speedY);
		SmartDashboard.putNumber("turnSpeed", turnSpeed);
		finished = isTimedOut();
		
	}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	return finished;
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
