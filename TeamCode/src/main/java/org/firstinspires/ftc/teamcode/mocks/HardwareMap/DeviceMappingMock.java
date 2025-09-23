package org.firstinspires.ftc.teamcode.mocks.HardwareMap;

import java.util.HashMap;
import java.util.Map;

public class DeviceMappingMock<T> {
    private final Map<String, T> devices = new HashMap<>();

    public void put(String name, T device) { devices.put(name, device); }
    public T get(String name) { return devices.get(name); }
}
