package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name="Test Single Motor (Full Speed)", group="Hardware")
public class MotorTestOp1 extends LinearOpMode {

    public static final String version = "1.5";

    public static class Params {
        public String motorName = "bigmotor";
        public boolean motorForward = true; // forward
    }

    public static Params PARAMS = new Params();

    @Override
    public void runOpMode() {
        telemetry.setDisplayFormat(Telemetry.DisplayFormat.MONOSPACE);
        telemetry.addLine().addData("Single Motor Test: ", PARAMS.motorName);
        telemetry.addData("Version", version);
        telemetry.addLine();
        telemetry.addData(">", "Press Start to Launch");
        telemetry.update();

        DcMotor motorLeft1 = hardwareMap.dcMotor.get(PARAMS.motorName);
        motorLeft1.setDirection(PARAMS.motorForward ? DcMotorSimple.Direction.REVERSE : DcMotorSimple.Direction.FORWARD);
        motorLeft1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLeft1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorLeft1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        DcMotor motorRight1 = hardwareMap.dcMotor.get(PARAMS.motorName);
        motorRight1.setDirection(PARAMS.motorForward ? DcMotorSimple.Direction.FORWARD : DcMotorSimple.Direction.REVERSE);
        motorRight1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRight1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRight1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();
        telemetry.clear();

        double power = 0.0;

        while (opModeIsActive()) {
            telemetry.addData("Motor", PARAMS.motorName);
            telemetry.addData("PositionLeft1", motorLeft1.getCurrentPosition());
            telemetry.addData("PowerLeft1", motorLeft1.getPower());
            telemetry.addData("Motor", PARAMS.motorName);
            telemetry.addData("PositionRight1", motorRight1.getCurrentPosition());
            telemetry.addData("PowerRight1", motorRight1.getPower());
            telemetry.update();
             if (gamepad1.aWasPressed()) {
                 power = 1.0;
             } else if (gamepad1.bWasPressed()) {
                 power = 0.75;
             } else if (gamepad1.xWasPressed()) {
                 power = 0.5;
             } else if (gamepad1.yWasPressed()) {
                 power = 0.25;
             } else if (gamepad1.dpadDownWasPressed()) {
                 power = 0.0;
             }

             motorLeft1.setPower(gamepad1.left_stick_y*power);
             motorRight1.setPower(gamepad1.right_stick_y*power);
        }
    }
}