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

    public static Servo shooterServo;

    public static void init(HardwareMap map) {

        //wheels
        frontLeft  = map.get(DcMotor.class, "frontLeft");
        frontRight = map.get(DcMotor.class, "frontRight");
        backLeft   = map.get(DcMotor.class, "rearLeft");
        backRight  = map.get(DcMotor.class, "rearRight");


        //intake and outake system
        intakeMotor  = map.get(DcMotor.class, "intakeMotor");
        outtakeMotor = map.get(DcMotor.class, "outtakeMotor");

        //servo
        shooterServo = map.get(Servo.class, "shooterServo");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.REVERSE);

        outtakeMotor.setDirection(DcMotor.Direction.REVERSE);

        shooterServo.setDirection(Servo.Direction.FORWARD);
    }

 /*   public Hardware(@NonNull HardwareMap map){
        shooterServo = hardwareMap.servo.get("shooterServo");
        shooterServo.setDirection(Servo.Direction.FORWARD);
    }*/



}
