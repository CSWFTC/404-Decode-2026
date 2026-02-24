package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.rframeworks.di.Container;
import com.rframeworks.di.Inject;
import com.rframeworks.di.Injector;
import com.rframeworks.di.generated.GeneratedRegistry;
import com.rframeworks.eventbus.EventBus;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.configurators.AprilTagConfig;
import org.openftc.apriltag.AprilTagDetection;

@TeleOp(name="DITest", group="Tests")
public class DITest extends LinearOpMode {

    @Inject("DITestClass")
    DITestClass dtc;

    public void init_test() {
        GeneratedRegistry.registerAll();
        DITestClass ndtc = new DITestClass();

        Container.register("DITestClass", ndtc);
//        Container.resolve()
        Injector.injectInto(this);
    }

    @Override
    public void runOpMode() {
        init_test();

        telemetry.setDisplayFormat(Telemetry.DisplayFormat.MONOSPACE);
        telemetry.addLine("DI Test");

        telemetry.update();

        dtc.sayHi(telemetry);

        waitForStart();
        if (isStopRequested()) return;

        telemetry.clear();

//        while (opModeIsActive()) {
//        }
    }
}
