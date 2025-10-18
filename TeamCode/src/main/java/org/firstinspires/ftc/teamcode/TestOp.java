package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.hardware.Servo;
import com.rframeworks.di.Inject;
import com.rframeworks.di.Injector;
import com.rframeworks.di.Resolved;
import com.rframeworks.eventbus.*;
import com.rframeworks.di.Injectable;

import com.rframeworks.di.generated.GeneratedRegistry;

import org.firstinspires.ftc.teamcode.configurators.AprilTagConfig;
import org.firstinspires.ftc.teamcode.mocks.MockOp;
import org.firstinspires.ftc.teamcode.mocks.MockRegistry;
import org.firstinspires.ftc.teamcode.mocks.OpModeMock;
import org.firstinspires.ftc.teamcode.mocks.Servo.ServoMock;

import com.qualcomm.robotcore.hardware.HardwareMap;

@MockOp(name = "Test OpMode", group = "OpModes")
@Disabled
public class TestOp extends OpModeMock {
    EventBus<String> eb = EventBus.getInstance();

    public static void Register() {
        MockRegistry.register(TestOp.class);
    }

    @Inject("AprilTagConfig")
    AprilTagConfig aprilTagConfig;

    @Override
    public void init() {
        System.out.println("Init");

        GeneratedRegistry.registerAll();
        Injector.injectInto(this);

        EventBus.getInstance().createTopic("DDAprilTagData");
        aprilTagConfig.registerConfig();
    }

    @Override
    public void init_loop() {
        this.pressPlay();
    }

    @Override
    public void start() {
        System.out.println("Started!");
        EventBus.getInstance().publish("DDAprilTagData", "123ABC");
        this.pressStop();
    }

    @Override
    public void loop() {
    }

    @Override
    public void stop() {
        System.out.println("AT Code from config: "+aprilTagConfig.getAprilTagCode());
        System.out.println("Stopped");
    }
}
