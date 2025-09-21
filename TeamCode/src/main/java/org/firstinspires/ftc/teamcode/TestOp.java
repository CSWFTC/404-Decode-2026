package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.rframeworks.eventbus.*;
import com.rframeworks.di.Injectable;

import com.rframeworks.di.generated.GeneratedRegistry;

import org.firstinspires.ftc.teamcode.mocks.MockOp;
import org.firstinspires.ftc.teamcode.mocks.MockRegistry;
import org.firstinspires.ftc.teamcode.mocks.OpModeMock;

@MockOp(name = "Test OpMode", group = "OpModes")
@Disabled
public class TestOp extends OpModeMock {
    EventBus<String> eb = EventBus.getInstance();

    public static void Register() {
        MockRegistry.register(TestOp.class);
    }

    @Override
    public void init() {
        GeneratedRegistry.registerAll();
        System.out.println("Init!");
    }

    @Override
    public void init_loop() {
        this.pressPlay();
    }

    @Override
    public void start() {
        System.out.println("Started!");
        this.pressStop();
    }

    @Override
    public void loop() {
    }

    @Override
    public void stop() {
        System.out.println("Stopped");
    }
}
