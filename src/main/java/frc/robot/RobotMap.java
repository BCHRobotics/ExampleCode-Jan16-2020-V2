/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public class RobotMap {

    //MOTORS

    //Drivetrain
    public static int SPARKLEFTFRONT = 11;
    public static int SPARKLEFTBACK = 12;

    public static int SPARKRIGHTFRONT = 15;
    public static int SPARKRIGHTBACK = 16;

    //Lift
    public static int TALONLIFT = 31;

    //Climber
    public static int SPARKARMLEFT = 41;
    public static int SPARKARMRIGHT = 42;
    public static int SPARKLEG = 43;
    public static int TALONLEGWHEEL = 44;

    //Grabber
    public static int TALONGRABBERTILT = 21;
    public static int TALONGRABBEREXTEND = 22;
    public static int TALONINTAKELEFT = 62;
    public static int TALONINTAKERIGHT = 61;

    /************************************ */

    //PNEUMATICS

    public static int GRABBER_SOLENOID_OPEN = 0;
    public static int GRABBER_SOLENOID_CLOSE = 1;

    /************************************ */

    //CONTROLLERS

    public static int OI_DRIVESTICK_USB = 0;
    public static int OI_FUNSTICK_USB = 1;
    public static int OI_PROSTICK_USB = 2;
    public static int OI_TESTSTICK_USB = 3;

    //Drivestick AXIS
    public static int OI_DRIVESTICK_MOVEY = 1;
    public static int OI_DRIVESTICK_TURN = 0;

    //Drivestick Buttons
    public static int BTN_DRIVESTICK_TURBO = 5;
    public static int BTN_DRIVESTICK_SNAIL = 4;

    //Funstick AXIS
    public static int OI_FUNSTICK_LIFT = 1;
    public static int OI_FUNSTICK_INTAKE_IN = 3;
    public static int OI_FUNSTICK_INTAKE_OUT = 2;

    //Funstick Buttons
    public static int BTN_FUNSTICK_GRABBER_OPEN = 5;
    public static int BTN_FUNSTICK_GRABBER_CLOSE = 6;


}
