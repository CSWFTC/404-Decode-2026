package org.firstinspires.ftc.teamcode;

import com.rframeworks.di.Injectable;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Injectable("DITestClass")
public class DITestClass {
    public void sayHi(Telemetry telemetry) {
        telemetry.addLine("Hello!");
        telemetry.update();
    }
}
