package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.sun.tools.javac.tree.DCTree;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Helper.GamePad;

@TeleOp(name="Test Single Motor (Full Speed)", group="Hardware")
public class TankDriveTrainTest2 extends LinearOpMode {

    public static final String version = "1.5";

    private DcMotor leftFront, leftBack, rightFront, rightBack;
    private GamePad gpInput;

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

        leftFront = hardwareMap.dcMotor.get("frontLeft");
        leftBack = hardwareMap.dcMotor.get("backLeft");
        rightFront = hardwareMap.dcMotor.get("frontRight");
        rightBack = hardwareMap.dcMotor.get("backRight");
//Note: All inputs associated with intakeMotor & shootMotor must be from to gamepad2 during competition.

        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        telemetry.clear();

        double power = 0.0;

        while (opModeIsActive()) {
            telemetry.addData("Motor", PARAMS.motorName);
            telemetry.addData("PositionLeft1", leftFront.getCurrentPosition());
            telemetry.addData("PowerLeft1", leftFront.getPower());
            telemetry.addData("Motor", PARAMS.motorName);
            telemetry.addData("PositionRight1", rightFront.getCurrentPosition());
            telemetry.addData("PowerRight1", rightFront.getPower());
            telemetry.update();

            GamePad.GameplayInputType inpType = gpInput.WaitForGamepadInput(30);
            switch (inpType) {
                case RIGHT_TRIGGER:
                    power = 0.0;
                    break;
                case BUTTON_A:
                    power = 1.0;
                    break;
                case BUTTON_B:
                    power = 0.5;
                    break;
                case JOYSTICK:
                    double leftPower = -gamepad1.left_stick_y;
                    double rightPower = -gamepad1.right_stick_y;

                    leftFront.setPower(leftPower*power);
                    leftBack.setPower(leftPower*power);
                    rightFront.setPower(rightPower*power);
                    rightBack.setPower(rightPower*power);
                    break;
            }
        }
    }
}