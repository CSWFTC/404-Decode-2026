package org.firstinspires.ftc.teamcode.Helper;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Outtake {

    public static DcMotor intakeMotor;
    public static DcMotor outtakeMotor;

    public static class Params {
        public double shootingspeed = 0.0;
    }
    public static void init(HardwareMap map) {

        intakeMotor  = map.get(DcMotor.class, "intakeMotor");
        outtakeMotor = map.get(DcMotor.class, "outtakeMotor");

    }

    public void Shoot(double shootingspeed){


    }




}
