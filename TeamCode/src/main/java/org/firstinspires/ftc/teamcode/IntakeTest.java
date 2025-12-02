package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Tank Drive Train", group="Hardware")
public class IntakeTest extends OpMode {

    private DcMotor intakeMotor;
    private DcMotor outtakeMotor;

    @Override
    public void init() {
        intakeMotor = hardwareMap.dcMotor.get("intakeMotor");
        outtakeMotor = hardwareMap.dcMotor.get("outtakeMotor");

        outtakeMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    double defaultIntakeSpd = 0.65;
    double defaultOuttakeSpd = 1;

    @Override
    public void loop() {

        double intakePower = 0.0;
        if (gamepad1.left_bumper) intakePower = defaultIntakeSpd;
        intakeMotor.setPower(intakePower);

        double outtakePower = 0.0;
        if (gamepad1.right_bumper) outtakePower = defaultOuttakeSpd;
        outtakeMotor.setPower(outtakePower);

        telemetry.addData("Intake Power", intakePower);
        telemetry.addData("Outtake Power", outtakePower);
        telemetry.update();
    }
}
