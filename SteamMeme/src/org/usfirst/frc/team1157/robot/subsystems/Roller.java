package org.usfirst.frc.team1157.robot.subsystems;

import org.usfirst.frc.team1157.robot.OI;
import org.usfirst.frc.team1157.robot.RobotMap;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Roller extends Subsystem {
	
		Jaguar motor = RobotMap.rollerMotor;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void up() {
    	motor.set((OI.stick1.getZ()-1)/(2));
    }
    public void down() {
    	motor.set((-(OI.stick1.getZ()-1))/(2));
    }
    public void stop() {
    	motor.set(0);
	}
}

