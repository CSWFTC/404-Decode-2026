package org.firstinspires.ftc.teamcode.Helper;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import android.os.SystemClock;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Helper.Hardware;

import androidx.annotation.NonNull;

//screws aren't in tight enough
public class Shooter {

    public static class Params{

        public double pow = 1;


    }
    public static Shooter.Params PARAMS = new Shooter.Params();

    public Shooter(@NonNull HardwareMap hdwMap){
        Hardware.outtakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Hardware.outtakeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Hardware.outtakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void motorPowerMax(){
        Hardware.outtakeMotor.setPower(PARAMS.pow);
    }

    public void motorPowerZero(){
        Hardware.outtakeMotor.setPower(0);
    }
}
