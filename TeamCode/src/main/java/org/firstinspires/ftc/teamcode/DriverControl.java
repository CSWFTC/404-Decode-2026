package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Helper.DriveTrain;
import org.firstinspires.ftc.teamcode.Helper.GamePad;
import org.firstinspires.ftc.teamcode.Helper.Hardware;
import org.firstinspires.ftc.teamcode.Helper.Shoot;

@TeleOp(name = "Driver Control ", group = "Competition")
public class DriverControl extends LinearOpMode {

    private DriveTrain drive;
    private Shoot shooter;
    private GamePad gp1, gp2;
    private boolean reversed = false;
    private double speedMultiplier = 0.5;

    @Override
    public void runOpMode() {

        Hardware.init(hardwareMap);

        telemetry.addLine("Driver Control 2-Motor")
                .addData("Version", "1.0");
        telemetry.update();

        drive = new DriveTrain();
        shooter = new Shoot(Hardware.intakeMotor, Hardware.outtakeMotor);

        gp1 = new GamePad(gamepad1);
        gp2 = new GamePad(gamepad2);

        waitForStart();
        telemetry.clear();

        while (opModeIsActive()) {

            // ----- DRIVE CONTROL (Gamepad1) -----
            GamePad.GameplayInputType input1 = gp1.WaitForGamepadInput(30);
            switch (input1) {
                case BUTTON_BACK:
                    reversed = !reversed;
                    break;
                case DPAD_DOWN:
                    speedMultiplier = 0.25;
                    break;
                case DPAD_LEFT:
                    speedMultiplier = 0.75;
                    break;
                case DPAD_RIGHT:
                    speedMultiplier = 0.5;
                    break;
                case DPAD_UP:
                    speedMultiplier = 1.0;
                    break;
                case JOYSTICK:
                    drive.setDriveVectorFromJoystick(
                            gamepad1.left_stick_x * (float) speedMultiplier,
                            gamepad1.right_stick_x * (float) speedMultiplier,
                            gamepad1.left_stick_y * (float) speedMultiplier,
                            reversed
                    );
                    break;
            }

            // ----- SHOOTER CONTROL (Gamepad2) -----
            GamePad.GameplayInputType input2 = gp2.WaitForGamepadInput(30);
            switch (input2) {
                case BUTTON_A:  // Toggle intake
                    double intakePower = shooter.getLeftPower() > 0 ? 0 : 1;
                    shooter.setPower(intakePower, shooter.getRightPower());
                    break;
                case BUTTON_Y:  // Toggle outtake
                    double outtakePower = shooter.getRightPower() > 0 ? 0 : 0.6;
                    shooter.setPower(shooter.getLeftPower(), outtakePower);
                    break;
            }

            // ----- TELEMETRY -----
            telemetry.addLine("Drive")
                    .addData("Front Left", Hardware.frontLeft.getPower())
                    .addData("Front Right", Hardware.frontRight.getPower())
                    .addData("Back Left", Hardware.backLeft.getPower())
                    .addData("Back Right", Hardware.backRight.getPower());

            telemetry.addLine("Shooter/Intake")
                    .addData("Intake Motor", Hardware.intakeMotor.getPower())
                    .addData("Outtake Motor", Hardware.outtakeMotor.getPower());

            telemetry.update();
        }
    }
}
