package org.firstinspires.ftc.teamcode;

import com.rframeworks.di.Inject;
import com.rframeworks.di.Injectable;
import com.rframeworks.di.Singleton;

@Injectable("InjectTest")
@Singleton
public class InjectTest {
    public void printSomething() {
        System.out.println("Printed from InjectTest");
    }
}
