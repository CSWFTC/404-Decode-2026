package org.firstinspires.ftc.teamcode.mocks;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MockOp {
    String name();
    String group();
}
