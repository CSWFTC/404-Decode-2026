package org.firstinspires.ftc.teamcode.Helper;


import static java.lang.Thread.sleep;

import com.qualcomm.robotcore.hardware.DcMotor;


public class DriveTrain {
    public static Params PARAMS = new Params();
    protected volatile boolean brakingOn = false;

    public DriveTrain() {
        Hardware.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Hardware.backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Hardware.frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Hardware.backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void setDriveVector(double forward, double strafe, double rotate) {
        if (brakingOn) return;

        double denominator = Math.max(Math.abs(forward) + Math.abs(strafe) + Math.abs(rotate), 1);

        double pwrfrontLeft = (forward + strafe + rotate) / denominator;
        double pwrbackLeft = (forward - strafe + rotate) / denominator;
        double pwrfrontRight = (forward - strafe - rotate) / denominator;
        double pwrbackRight = (forward + strafe - rotate) / denominator;

        Hardware.frontLeft.setPower(pwrfrontLeft);
        Hardware.backLeft.setPower(pwrbackLeft);
        Hardware.frontRight.setPower(pwrfrontRight);
        Hardware.backRight.setPower(pwrbackRight);
    }


    public void setMotorsManually(boolean frontLeft, boolean frontRight, boolean backLeft, boolean backRight) {
        Hardware.backLeft.setPower(backLeft ? 1.0 : 0.0);
        Hardware.backRight.setPower(backRight ? 1.0 : 0.0);
        Hardware.frontLeft.setPower(frontLeft ? 1.0 : 0.0);
        Hardware.frontRight.setPower(frontRight ? 1.0 : 0.0);
    }

    public void setDriveVectorFromJoystick(float stickLeftX, float stickRightX, float stickLeftY, boolean setReversed) {
        if (brakingOn) return;

        double rotate = stickRightX;
        double forward = stickLeftY * PARAMS.joystickYInputAdjustment;
        double strafe = stickLeftX * PARAMS.strafingAdjustment;

        if (setReversed) {
            forward = stickLeftY * PARAMS.joystickYInputAdjustment * -1;
            strafe = stickLeftX * PARAMS.strafingAdjustment * -1;
        }

        setDriveVector(forward, strafe, rotate);
    }

    public void setBrakeStatus(boolean braking) {
        brakingOn = braking;
        if (braking) {
            boolean allStop = false;
            boolean timerExpired = false;
            long brakeStart = System.currentTimeMillis();


            while (!allStop && !timerExpired) {
                boolean flStop = coasterBrakeMotor(Hardware.frontLeft);
                boolean blStop = coasterBrakeMotor(Hardware.backLeft);
                boolean frStop = coasterBrakeMotor(Hardware.frontRight);
                boolean brStop = coasterBrakeMotor(Hardware.backRight);


                allStop = flStop && blStop && frStop && brStop;
                timerExpired = (System.currentTimeMillis() >= (brakeStart + PARAMS.brakingMaximumTime));


                if (!allStop && !timerExpired) {
                    try {
                        sleep(PARAMS.brakingInterval);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    private boolean coasterBrakeMotor(DcMotor motor) {
        double curPower = motor.getPower();
        boolean stopped = (curPower == 0);

        if (!stopped) {
            double newPower = curPower - (Math.signum(curPower) * PARAMS.brakingInterval);
            if (Math.abs(newPower) < PARAMS.brakingStopThreshold) newPower = 0;
            motor.setPower(newPower);
        }

        return stopped;
    }

    public static class Params {
        public double strafingAdjustment = 1.08;
        public double joystickYInputAdjustment = -1;
        public double brakingStopThreshold = 0.25;
        public double brakingGain = 0.15;
        public long brakingInterval = 100;
        public double brakingMaximumTime = (long) Math.ceil(1 / brakingGain) * brakingInterval;
    }

}
