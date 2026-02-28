package org.firstinspires.ftc.teamcode.Auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.bylazar.configurables.annotations.Configurable;

import org.firstinspires.ftc.teamcode.Helper.Hardware;
import org.firstinspires.ftc.teamcode.Helper.Pusher;
import org.firstinspires.ftc.teamcode.Helper.Shooter;
import org.firstinspires.ftc.teamcode.Helper.Spindexer;
import org.firstinspires.ftc.teamcode.Helper.Turret;

@Autonomous(name = "AutonREDDD3", group = "Auton")
@Configurable
public class BasicAutonRed extends LinearOpMode {

    public static long forwardTime = 650;
    public static long waitTime = 500;
    public static long turn45Time = 150;
    public static long forwardToGoalTime = 50;
    public static long shootingTime = 3000;

    //239 pose 1
    //784
   // 1281


    @Override
    public void runOpMode() {
        Hardware.init(hardwareMap);
        waitForStart();
        Hardware.frontLeft.setPower(0.8);
        Hardware.frontRight.setPower(1);
        Hardware.backLeft.setPower(0.8);
        Hardware.backRight.setPower(1);
        sleep(650);
        Hardware.frontLeft.setPower(0);
        Hardware.frontRight.setPower(0);
        Hardware.backLeft.setPower(0);
        Hardware.backRight.setPower(0);
        sleep(600);
        Hardware.frontLeft.setPower(1);
        Hardware.frontRight.setPower(0);
        Hardware.backLeft.setPower(1);
        Hardware.backRight.setPower(0);
        telemetry.addLine("Drive V1")
                .addData("Front Left", Hardware.frontLeft.getPower())
                .addData("Front Right", Hardware.frontRight.getPower())
                .addData("Back Left", Hardware.backLeft.getPower())
                .addData("Back Right", Hardware.backRight.getPower());
        sleep(450);
       /* Hardware.turretMotor.setPower(0.3);
        sleep(turn45Time);
        Hardware.outtakeMotor.setPower(1);
        push.comboMove();
        sleep(forwardTime);
        spinner.moveToSecond();
        sleep(turn45Time);
        Hardware.outtakeMotor.setPower(1);
        push.comboMove();
        sleep(forwardTime);
        spinner.moveToThird();
        sleep(turn45Time);
        Hardware.outtakeMotor.setPower(1);
        push.comboMove();
        sleep(forwardTime);*/

    }




}
