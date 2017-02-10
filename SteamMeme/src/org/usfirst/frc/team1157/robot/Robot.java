package org.usfirst.frc.team1157.robot;
import edu.wpi.first.wpilibj.Utility;

import org.usfirst.frc.team1157.robot.commands.Arm1Wave;
import org.usfirst.frc.team1157.robot.commands.AutoHangGearWithTurn;
import org.usfirst.frc.team1157.robot.commands.AutoDriveRight;
import org.usfirst.frc.team1157.robot.commands.DTAutoDriveStraight;
import org.usfirst.frc.team1157.robot.commands.DTTurnAngle;
import org.usfirst.frc.team1157.robot.commands.RollerMove;
import org.usfirst.frc.team1157.robot.subsystems.Roller;
import org.usfirst.frc.team1157.robot.subsystems.DriveTrain;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */


public class Robot extends IterativeRobot {
	
    boolean userButton;
    public static NetworkTable table;
    
    public Robot() {
	table = NetworkTable.getTable("/vision");
    }

    public static final Roller roller = new Roller();
    public static OI oi;
    Command autonomousCommand;

    SendableChooser<Command> chooser = new SendableChooser<>();

    private static final int IMG_WIDTH = 1280;
    private static final int IMG_HEIGHT = 720;

    double centerX = 0.0;

    public static final DriveTrain driveTrain = new DriveTrain();
    public static final ADXRS450_Gyro gyro = new ADXRS450_Gyro();
    
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.	
     */
    @Override
    public void robotInit() {
    	userButton = false;
    	
	oi = new OI();

	chooser.addDefault("even better auto", new Arm1Wave());
	chooser.addObject("arm 1", new RollerMove(0.9, 0.5));
	chooser.addObject("auto drive forward", new DTAutoDriveStraight (0, 0.5, 3, true));
	chooser.addObject("auto turn", new DTTurnAngle(45));
	chooser.addObject("hangGearFromLeft", new AutoHangGearWithTurn(true));
	chooser.addObject("hangGearFromRight", new AutoHangGearWithTurn(false));
	chooser.addObject("autoDriveRight", new AutoDriveRight());

	SmartDashboard.putData("Auto mode", chooser);
	SmartDashboard.putNumber("Twist Damp", 0.5);
	SmartDashboard.putNumber("Speed Damp", 0.5);
	SmartDashboard.putNumber("Forward Speed", 500);
	SmartDashboard.putNumber("Backward Speed", -500);
	SmartDashboard.putBoolean("KeepAlive", true);
	SmartDashboard.putNumber("exposure", -8);
	
	UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
	camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
	
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    @Override
    public void disabledInit() {

    }

    @Override
    public void disabledPeriodic() {
	Scheduler.getInstance().run();
	userButton = Utility.getUserButton();
	if(userButton == true) {
		SmartDashboard.putBoolean("KeepAlive", false);
	}
	
	
	
	
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString code to get the auto name from the text box below the Gyro
     *
     * You can add additional auto modes by adding additional commands to the
     * chooser code above (like the commented example) or additional comparisons
     * to the switch structure below with additional strings & commands.
     */
    @Override
    public void autonomousInit() {
	autonomousCommand = chooser.getSelected();
	

	/*
	 * String autoSelected = SmartDashboard.getString("Auto Selector",
	 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
	 * = new MyAutoCommand(); break; case "Default Auto": default:
	 * autonomousCommand = new ExampleCommand(); break; }
	 */

	// schedule the autonomous command (example)
	if (autonomousCommand != null)
	    autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
	Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
	// This makes sure that the autonomous stops running when
	// teleop starts running. If you want the autonomous to
	// continue until interrupted by another command, remove
	// this line or comment it out.
    	
	if (autonomousCommand != null)
	    autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
    	//table.putNumber("testANewNetworkVar2", 66);
    	//table.putNumber("asdf", 77);
    	//SmartDashboard.putNumber("testANewNetworkVar", x);
    	double gyroAngle = gyro.getAngle();
    	SmartDashboard.putNumber("gyroAngle",gyroAngle);
    	Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode
     */
    @Override
    public void testPeriodic() {
	LiveWindow.run();
    }
}

