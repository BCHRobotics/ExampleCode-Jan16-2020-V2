/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Grabber;
import frc.robot.subsystems.Lift;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  public OI m_oi = null;

  //Subsystems
  public Drivetrain m_drivetrain = null;
  //public Climber m_climber = null;
  public Grabber m_grabber = null;
  public Lift m_lift = null;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    
    m_oi = new OI();

    //Subsystems
    m_drivetrain = new Drivetrain();
    //m_climber = new Climber();
    m_grabber = new Grabber();
    m_lift = new Lift();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    teleDrive();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  public double y = 0;
  public double turn = 0;
  public double speed = 0;
  public static double deadzone = 0.07;

  public void teleDrive(){

    speed = 0.7;

    if(m_oi.buttonSnail.get()) speed = 0.5;
    if(m_oi.buttonTurbo.get()) speed = 1;

    y = deadzone(m_oi.driveStick.getRawAxis(RobotMap.OI_DRIVESTICK_MOVEY), 0.07);
    turn = deadzone(m_oi.driveStick.getRawAxis(RobotMap.OI_DRIVESTICK_TURN), 0.07);

    m_drivetrain.arcade(y * speed, turn * speed);

  }

  public void teleFun(){

    //intakeSpeed = -INTAKE_OUT + INTAKE_IN
    double intakeSpeed = -m_oi.funStick.getRawAxis(RobotMap.OI_FUNSTICK_INTAKE_OUT) + m_oi.funStick.getRawAxis(RobotMap.OI_FUNSTICK_INTAKE_IN);
    m_grabber.intake(intakeSpeed);

    if(m_oi.buttonGrabberOpen.get()){ m_grabber.grabberOpen(); }
    else if(m_oi.buttonGrabberClose.get()) m_grabber.grabberClose();


    double ladderSpeed = deadzone(m_oi.funStick.getRawAxis(RobotMap.OI_FUNSTICK_LIFT), 0.07);
    m_lift.moveLift(ladderSpeed);

  }

  public double deadzone(double input, double deadzone){

    if(input < deadzone && input > -deadzone) input = 0;
    return input;
  }

  public double deadzone(double input, double deadzonePos, double deadzoneNeg){

    if(input < deadzonePos && input > -deadzoneNeg) input = 0;
    return input;
  }
}
