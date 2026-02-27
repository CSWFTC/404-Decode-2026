package org.firstinspires.ftc.teamcode.mocks;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

//Move forward
//Turn left
//Move forward
//Turn left
//Move forward and intake
//Turn towards line
//Move towards line
//Turn towards goal
//Shoot

@Autonomous(name = "MockAuton")
public class MockAuton extends LinearOpMode {

    DcMotor frontLeft;
    DcMotor rearRight;
    DcMotor frontRight;
    DcMotor rearLeft;

    @Override

    public void runOpMode() {

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        rearLeft = hardwareMap.get(DcMotor.class, "frontRight");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        rearRight = hardwareMap.get(DcMotor.class, "rearRight");





        waitForStart();

        if (opModeIsActive()) {

            moveForward(0.5, 500);
            turn(-0.5, 300);
            moveForward(0.5, 500);
            turn(-0.5, 300);
            moveForward(0.2, 2000);
            //Intake
            turn(0.5, 150);
            moveForward(-0.5, 450);
            turn(-0.5, 200);
            //Shoot

        }
    }
    public void moveForward(double power, long time) {
        frontLeft.setPower(power);
        rearLeft.setPower(power);
        frontRight.setPower(power);
        rearRight.setPower(power);
        sleep(time);
        stopMotors();
    }

    public void stopMotors() {
        frontLeft.setPower(0.0);
        rearLeft.setPower(0.0);
        frontRight.setPower(0.0);
        rearRight.setPower(0.0);
    }

    //For turning, a positive number = right turn
    public void turn(double power, long time) {
        frontLeft.setPower(power);
        rearLeft.setPower(power);
        frontRight.setPower(-power);
        rearRight.setPower(-power);
        sleep(time);
        stopMotors();
    }


}