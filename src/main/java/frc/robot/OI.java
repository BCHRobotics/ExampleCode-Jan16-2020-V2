/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

    //Init Joysticks
    public Joystick driveStick = new Joystick(RobotMap.OI_DRIVESTICK_USB);
    public Joystick funStick = new Joystick(RobotMap.OI_FUNSTICK_USB);
    public Joystick proStick = new Joystick(RobotMap.OI_PROSTICK_USB);
    public Joystick testStick = new Joystick(RobotMap.OI_TESTSTICK_USB);

    //driveStick buttons
    public JoystickButton buttonTurbo = new JoystickButton(driveStick, RobotMap.BTN_DRIVESTICK_TURBO);
    public JoystickButton buttonSnail = new JoystickButton(driveStick, RobotMap.BTN_DRIVESTICK_SNAIL);

    //funstick buttons
    public JoystickButton buttonGrabberOpen = new JoystickButton(funStick, RobotMap.BTN_FUNSTICK_GRABBER_OPEN);
    public JoystickButton buttonGrabberClose = new JoystickButton(funStick, RobotMap.BTN_FUNSTICK_GRABBER_CLOSE);
}
