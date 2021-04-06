// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

/**
 * The VM is configured to automatically run this class, and to call the methods corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot
{
    private CANSparkMax m_leftFrontMotor;
    private CANSparkMax m_rightFrontMotor;
    private CANSparkMax m_leftBackMotor;
    private CANSparkMax m_rightBackMotor;

    private SpeedControllerGroup leftSpeedControllers;
    private SpeedControllerGroup rightSpeedControllers;
    private DifferentialDrive diffDrive;

    public static Joystick joystick = new Joystick(0);


    /**
     * This method is run when the robot is first started up and should be used for any
     * initialization code.
     */
    @Override
    public void robotInit()
    {

        m_leftFrontMotor = new CANSparkMax(4, CANSparkMaxLowLevel.MotorType.kBrushless);
        m_rightFrontMotor = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless);
        m_leftBackMotor = new CANSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushless);
        m_rightBackMotor = new CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless);

        m_leftFrontMotor.restoreFactoryDefaults();
        m_rightFrontMotor.restoreFactoryDefaults();
        m_leftBackMotor.restoreFactoryDefaults();
        m_rightBackMotor.restoreFactoryDefaults();

        m_rightFrontMotor.setInverted(true);
        m_rightBackMotor.setInverted(true);

        leftSpeedControllers = new SpeedControllerGroup(m_leftFrontMotor, m_leftBackMotor);
        rightSpeedControllers = new SpeedControllerGroup(m_rightFrontMotor, m_rightBackMotor);
        diffDrive = new DifferentialDrive(leftSpeedControllers, rightSpeedControllers);


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
    public void autonomousInit()
    {

    }

    /** This method is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic()
    {

    }

    /** This function is called once when teleop is enabled. */
    @Override
    public void teleopInit() {}

    /** This method is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {
        diffDrive.arcadeDrive(joystick.getX() * 0.4, joystick.getY() * -0.4); //megan was right
    }

    /** This function is called once when the robot is disabled. */
    @Override
    public void disabledInit() {}

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
