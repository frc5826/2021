// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.TimedRobot;

/**
 * The VM is configured to automatically run this class, and to call the methods corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot
{

    private Wheel wheel;
    private final int AZIMUTH_CAN_ID = 1;
    private final int DRIVE_CAN_ID = 2;

    private int count = -1;
    private int setpoint = 0;

    /**
     * This method is run when the robot is first started up and should be used for any
     * initialization code.
     */
    @Override
    public void robotInit() {
        TalonSRXConfiguration azimuthConfig = new TalonSRXConfiguration();
        // NOTE: ensure encoders are in-phase with motor direction. Encoders should increase
        // when azimuth motor runs in forward direction.
        azimuthConfig.primaryPID.selectedFeedbackSensor = FeedbackDevice.CTRE_MagEncoder_Relative;
        azimuthConfig.continuousCurrentLimit = 10;
        azimuthConfig.peakCurrentDuration = 0;
        azimuthConfig.peakCurrentLimit = 0;
        azimuthConfig.slot0.kP = 20;
        azimuthConfig.slot0.kI = 0.0;
        azimuthConfig.slot0.kD = 300.0;
        azimuthConfig.slot0.kF = 0.0;
        azimuthConfig.slot0.integralZone = 0;
        azimuthConfig.slot0.allowableClosedloopError = 0;
        azimuthConfig.motionAcceleration = 10_000;
        azimuthConfig.motionCruiseVelocity = 800;
        azimuthConfig.peakOutputForward = 0.75;
        azimuthConfig.peakOutputReverse = -0.75;


        TalonSRX azimuthTalon = new TalonSRX(AZIMUTH_CAN_ID);
        azimuthTalon.configAllSettings(azimuthConfig);

        CANSparkMax driveSpark = new CANSparkMax(DRIVE_CAN_ID, CANSparkMaxLowLevel.MotorType.kBrushless);

        wheel = new Wheel(azimuthTalon, driveSpark);
    }

    /**
     * This method is called every robot packet, no matter the mode. Use this for items like
     * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
     * <p>
     * This runs after the mode specific periodic methods, but before LiveWindow and
     * SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {}

    /**
     * This autonomous (along with the chooser code above) shows how to select between different
     * autonomous modes using the dashboard. The sendable chooser code works with the Java
     * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
     * uncomment the getString line to get the auto name from the text box below the Gyro.
     * <p>
     * You can add additional auto modes by adding additional comparisons to the switch structure
     * below with additional strings. If using the SendableChooser make sure to add them to the
     * chooser code above as well.
     */
    @Override
    public void autonomousInit() {}

    /** This method is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic() {}

    /** This function is called once when teleop is enabled. */
    @Override
    public void teleopInit() {


    }

    /** This method is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {
        if(this.count++/100 != this.setpoint){
            this.setpoint = this.count/100;
            System.out.println(this.setpoint);
        }
        wheel.set(this.setpoint, .3);
    }

    /** This function is called once when the robot is disabled. */
    @Override
    public void disabledInit() {
        wheel.stop();
    }

    /** This function is called periodically when disabled. */
    @Override
    public void disabledPeriodic() {}

    /** This function is called once when test mode is enabled. */
    @Override
    public void testInit() {}

    /** This method is called periodically during test mode. */
    @Override
    public void testPeriodic() {}
}
