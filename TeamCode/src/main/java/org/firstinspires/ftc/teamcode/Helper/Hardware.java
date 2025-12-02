package org.firstinspires.ftc.teamcode.Helper;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

public class Hardware {

    public static DcMotor frontLeft;
    public static DcMotor frontRight;
    public static DcMotor backLeft;
    public static DcMotor backRight;

    public static DcMotor intakeMotor;
    public static DcMotor outtakeMotor;

    public static WebcamName camera;

    public static void init(HardwareMap map) {
        frontLeft  = map.get(DcMotor.class, "frontLeft");
        frontRight = map.get(DcMotor.class, "frontRight");
        backLeft   = map.get(DcMotor.class, "rearLeft");
        backRight  = map.get(DcMotor.class, "rearRight");

        intakeMotor  = map.get(DcMotor.class, "intakeMotor");
        outtakeMotor = map.get(DcMotor.class, "outtakeMotor");


    }
}
