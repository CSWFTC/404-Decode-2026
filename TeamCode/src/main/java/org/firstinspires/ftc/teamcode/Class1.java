package org.firstinspires.ftc.teamcode;

import com.rframeworks.di.Inject;
import com.rframeworks.di.Resolved;

public class Class1 extends Resolved {
    @Inject("InjectTest")
    InjectTest test;

    public void printSomethingFromTest() {
        test.printSomething();
    }
}
