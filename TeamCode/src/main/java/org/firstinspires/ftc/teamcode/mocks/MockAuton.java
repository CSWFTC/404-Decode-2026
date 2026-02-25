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

    DcMotor leftMotor;
    DcMotor rightMotor;

    @Override

    public void runOpMode() {

        leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");





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
        leftMotor.setPower(power);
        rightMotor.setPower(power);
        sleep(time);
        stopMotors();
    }

    public void stopMotors() {
        leftMotor.setPower(0.0);
        rightMotor.setPower(0.0);
    }

    //For turning, a positive number = right turn
    public void turn(double power, long time) {
        leftMotor.setPower(power);
        rightMotor.setPower(-power);
        sleep(time);
        stopMotors();
    }
}