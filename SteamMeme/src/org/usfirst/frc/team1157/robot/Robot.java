//TODO:practicaly everything kappa
package org.usfirst.frc.team1157.robot;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team1157.robot.commands.Arm1Wave;
import org.usfirst.frc.team1157.robot.commands.AutoDriveForward;
import org.usfirst.frc.team1157.robot.commands.ExampleCommand;

import org.usfirst.frc.team1157.robot.commands.RollerUp;
import org.usfirst.frc.team1157.robot.subsystems.Roller;
import org.usfirst.frc.team1157.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1157.robot.subsystems.ExampleSubsystem;

import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */


public class Robot extends IterativeRobot {

    public static final Roller roller = new Roller();
    public static OI oi;
    public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
    Command autonomousCommand;

    SendableChooser<Command> chooser = new SendableChooser<>();

    private static final int IMG_WIDTH = 1080;
    private static final int IMG_HEIGHT = 960;

    VisionThread visionThread;
    double centerX = 0.0;

    private final Object imgLock = new Object();

    public static final DriveTrain driveTrain = new DriveTrain();
    public static final AnalogGyro gyro = new AnalogGyro(0);
    
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.	
     */
    @Override
    public void robotInit() {
    	
    gyro.calibrate();
    	
	oi = new OI();

	chooser.addDefault("even better auto", new Arm1Wave());
	chooser.addObject("arm 1", new RollerUp());


	SmartDashboard.putData("Auto mode", chooser);
	SmartDashboard.putNumber("Twist Damp", 0.5);
	SmartDashboard.putNumber("Speed Damp", 0.5);
	
	
	UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
	//camera.setResolution(IMG_WIDTH, IMG_HEIGHT);

	visionThread = new VisionThread(camera, new GripPipelineJay(), GripPipeline -> {
	    if (!GripPipeline.filterContoursOutput().isEmpty()) {
		Rect r = Imgproc.boundingRect(GripPipeline.filterContoursOutput().get(0));
	    }
	});
	visionThread.start();
	SmartDashboard.putNumber("Hlow",0);
	SmartDashboard.putNumber("Hhigh",255);
	SmartDashboard.putNumber("Slow",0);
	SmartDashboard.putNumber("Shigh",255);
	SmartDashboard.putNumber("Llow",0);
	SmartDashboard.putNumber("Lhigh",255);
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

