package org.firstinspires.ftc.teamcode.Helper;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import android.os.SystemClock;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Helper.Hardware;



import androidx.annotation.NonNull;


public class Spindexer {

    public static class Params{

        public boolean spinMotorReverse = true;
        public double secondPos = 445;
        public double thirdPos = 896;

        public double fOut = 210;
        public double sOut = 685;
        public double tOut = 1130;
        public double kp = 0.03;
        public double ki = 0.0000;
        public double kd = 0.0005;

    }
    double setpoint = 0, prevError = 0, integral = 0;
    long prevTime = System.currentTimeMillis();
    public static Params PARAMS = new Params();
    public Spindexer(@NonNull HardwareMap hdwMap){
       Hardware.spinnerMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
       Hardware.spinnerMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       Hardware.spinnerMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    //reset the encoder ig
    public void resetEncoders() {
       Hardware.spinnerMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       Hardware.spinnerMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public int getCurrentPosition(){
        return Hardware.spinnerMotor.getCurrentPosition();
    }
    public void Update(){
        long now = System.currentTimeMillis();
        double delta = (double)(now - prevTime) / 1000;
        double e = setpoint - Hardware.spinnerMotor.getCurrentPosition();
        integral += e * PARAMS.ki * delta;
        double derivative = PARAMS.kd * (e - prevError) / delta;
        prevError = e;
        double power = PARAMS.kp * e + integral + derivative;
        Hardware.spinnerMotor.setPower(Math.min(1, Math.max(power, -1)));
        prevError = e;
        prevTime = now;

    }
    private void moveToPosition(int targetPosition){
        setpoint = targetPosition;

    }

    //spin to first slot
    public void moveZeroPos(){
        moveToPosition(0);
    }
    //spin to second slot
    public void moveToSecond(){
        moveToPosition((int) PARAMS.secondPos);
    }
    //spin to third slot
    public void moveToThird(){
        moveToPosition((int) PARAMS.thirdPos);
    }

    //shooting??


}
