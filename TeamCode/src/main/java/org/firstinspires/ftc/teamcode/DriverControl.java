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
    private double speedMultiplier = 0.9;

    @Override
    public void runOpMode() {

        Hardware.init(hardwareMap);

        telemetry.addLine("Driver Control 2-Motor")
                .addData("Version", "1.1");
        telemetry.update();

        drive = new DriveTrain();
        shooter = new Shoot(Hardware.intakeMotor, Hardware.outtakeMotor);

        // edge detection
        boolean lastBack = false;
        boolean lastA = false;
        boolean lastB = false;

        waitForStart();
        telemetry.clear();

        while (opModeIsActive()) {

            boolean backPressed = gamepad1.back && !lastBack;
            if (backPressed) {
                reversed = !reversed;
            }
            lastBack = gamepad1.back;

            if (gamepad1.dpad_down)  speedMultiplier = 0.25;
            if (gamepad1.dpad_left)  speedMultiplier = 0.75;
            if (gamepad1.dpad_right) speedMultiplier = 0.50;
            if (gamepad1.dpad_up)    speedMultiplier = 1.00;

            drive.setDriveVectorFromJoystick(
                    gamepad1.left_stick_x  * (float) speedMultiplier,
                    -gamepad1.right_stick_x * (float) speedMultiplier,
                    -gamepad1.left_stick_y * (float) speedMultiplier,
                    reversed
            );


            boolean aPressed = gamepad2.a && !lastA;
            boolean bPressed = gamepad2.b && !lastB;

            if (aPressed) {
                double intakePower = shooter.getLeftPower() > 0 ? 0 : 1;
                shooter.setPower(intakePower, shooter.getRightPower());
            }

            if (bPressed) {
                double outtakePower = shooter.getRightPower() > 0 ? 0 : 0.6;
                shooter.setPower(shooter.getLeftPower(), outtakePower);
            }

            lastA = gamepad2.a;
            lastB = gamepad2.b;


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
