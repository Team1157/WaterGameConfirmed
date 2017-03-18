package org.usfirst.frc.team1157.robot.subsystems;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearManipSubsys extends Subsystem {

    static DigitalInput limitSwitch = new DigitalInput(1);
    static Counter counter = new Counter(limitSwitch);
    static DigitalInput limitSwitch2 = new DigitalInput(2);
    static Counter counter2 = new Counter(limitSwitch2);
    static Relay relay = new Relay(0, Relay.Direction.kForward);

    public GearManipSubsys() {

    }

    public boolean isSwitch1Set() {
	return counter.get() > 0;
    }

    public boolean isSwitch2Set() {
	return counter2.get() > 0;
    }

    public void initializeCounter() {
	counter.reset();
    }

    public void initializeCounter2() {
	counter2.reset();
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void Open() {
	relay.set(Relay.Value.kForward);
    }

    public void Close() {
	relay.set(Relay.Value.kForward);
    }

    public void Stop() {
	relay.set(Relay.Value.kOff);
	// Set the default command for a subsystem here.
	// setDefaultCommand(new MySpecialCommand());
    }

    @Override
    protected void initDefaultCommand() {
	// TODO Auto-generated method stub

    }
}
