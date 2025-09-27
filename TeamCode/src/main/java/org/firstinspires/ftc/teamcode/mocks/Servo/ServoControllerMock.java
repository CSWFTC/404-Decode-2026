package org.firstinspires.ftc.teamcode.mocks.Servo;
import com.qualcomm.robotcore.hardware.HardwareDevice;

public interface ServoControllerMock extends HardwareDevice {
    enum PwmStatus { ENABLED, DISABLED, MIXED }

    void pwmEnable();

    void pwmDisable();

    Class<?> getPwmStatus();

    void setServoPosition(int servo, double position);

    double getServoPosition(int servo);
}