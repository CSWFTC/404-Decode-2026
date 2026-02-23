package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Helper.DriveTrain;
import org.firstinspires.ftc.teamcode.Helper.Hardware;
import org.firstinspires.ftc.teamcode.Helper.Shoot;
import org.firstinspires.ftc.teamcode.Helper.Turret;

@TeleOp(name = "Driver Control", group = "Competition")
public class DriverControl extends LinearOpMode {

    private DriveTrain drive;
    private Shoot shooter;
    private Turret turret;

    private boolean reversed = false;
    private double speedMultiplier = 0.9;

    @Override
    public void runOpMode() {

        Hardware.init(hardwareMap);

        drive = new DriveTrain();
        shooter = new Shoot(Hardware.intakeMotor, Hardware.outtakeMotor);
        turret = new Turret(Hardware.turretMotor);

        boolean lastBack = false;
        boolean lastA = false;
        boolean lastB = false;

        waitForStart();

        while (opModeIsActive()) {

            boolean backPressed = gamepad1.back && !lastBack;
            if (backPressed) reversed = !reversed;
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
                double intakePower = shooter.getIntakePower() > 0 ? 0 : 1;
                shooter.setPower(intakePower, shooter.getOuttakePower());
            }

            if (bPressed) {
                double outtakePower = shooter.getOuttakePower() > 0 ? 0 : 0.6;
                shooter.setPower(shooter.getIntakePower(), outtakePower);
            }

            lastA = gamepad2.a;
            lastB = gamepad2.b;

            turret.update(
                    gamepad2.right_stick_x,
                    gamepad2.left_bumper,
                    gamepad2.x,
                    gamepad2.b,
                    gamepad2.y
            );

            telemetry.addData("Turret Position", turret.getPosition());
            telemetry.update();
        }
    }
}