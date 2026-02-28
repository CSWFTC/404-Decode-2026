package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Helper.ColorSensor.LEDColor.GREEN;
import static org.firstinspires.ftc.teamcode.Helper.ColorSensor.LEDColor.RED;
import static java.lang.Boolean.FALSE;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Helper.ColorSensor;
import org.firstinspires.ftc.teamcode.Helper.DriveTrain;
import org.firstinspires.ftc.teamcode.Helper.GamePad;
import org.firstinspires.ftc.teamcode.Helper.Hardware;
import org.firstinspires.ftc.teamcode.Helper.Intake;
import org.firstinspires.ftc.teamcode.Helper.Spindexer;
import org.firstinspires.ftc.teamcode.Helper.Shooter;
import org.firstinspires.ftc.teamcode.Helper.Pusher;
import org.firstinspires.ftc.teamcode.Helper.Turret;
import org.firstinspires.ftc.teamcode.Helper.mechanisms.TestBench;


@TeleOp(name = "Driver Control v4.4 ", group = "Competition")
public class DriverControl extends LinearOpMode {

    private DriveTrain drive;
    private Spindexer spinner;
    private Shooter outtake;
    private Turret turret;
    private Pusher push;
    private Intake intake;
    private TestBench touch;
    private ColorSensor color;
    private GamePad gp1, gp2;
    private boolean reversed = false;
    private double speedMultiplier = 0.9;
    public static double onIn = 0;
    int spindexerState = 0;

    boolean outakeOn = false;
    boolean intakeOn = false;
    boolean lastAState = false;
    boolean lastBState = false;


    @Override
    public void runOpMode() {

        double pos = 0;

        Hardware.init(hardwareMap);

        telemetry.addLine("Driver Control 2-Motor")
                .addData("Version", "2");
        telemetry.update();


        drive = new DriveTrain();
        spinner = new Spindexer(hardwareMap);
        outtake = new Shooter(hardwareMap);
        push = new Pusher();
        turret = new Turret(Hardware.turretMotor);
        intake = new Intake(hardwareMap);
        color = new ColorSensor(hardwareMap);
        touch = new TestBench();


        boolean lastBack = false;
        boolean lastA = false;
        boolean lastB = false;

        //initalization
        push.moveDown();

        waitForStart();

        while (opModeIsActive()) {

            if(touch.getTouchSensorState()){
                color.setLEDColor(GREEN);
            }
            else{
                color.setLEDColor(RED);
            }
            //GAMEPAD 1
           /* boolean backPressed = gamepad1.back && !lastBack;
            if (backPressed) reversed = !reversed;
            lastBack = gamepad1.back;
i
            if (gamepad1.dpad_down)  speedMultiplier = 0.25;
            if (gamepad1.dpad_left)  speedMultiplier = 0.75;
            if (gamepad1.dpad_right) speedMultiplier = 0.50;
            if (gamepad1.dpad_up)    speedMultiplier = 1.00;*/


            drive.setDriveVectorFromJoystick(
                    gamepad1.left_stick_x  * (float) speedMultiplier,
                    -gamepad1.right_stick_x * (float) speedMultiplier,
                    -gamepad1.left_stick_y * (float) speedMultiplier,
                    reversed
            );

            if(gamepad1.b){
                push.comboMove();
            }

            //GAMEPAD 2

            //spindexer
            if(gamepad2.dpad_right){
                spinner.moveZeroPos();
            }
            if(gamepad2.dpad_down){
                spinner.moveToSecond();
            }
            if(gamepad2.dpad_left){
                spinner.moveToThird();
            }
            if(gamepad2.dpad_up){
              spinner.increasePos();
            };

            spinner.Update();

            boolean currentAState = gamepad2.a;

            if(currentAState && !lastAState) {
                outakeOn = !outakeOn;
            }
            if(outakeOn){
                outtake.motorPowerMax();
            }
            else{
                outtake.motorPowerZero();
            }
            lastAState = currentAState;


            turret.update(
                    gamepad2.right_stick_x,gamepad2.x
            );

            boolean currentBState = gamepad2.b;
            if(currentBState && !lastBState){
                intakeOn = !intakeOn; }

                if(intakeOn){
                    intake.motorPowerMax();
                }
                else{
                    intake.motorPowerZero();
                }
                lastBState = currentBState;


             /*
            boolean currentSState = gamepad2.dpad_left;
            if(currentSState && !lastSState){
                spindexerState++;
                if(spindexerState > 2){ spindexerState = 0;}

                if(spindexerState == 0){ spinner.moveZeroPos();}
                else if(spindexerState == 1) {spinner.moveToSecond();}
                else if(spindexerState == 2){spinner.moveToThird();}

            }*/

            telemetry.addLine("Drive V1")
                    .addData("Front Left", Hardware.frontLeft.getPower())
                    .addData("Front Right", Hardware.frontRight.getPower())
                    .addData("Back Left", Hardware.backLeft.getPower())
                    .addData("Back Right", Hardware.backRight.getPower());


            telemetry.addLine("Shooter/Intake")
                    // .addData("Intake Motor", Hardware.intakeMotor.getPower())
                    .addData("Outtake Motor", Hardware.outtakeMotor.getPower());


            telemetry.addLine("Spindexer")
                    .addData("Spin Motor", Hardware.spinnerMotor.getCurrentPosition());

            telemetry.addLine("Pusher")
                            .addData("Push Servo", Hardware.pushServo.getPosition())
                             .addData("spin position",spindexerState);
            updateTelemetry(telemetry);


        }
    }
}