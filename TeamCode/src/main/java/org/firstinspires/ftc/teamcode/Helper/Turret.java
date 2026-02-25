package org.firstinspires.ftc.teamcode.Helper;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Turret {

        private final DcMotor turretMotor;

        private final int LEFT_LIMIT = -700;
        private final int RIGHT_LIMIT = 700;

        private final int CENTER = 0;
        private final int LEFT_PRESET = -400;
        private final int RIGHT_PRESET = 400;

        private double kP = 0.003;
        private double kD = 0.0005;

        private int targetPosition = 0;
        private double lastError = 0;

        public Turret(DcMotor turretMotor) {
            this.turretMotor = turretMotor;

            turretMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            turretMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            turretMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        public void update(double stickX,
                           boolean slowMode,
                           boolean leftPreset,
                           boolean rightPreset,
                           boolean centerPreset) {

            int currentPos = turretMotor.getCurrentPosition();

            if (leftPreset) targetPosition = LEFT_PRESET;
            if (rightPreset) targetPosition = RIGHT_PRESET;
            if (centerPreset) targetPosition = CENTER;

            if (Math.abs(stickX) > 0.05) {

                double scale = slowMode ? 0.25 : 0.6;
                double power = stickX * scale;

                if ((currentPos <= LEFT_LIMIT && power < 0) ||
                        (currentPos >= RIGHT_LIMIT && power > 0)) {
                    turretMotor.setPower(0);
                } else {
                    turretMotor.setPower(power);
                    targetPosition = currentPos;
                    
                }

            } else {

                double error = targetPosition - currentPos;
                double derivative = error - lastError;


                double output = (kP * error) + (kD * derivative);

                turretMotor.setPower(output);
                lastError = error;
            }
        }

        public int getPosition() {
            return turretMotor.getCurrentPosition();
        }
    }


