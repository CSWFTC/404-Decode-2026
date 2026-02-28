package org.firstinspires.ftc.teamcode.Helper.mechanisms;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Helper.Hardware;

public class TestBench {
    private DigitalChannel touchSensor; // touchSensorOutake

    public void init(HardwareMap hwMap){
        touchSensor = hwMap.get(DigitalChannel.class,"touchSensor");
        touchSensor.setMode(DigitalChannel.Mode.INPUT);
    }

    public boolean getTouchSensorState(){
        return touchSensor.getState();
    }


}
