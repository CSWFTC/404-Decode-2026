package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name="Test Single Motor (Full Speed)", group="Hardware")
public class MotorTestOp2 extends LinearOpMode {

    public static final String version = "1.1";

    public static class Params {
        public String intakeMotorName = "intakeMotor";
        public String outtakeMotorName = "outtakeMotor";

        public boolean intakeForward = true;
        public boolean outtakeForward = false;
    }

    public static Params PARAMS = new Params();

    @Override
    public void runOpMode() {
        telemetry.setDisplayFormat(Telemetry.DisplayFormat.MONOSPACE);
        telemetry.addLine().addData("Two Motor Test:", PARAMS.intakeMotorName + " & " + PARAMS.outtakeMotorName);
        telemetry.addData("Version", version);
        telemetry.addLine();
        telemetry.addData(">", "Press Start to Launch");
        telemetry.update();

        DcMotor intakeMotor = hardwareMap.dcMotor.get(PARAMS.intakeMotorName);
        DcMotor outtakeMotor = hardwareMap.dcMotor.get(PARAMS.outtakeMotorName);

        intakeMotor.setDirection(PARAMS.intakeForward ? DcMotorSimple.Direction.FORWARD : DcMotorSimple.Direction.REVERSE);
        outtakeMotor.setDirection(PARAMS.outtakeForward ? DcMotorSimple.Direction.FORWARD : DcMotorSimple.Direction.REVERSE);

        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        outtakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        intakeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        outtakeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        outtakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();
        telemetry.clear();

        intakeMotor.setPower(1.0);
        outtakeMotor.setPower(1.0);

        while (opModeIsActive()) {
            telemetry.addData("Intake Motor", PARAMS.intakeMotorName);
            telemetry.addData("Intake Position", intakeMotor.getCurrentPosition());
            telemetry.addData("Intake Power", intakeMotor.getPower());

            telemetry.addData("Outtake Motor", PARAMS.outtakeMotorName);
            telemetry.addData("Outtake Position", outtakeMotor.getCurrentPosition());
            telemetry.addData("Outtake Power", outtakeMotor.getPower());

            telemetry.update();
        }

        intakeMotor.setPower(0);
        outtakeMotor.setPower(0);
    }
}