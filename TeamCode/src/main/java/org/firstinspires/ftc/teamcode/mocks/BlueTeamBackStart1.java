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
public class BlueTeamBackStart1 extends LinearOpMode {

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
            moveForward(0.5, 150);
            turn(-0.5,100); //Look towards goal
            //Shoot preloaded balls
            turn(-0.5,100);
            moveForward(0.5,300);
            turn(-0.5,200);
            moveForward(0.2, 2000);
            //Intake
            turn(0.5,100);
            moveForward(-0.5,400);
            turn(0.5,200);
            //Shoot
            turn(-0.5,200);
            moveForward(0.5,450);
            sleep(4000);
            //Player helper puts in balls (has 4 seconds)
            moveForward(-0.5,450);
            turn(0.5,300);
            //Shoot
            turn(0.5,300);
            moveForward(-0.5,450);//End in player helper zone
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

    public void strafeRight(double power, long time) {
        topLeftMotor.setPower(power);
        topRightMotor.setPower(-power);
        bottomLeftMotor.setPower(-power);
        bottomRightMotor.setPower(power);

        sleep(time);
        stopMotors();
    }

    public void strafeLeft(double power, long time) {
        topLeftMotor.setPower(-power);
        topRightMotor.setPower(power);
        bottomLeftMotor.setPower(power);
        bottomRightMotor.setPower(-power);

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