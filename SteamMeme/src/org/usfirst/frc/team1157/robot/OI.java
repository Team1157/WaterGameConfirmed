package org.usfirst.frc.team1157.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team1157.robot.commands.AutoTeleVistion;
import org.usfirst.frc.team1157.robot.commands.ConstantBackward;
import org.usfirst.frc.team1157.robot.commands.ConstantForward;
import org.usfirst.frc.team1157.robot.commands.ConstantLeft;
import org.usfirst.frc.team1157.robot.commands.ConstantRight;
import org.usfirst.frc.team1157.robot.commands.GearManipClose;
import org.usfirst.frc.team1157.robot.commands.GearManipOpen;
import org.usfirst.frc.team1157.robot.commands.GearManipSkillStop;
import org.usfirst.frc.team1157.robot.commands.RollerMove;
import org.usfirst.frc.team1157.robot.commands.TurnPiOff;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static Joystick stick1 = new Joystick(1);
	public static Joystick stick2 = new  Joystick(0);
	public static XboxController rumblePad = new XboxController(2);
	Button s1b1 = new JoystickButton(stick1, 1);
	Button s1b2 = new JoystickButton(stick1, 2);
	Button s1b3 = new JoystickButton(stick1, 3);
	Button s1b7 = new JoystickButton(stick1, 7);
	Button s1b6 = new JoystickButton(stick1, 6);
	Button s2b7 = new JoystickButton(stick2, 7);
	Button s2b9 = new JoystickButton(stick2, 9);
	Button s2b8 = new JoystickButton(stick2, 8);
	Button s2b10 = new JoystickButton(stick2, 10);
	Button s2b12 = new JoystickButton(stick2, 12);
	//public Button s1b10 = new JoystickButton(stick1, 10);
	
	public OI() {
	    //s1b10.whileHeld(new TurnPiOff());
		s1b1.whileHeld(new RollerMove(stick1));
		s2b12.whileHeld(new AutoTeleVistion());
		s1b6.whenPressed(new GearManipOpen());
		s1b7.whenPressed(new GearManipClose());
		s1b3.whenPressed(new GearManipSkillStop());
	}
	
	
	//// CREATING BUTTON
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
