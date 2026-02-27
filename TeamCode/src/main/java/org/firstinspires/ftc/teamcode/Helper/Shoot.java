package org.firstinspires.ftc.teamcode.Helper;
import static org.firstinspires.ftc.teamcode.Helper.Hardware.outtakeMotor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;



import androidx.annotation.NonNull;


public class Shoot {

    private final DcMotor leftMotor;
    private final DcMotor rightMotor;

    public static class Params {
        public double shootingspeed = 0.00;
    }


    public static double targetShooterPosition = 0.00;
    public static Shoot.Params PARAMS = new Shoot.Params();
    public Shoot(@NonNull HardwareMap hdwMap) {

        leftMotor = hdwMap.dcMotor.get("leftMotor");
        rightMotor = hdwMap.dcMotor.get("rightMotor");


        this.leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        this.leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setPower(double leftPower, double rightPower) {
        leftMotor.setPower(leftPower);
        rightMotor.setPower(rightPower);
    }
    public void stop() {
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
    public double getLeftPower() {
        return leftMotor.getPower();
    }
    public double getRightPower() {
        return rightMotor.getPower();
    }

    public void setShooterSpeed(double speed){
        outtakeMotor.setPower(speed);
        Shoot.PARAMS.shootingspeed = speed;
    }

    public void increaseShooterSpeed (){
        Shoot.PARAMS.shootingspeed += 0.01;
        setShooterSpeed(PARAMS.shootingspeed);
    }

    public void decreaseShooterSpeed (){
        Shoot.PARAMS.shootingspeed -= 0.01;
        setShooterSpeed(Shoot.PARAMS.shootingspeed);
    }

    //shooting angle
    public void setAnglePosition(double newPos){
        targetShooterPosition = newPos;

    }

    public void increaseAnglePosition(){
        targetShooterPosition += 0.05;
        setAnglePosition(targetShooterPosition);

    }

    public void decreaseAnglePosition(){
        targetShooterPosition -= 0.05;
        setAnglePosition(targetShooterPosition);
    }

}
