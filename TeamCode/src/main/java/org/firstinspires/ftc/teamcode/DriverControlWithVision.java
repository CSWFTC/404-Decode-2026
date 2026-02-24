package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Helper.DriveTrain;
import org.firstinspires.ftc.teamcode.Helper.Hardware;
import org.firstinspires.ftc.teamcode.Helper.Shoot;
import org.firstinspires.ftc.teamcode.Helper.SimpleAprilTagVision;

import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

@TeleOp(name = "Driver + Vision Control", group = "Competition")
public class DriverControlWithVision extends LinearOpMode {

    private DriveTrain drive;
    private Shoot shooter;
    private SimpleAprilTagVision vision;

    private boolean reversed = false;
    private double speedMultiplier = 0.9;
    private double intakeSpeed = 0.7;

    private boolean lastBack = false;
    private boolean lastA = false;
    private boolean lastRB = false;
    private boolean lastLB = false;

    private boolean lastY2 = false;

    @Override
    public void runOpMode() {

        Hardware.init(hardwareMap);
        drive = new DriveTrain();
        shooter = new Shoot(Hardware.intakeMotor, Hardware.outtakeMotor);
        vision = new SimpleAprilTagVision(hardwareMap);
        vision.init();

        telemetry.addLine("Driver + Vision Control");
        telemetry.update();

        waitForStart();
        telemetry.clear();

        boolean lastB = false;

        while (opModeIsActive()) {
            boolean backPressed = gamepad1.back && !lastBack;
            if (backPressed) reversed = !reversed;
            lastBack = gamepad1.back;

            if (gamepad1.dpad_down)  speedMultiplier = 0.25;
            if (gamepad1.dpad_left)  speedMultiplier = 0.75;
            if (gamepad1.dpad_right) speedMultiplier = 0.50;
            if (gamepad1.dpad_up)    speedMultiplier = 1.00;

            drive.setDriveVectorFromJoystick(
                    gamepad1.left_stick_x * (float) speedMultiplier,
                    -gamepad1.right_stick_x * (float) speedMultiplier,
                    -gamepad1.left_stick_y * (float) speedMultiplier,
                    reversed
            );

            boolean rbPressed = gamepad2.right_bumper && !lastRB;
            boolean lbPressed = gamepad2.left_bumper && !lastLB;

            if (rbPressed) {
                intakeSpeed += 0.1;
                if (intakeSpeed > 1.0) intakeSpeed = 1.0;
                if (shooter.getLeftPower() > 0) {
                    shooter.setPower(intakeSpeed, shooter.getRightPower());
                }
            }

            if (lbPressed) {
                intakeSpeed -= 0.1;
                if (intakeSpeed < 0.2) intakeSpeed = 0.2;
                if (shooter.getLeftPower() > 0) {
                    shooter.setPower(intakeSpeed, shooter.getRightPower());
                }
            }

            lastRB = gamepad2.right_bumper;
            lastLB = gamepad2.left_bumper;

            boolean aPressed = gamepad2.a && !lastA;
            boolean bPressed = gamepad2.b && !lastB;

            if (aPressed) {
                boolean running = shooter.getLeftPower() > 0;
                shooter.setPower(running ? 0 : intakeSpeed, shooter.getRightPower());
            }
            lastA = gamepad2.a;
            lastB = gamepad2.b;


            if (bPressed) {
                double outtakePower = shooter.getRightPower() > 0 ? 0 : 0.6;
                shooter.setPower(shooter.getLeftPower(), outtakePower);
            }


            boolean yPressed = gamepad2.y && !lastY2;
            if (yPressed) {
                if (vision.isEnabled()) {
                    vision.disable();
                } else {
                    vision.enable();
                }
            }
            lastY2 = gamepad2.y;

            telemetry.addLine("Drive")
                    .addData("FL", Hardware.frontLeft.getPower())
                    .addData("FR", Hardware.frontRight.getPower())
                    .addData("BL", Hardware.backLeft.getPower())
                    .addData("BR", Hardware.backRight.getPower());
            telemetry.addLine("Intake")
                    .addData("IntakePower", Hardware.intakeMotor.getPower())
                    .addData("IntakeSpeedSetting", intakeSpeed);

            if (vision.isEnabled()) {
                AprilTagDetection det = vision.getLatestDetection();
                if (det != null) {
                    telemetry.addLine("AprilTag")
                            .addData("Tag ID", det.id)
                            .addData("Pose X", det.ftcPose.x)
                            .addData("Pose Y", det.ftcPose.y)
                            .addData("Bearing (deg)", det.ftcPose.bearing);
                } else {
                    telemetry.addLine("AprilTag").addData("Tag", "None");
                }
            } else {
                telemetry.addLine("AprilTag").addData("Status", "Disabled");
            }

            telemetry.update();
        }
    }
}
