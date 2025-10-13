package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name="Test Single Motor (Full Speed)", group="Hardware")
public class MotorTestOp extends LinearOpMode {

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

        DcMotor motor = hardwareMap.dcMotor.get(PARAMS.motorName);
        motor.setDirection(PARAMS.motorForward ? DcMotorSimple.Direction.FORWARD : DcMotorSimple.Direction.REVERSE);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();
        telemetry.clear();

        motor.setPower(1.0);

        while (opModeIsActive()) {
            telemetry.addData("Motor", PARAMS.motorName);
            telemetry.addData("Position", motor.getCurrentPosition());
            telemetry.addData("Power", motor.getPower());
            telemetry.update();
        }

        motor.setPower(0);
    }
}
