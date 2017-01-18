package org.usfirst.frc.team1157.robot.subsystems;

import org.usfirst.frc.team1157.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm1 extends Subsystem {
	
		Victor motor = RobotMap.Arm1Motor;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void up() {
    	motor.set(1);
    }
    public void down() {
    	motor.set(-1);
    }
    public void stop() {
    	motor.set(0);
	}
}

