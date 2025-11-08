package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="Tank Drive Train", group="Hardware")
public class TankDriveTrain extends OpMode {

    private DcMotor leftFront, leftBack, rightFront, rightBack, intakeMotor, shootMotor;

    @Override
    public void init() {

        leftFront = hardwareMap.dcMotor.get("frontLeft");
        leftBack = hardwareMap.dcMotor.get("backLeft");
        rightFront = hardwareMap.dcMotor.get("frontRight");
        rightBack = hardwareMap.dcMotor.get("backRight");
        intakeMotor = hardwareMap.dcMotor.get("intakeMotor");
        shootMotor = hardwareMap.dcMotor.get("shootMotor");
//Note: All inputs associated with intakeMotor & shootMotor must be from to gamepad2 during competition.

        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    double power = 0.0;
    double defaultIntakeSpd = 1.0;

    @Override
    public void loop() {


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


        if (gamepad1.rightBumperWasPressed()) {
            shootMotor.setPower(power);
        } else if (gamepad1.rightBumperWasReleased()) {
            shootMotor.setPower(0.0);
        }


        if (gamepad1.leftBumperWasPressed()) {
            intakeMotor.setPower(defaultIntakeSpd);
        } else if (gamepad1.leftBumperWasReleased()) {
            intakeMotor.setPower(0.0);
        }


        double leftPower = -gamepad1.left_stick_y;
        double rightPower = -gamepad1.right_stick_y;


        leftFront.setPower(leftPower);
        leftBack.setPower(leftPower);
        rightFront.setPower(rightPower);
        rightBack.setPower(rightPower);


        telemetry.addData("Left Power", leftPower);
        telemetry.addData("Right Power", rightPower);
        telemetry.update();
    }
}
