package org.usfirst.frc.team1157.robot.commands;

import org.usfirst.frc.team1157.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoHangGearWithTurn extends CommandGroup {
	boolean m_left;
	double m_turnAngle;

    public AutoHangGearWithTurn(boolean left) {
    	
    	if(left == true){
    		m_left = true;
    		m_turnAngle = -60;
    	} else {
    		m_left = false;
    		m_turnAngle = 60;
    	}
    	requires(Robot.driveTrain);
    	addSequential(new AutoDriveStraight(0.45, 1.7));
    	addSequential(new AutoTurnAngle(m_turnAngle));
    	addSequential(new AutoVistion(m_turnAngle));
    	addSequential(new AutoDriveStraight(-0.45, 0.5));
    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
