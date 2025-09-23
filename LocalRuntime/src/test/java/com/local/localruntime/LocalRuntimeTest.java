package com.local.localruntime;

import org.firstinspires.ftc.teamcode.TestOp;
import org.junit.Test;

public class LocalRuntimeTest {
    @Test
    public void testMain() {
        Class<?>[] mockOps = {TestOp.class};
        LocalRuntime.run(mockOps, 0);
    }
}
