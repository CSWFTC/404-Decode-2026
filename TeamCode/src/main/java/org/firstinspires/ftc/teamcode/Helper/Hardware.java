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
    public static DcMotor turretMotor;

    public static WebcamName camera;

    public static DcMotor spinnerMotor;
    public static Servo pushServo;


    public static void init(HardwareMap map) {

        //wheels
        frontLeft  = map.get(DcMotor.class, "frontLeft");
        frontRight = map.get(DcMotor.class, "frontRight");
        backLeft   = map.get(DcMotor.class, "rearLeft");
        backRight  = map.get(DcMotor.class, "rearRight");



        //intake and outake system
        intakeMotor  = map.get(DcMotor.class, "intakeMotor");
        outtakeMotor = map.get(DcMotor.class, "outtakeMotor");
        turretMotor = map.get(DcMotor.class, "turretMotor");

        spinnerMotor = map.get(DcMotor.class,"spinnerMotor");
        turretMotor = map.get(DcMotor.class, "turretMotor");


        pushServo = map.get(Servo.class,"pushServo");

        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);


        intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        spinnerMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        turretMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        outtakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        pushServo.setDirection(Servo.Direction.REVERSE);
    }




}
