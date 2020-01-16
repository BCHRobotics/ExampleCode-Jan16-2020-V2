/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/


package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Lift extends SubsystemBase {
  
  private double encoderCal = 0.0026929;
  //private double liftDeadzone = 1;

  TalonSRX TALONLIFT = new TalonSRX(RobotMap.TALONLIFT);

  private final int kTimeoutMs = 30;
  private final boolean kDiscontinuityPresent = true;
  private final int kBookEnd_0 = 910;		/* 80 deg */
	private final int kBookEnd_1 = 1137;	/* 100 deg */

  public Lift() {

    double rampRate = 0.2;

    initQuadrature();

    TALONLIFT.configOpenloopRamp(rampRate);
    TALONLIFT.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, kTimeoutMs);

  }

  public void moveLift(double liftSpeed){

    liftSpeed = -liftSpeed;

    if(getEncoder() <= 0.25 && liftSpeed > 0) {
      TALONLIFT.set(ControlMode.PercentOutput, liftSpeed);
    } else if(getEncoder() >= 36 && liftSpeed < 0){
      TALONLIFT.set(ControlMode.PercentOutput, liftSpeed);
    } else {
      TALONLIFT.set(ControlMode.PercentOutput, 0);
    }
  }

  public void resetEncoder(){
    TALONLIFT.setSelectedSensorPosition(0, 0, this.kTimeoutMs);
  }

  public double getEncoder(){

    double encoderVal = -TALONLIFT.getSelectedSensorPosition(0) * encoderCal;

    return encoderVal;
  }

  public void initQuadrature() {
		/* get the absolute pulse width position */
		int pulseWidth = TALONLIFT.getSensorCollection().getPulseWidthPosition();

		/**
		 * If there is a discontinuity in our measured range, subtract one half
		 * rotation to remove it
		 */
		if (kDiscontinuityPresent) {

			/* Calculate the center */
			int newCenter;
			newCenter = (kBookEnd_0 + kBookEnd_1) / 2;
			newCenter &= 0xFFF;

			/**
			 * Apply the offset so the discontinuity is in the unused portion of
			 * the sensor
			 */
			pulseWidth -= newCenter;
		}

		/**
		 * Mask out the bottom 12 bits to normalize to [0,4095],
		 * or in other words, to stay within [0,360) degrees 
		 */
		pulseWidth = pulseWidth & 0xFFF;

		/* Update Quadrature position */
		TALONLIFT.getSensorCollection().setQuadraturePosition(pulseWidth, kTimeoutMs);
	}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
