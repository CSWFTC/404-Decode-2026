package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Helper.GamePad;
import org.firstinspires.ftc.teamcode.Helper.Shoot;

@Config
@TeleOp(name="Outtake Test", group="Hardware")
public class OuttakeTest extends LinearOpMode {


    public static class Params {
        public double sSpeed = 0.00;
        public double sPos = 0.00;
    }

    public static OuttakeTest.Params PARAMS = new OuttakeTest.Params();

    public void runOpMode(){

        Shoot shooting = new Shoot(hardwareMap);



        GamePad gpIn1 = new GamePad(gamepad1);

        waitForStart();

        while (opModeIsActive()){

            GamePad.GameplayInputType inputType1 = gpIn1.WaitForGamepadInput(30);
            switch(inputType1){

                case BUTTON_A:
                    shooting.setShooterSpeed(PARAMS.sSpeed);
                    break;

                case BUTTON_B:
                    shooting.setAnglePosition(PARAMS.sPos);
                    break;

                case BUTTON_X:
                    shooting.decreaseAnglePosition();
                    break;

                case BUTTON_Y:
                    shooting.increaseAnglePosition();
                    break;

            }


        }

    }


}
