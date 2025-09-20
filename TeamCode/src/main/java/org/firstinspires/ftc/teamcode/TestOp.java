package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.rframeworks.eventbus.*;
import com.rframeworks.di.*;

@TeleOp(name = "Test OpMode", group = "OpModes")
@Disabled
public class TestOp extends OpMode {
    private EventBus<String> bus = EventBus.getInstance();

    @Override
    public void init() {
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
    }

    @Override
    public void stop() {
    }
}
