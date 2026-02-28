package org.firstinspires.ftc.teamcode.Helper;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Turret {

    private final DcMotor turretMotor;

    private final int LEFT_LIMIT = -700;
    private final int RIGHT_LIMIT = 700;

    private double kP = 0.003;
    private double kD = 0.0005;

    private int targetPosition = 0;
    private double lastError = 0;

    private boolean holdMode = false;
    private boolean lastButtonState = false;

    public Turret(DcMotor turretMotor) {
        this.turretMotor = turretMotor;

        turretMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        turretMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        turretMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void update(double stickY, boolean holdButtonX) {

        int currentPos = turretMotor.getCurrentPosition();

        if (holdButtonX && !lastButtonState) {
            holdMode = !holdMode;
            targetPosition = currentPos;
        }
        lastButtonState = holdButtonX;

        if (Math.abs(stickY) > 0.05) {

            holdMode = false;

            double power = -stickY * 0.6;

            if ((currentPos <= LEFT_LIMIT && power < 0) ||
                    (currentPos >= RIGHT_LIMIT && power > 0)) {
                turretMotor.setPower(0);
            } else {
                turretMotor.setPower(power);
                targetPosition = currentPos;
            }

        } else {

            if (holdMode) {
                double error = targetPosition - currentPos;
                double derivative = error - lastError;

                double output = (kP * error) + (kD * derivative);

                turretMotor.setPower(output);
                lastError = error;
            } else {
                turretMotor.setPower(0);
            }
        }
    }

    public int getPosition() {
        return turretMotor.getCurrentPosition();
    }}