/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;


public class Drivetrain extends SubsystemBase {
  
  CANSparkMax SPARKLEFTFRONT = new CANSparkMax(RobotMap.SPARKLEFTFRONT, MotorType.kBrushless);
  CANSparkMax SPARKLEFTBACK = new CANSparkMax(RobotMap.SPARKLEFTBACK, MotorType.kBrushless);

  CANSparkMax SPARKRIGHTFRONT = new CANSparkMax(RobotMap.SPARKRIGHTFRONT, MotorType.kBrushless);
  CANSparkMax SPARKRIGHTBACK = new CANSparkMax(RobotMap.SPARKRIGHTBACK, MotorType.kBrushless);


  CANEncoder encoderFL = new CANEncoder(SPARKLEFTFRONT);
  CANEncoder encoderBL = new CANEncoder(SPARKLEFTBACK);
  CANEncoder encoderFR = new CANEncoder(SPARKRIGHTFRONT);
  CANEncoder encoderBR = new CANEncoder(SPARKRIGHTBACK);

  private static double encoderCal = 2.0034;
  private static double rampRate = 0.2;

  public Drivetrain() {

    SPARKLEFTFRONT.setInverted(true);
    SPARKLEFTBACK.setInverted(true);
    SPARKRIGHTFRONT.setInverted(false);
    SPARKRIGHTBACK.setInverted(false);

    SPARKLEFTFRONT.setClosedLoopRampRate(rampRate);
    SPARKLEFTBACK.setClosedLoopRampRate(rampRate);
    SPARKRIGHTFRONT.setClosedLoopRampRate(rampRate);
    SPARKRIGHTBACK.setClosedLoopRampRate(rampRate);

  }

  public void arcade(double ySpeed, double turnSpeed){

    SPARKLEFTFRONT.set(ySpeed - turnSpeed);
    SPARKLEFTBACK.set(ySpeed - turnSpeed);
    
    SPARKRIGHTFRONT.set(ySpeed + turnSpeed);
    SPARKRIGHTBACK.set(ySpeed + turnSpeed);

  }

  public double getEncoderFR(){

    double encoderVal = encoderFR.getPosition() * encoderCal;

    return encoderVal;
  }

  public double getEncoderBR(){

    double encoderVal = encoderBR.getPosition() * encoderCal;

    return encoderVal;
  }

  public double getEncoderFL(){

    double encoderVal = encoderFL.getPosition() * encoderCal;

    return encoderVal;
  }

  public double getEncoderBL(){

    double encoderVal = encoderBL.getPosition() * encoderCal;

    return encoderVal;
  }

  public void resetEncoders(){
    encoderFL.setPosition(0);
    encoderBL.setPosition(0);
    encoderFR.setPosition(0);
    encoderBR.setPosition(0);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
