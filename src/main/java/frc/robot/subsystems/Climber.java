/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Climber extends SubsystemBase {
  
  private double encoderArmCal = 1.172;
  private double encoderLegCal = 0.088;

  CANSparkMax ARMLEFT = new CANSparkMax(RobotMap.SPARKARMLEFT, MotorType.kBrushless);
  CANSparkMax ARMRIGHT = new CANSparkMax(RobotMap.SPARKARMRIGHT, MotorType.kBrushless);

  CANSparkMax SPARKLEG = new CANSparkMax(RobotMap.SPARKLEG, MotorType.kBrushless);
  TalonSRX TALONLEGWHEEL = new TalonSRX(RobotMap.TALONLEGWHEEL);

  CANEncoder encoderLeg = new CANEncoder(SPARKLEG);
  CANEncoder encoderArmLeft = new CANEncoder(ARMLEFT);
  CANEncoder encoderArmRight = new CANEncoder(ARMRIGHT);

  public Climber() {

    ARMLEFT.setInverted(true);
    ARMRIGHT.setInverted(false);

    SPARKLEG.setInverted(true);
    TALONLEGWHEEL.setInverted(false);

  }

  public void armsMove(double speed){

    speed = -speed;

    if((getEncoderArmRight() <= 25 || getEncoderArmLeft() <= 25) && speed > 0){
      ARMLEFT.set(speed);
      ARMRIGHT.set(speed);
    } else if((getEncoderArmRight() >= 208 || getEncoderArmLeft() >= 208) && speed < 0){
      ARMLEFT.set(speed);
      ARMRIGHT.set(speed);
    } else {
      ARMLEFT.set(0);
      ARMRIGHT.set(0);
    }
  }

  public double getEncoderArmLeft(){
    double encoderVal = -encoderArmLeft.getPosition() * encoderArmCal;
    return encoderVal;
  }

  public double getEncoderArmRight(){
    double encoderVal = -encoderArmRight.getPosition() * encoderArmCal;
    return encoderVal;
  }

  public double getEncoderArmAvg(){
    double encoderVal = (getEncoderArmLeft() + getEncoderArmRight()) / 2;
    return encoderVal;
  }

  public double getEncoderLeg(){
    double encoderVal = -encoderLeg.getPosition() * encoderLegCal;
    return encoderVal;
  }

  public void resetEncoderArms(){
    encoderArmLeft.setPosition(0);
    encoderArmRight.setPosition(0);
  }

  public void resetEncoderLeg(){
    encoderLeg.setPosition(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
