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

    DcMotor topLeftMotor;
    DcMotor topRightMotor;
    DcMotor bottomLeftMotor;
    DcMotor bottomRightMotor;

    @Override

    public void runOpMode() {

        topLeftMotor = hardwareMap.get(DcMotor.class, "topLeftMotor");
        topRightMotor = hardwareMap.get(DcMotor.class, "topRightMotor");
        bottomLeftMotor = hardwareMap.get(DcMotor.class, "bottomLeftMotor");
        bottomRightMotor = hardwareMap.get(DcMotor.class, "bottomRightMotor");





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
        topLeftMotor.setPower(power);
        topRightMotor.setPower(power);
        bottomLeftMotor.setPower(power);
        bottomRightMotor.setPower(power);
        sleep(time);
        stopMotors();
    }

    public void stopMotors() {
        topLeftMotor.setPower(0.0);
        topRightMotor.setPower(0.0);
        bottomLeftMotor.setPower(0.0);
        bottomRightMotor.setPower(0.0);
    }

    //For turning, a positive number = right turn
    public void turn(double power, long time) {
        topLeftMotor.setPower(power);
        topRightMotor.setPower(power);
        bottomLeftMotor.setPower(-power);
        bottomRightMotor.setPower(-power);
        sleep(time);
        stopMotors();
    }
}