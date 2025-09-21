package org.firstinspires.ftc.teamcode.mocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MockRegistry {
    private static final List<Class<?>> mocks = new ArrayList<>();

    public static void register(Class<?> cls) {
        mocks.add(cls);
    }

    public static List<Class<?>> getMocks() {
        return Collections.unmodifiableList(mocks);
    }
}
