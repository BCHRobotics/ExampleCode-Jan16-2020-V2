/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Grabber extends SubsystemBase {
  
  DoubleSolenoid grabberSol = null;

  TalonSRX TALONGRABBERTILT = new TalonSRX(RobotMap.TALONGRABBERTILT);

  TalonSRX TALONINTAKELEFT = new TalonSRX(RobotMap.TALONINTAKELEFT);
  TalonSRX TALONINTAKERIGHT = new TalonSRX(RobotMap.TALONINTAKERIGHT);

  Compressor m_compressor = new Compressor(0);

  public Grabber() {

    grabberSol = new DoubleSolenoid(RobotMap.GRABBER_SOLENOID_OPEN,
      RobotMap.GRABBER_SOLENOID_CLOSE);

    TALONGRABBERTILT.setInverted(false);

    TALONINTAKELEFT.setInverted(false);
    TALONINTAKERIGHT.setInverted(true);

    TALONINTAKELEFT.configClosedloopRamp(0);
    TALONINTAKERIGHT.configClosedloopRamp(0);

    TALONINTAKERIGHT.follow(TALONINTAKELEFT);

  }

  public void grabberOpen(){
    grabberSol.set(Value.kForward);
  }

  public void grabberClose(){
    grabberSol.set(Value.kReverse);
  }

  public void compressorOn(){
    m_compressor.setClosedLoopControl(true);
  }

  public void compressorOff(){
    m_compressor.setClosedLoopControl(false);
  }

  public void tilt(double speed){
    TALONGRABBERTILT.set(ControlMode.PercentOutput, speed);
  }

  public void intake(double speed){
    TALONINTAKELEFT.set(ControlMode.PercentOutput, speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
