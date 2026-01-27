package org.firstinspires.ftc.teamcode.Helper;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Hardware {

    public static DcMotor frontLeft;
    public static DcMotor frontRight;
    public static DcMotor backLeft;
    public static DcMotor backRight;

    public static DcMotor intakeMotor;
    public static DcMotor outtakeMotor;

    public static WebcamName camera;

    public static class Params {

        public double shootingspeed = 0.00;
    }

    public static double targetShooterPosition = 0.00;

    public static Params PARAMS = new Params();
    private final Servo shooter;

    public static void init(HardwareMap map) {
        frontLeft  = map.get(DcMotor.class, "frontLeft");
        frontRight = map.get(DcMotor.class, "frontRight");
        backLeft   = map.get(DcMotor.class, "rearLeft");
        backRight  = map.get(DcMotor.class, "rearRight");

        intakeMotor  = map.get(DcMotor.class, "intakeMotor");
        outtakeMotor = map.get(DcMotor.class, "outtakeMotor");

        outtakeMotor.setDirection(DcMotor.Direction.REVERSE);

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
    }

    public Hardware(@NonNull HardwareMap map){
        shooter = hardwareMap.servo.get("shooterServo");
        shooter.setDirection(Servo.Direction.FORWARD);
    }

    //motor speeds
    public void setShooterSpeed(double speed){
        outtakeMotor.setPower(speed);
        PARAMS.shootingspeed = speed;
    }

    public void increaseShooterSpeed (){
        PARAMS.shootingspeed += 0.01;
        setShooterSpeed(PARAMS.shootingspeed);
    }

    public void decreaseShooterSpeed (){
        PARAMS.shootingspeed -= 0.01;
        setShooterSpeed(PARAMS.shootingspeed);
    }

    //shooting angle
    public void setAnglePosition(double newPos){
        shooter.setPosition(newPos);
        targetShooterPosition = newPos;

    }

    public void increaseAnglePosition(){
        targetShooterPosition += 0.01;
        setAnglePosition(targetShooterPosition);

    }

    public void decreaseAnglePosition(){
        targetShooterPosition -= 0.01;
        setAnglePosition(targetShooterPosition);
    }


}
