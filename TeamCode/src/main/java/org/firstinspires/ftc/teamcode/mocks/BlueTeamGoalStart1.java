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

@Autonomous(name = "BlueGoal")
public class BlueTeamGoalStart1 extends LinearOpMode {

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;

    @Override

    public void runOpMode() {

        frontLeft = hardwareMap.get(DcMotor.class, "topLeftMotor");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");





        waitForStart();

        if (opModeIsActive()) {
            moveForward(0.5, 1800);
            turn(-0.5, 300);
            //Shoot preloaded balls
            turn(-0.5,200);
            moveForward(0.5, 200);
            turn(0.5, 300);
            moveForward(0.2, 2000);
            //intake balls
            moveForward(-0.5, 450); //Get to shooting position1
            turn(-0.5, 100); //Look at goal
            //Shoot balls
            strafeLeft(0.5,300);
            turn(-0.5,100);
            moveForward(0.2, 2000);
            //intake balls
            turn(0.5,100);
            strafeRight(0.5, 450); //Get to shooting position2
            turn(-0.5, 100); //Look at goal
            //Shoot balls
            turn(-0.5, 150);
            strafeLeft(-0.5, 450); //Go around 3rd row of balls
            turn(0.5, 100);
            strafeLeft(-0.5, 300); //Strafe into Player Area
            turn(0.5, 250); // Spin to look towards the field so it's easy to drive
        }
    }
    public void moveForward(double power, long time) {
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
        sleep(time);
        stopMotors();
    }

    public void strafeRight(double power, long time) {
        frontLeft.setPower(power);
        frontRight.setPower(-power);
        backLeft.setPower(-power);
        backRight.setPower(power);

        sleep(time);
        stopMotors();
    }

    public void strafeLeft(double power, long time) {
        frontLeft.setPower(-power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(-power);

        sleep(time);
        stopMotors();
    }

    public void stopMotors() {
        frontLeft.setPower(0.0);
        frontRight.setPower(0.0);
        backLeft.setPower(0.0);
        backRight.setPower(0.0);
    }

    //For turning, a positive number = right turn
    public void turn(double power, long time) {
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(-power);
        backRight.setPower(-power);
        sleep(time);
        stopMotors();
    }
}