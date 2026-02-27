package org.firstinspires.ftc.teamcode;

import static java.lang.Boolean.FALSE;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Helper.GamePad;

import org.firstinspires.ftc.teamcode.Helper.DriveTrain;
import org.firstinspires.ftc.teamcode.Helper.GamePad;
import org.firstinspires.ftc.teamcode.Helper.Hardware;
import org.firstinspires.ftc.teamcode.Helper.Spindexer;
import org.firstinspires.ftc.teamcode.Helper.Shooter;
import org.firstinspires.ftc.teamcode.Helper.Pusher;
import org.firstinspires.ftc.teamcode.Helper.Turret;


@TeleOp(name = "Driver Control v3.45", group = "Competition")
public class DriverControl extends LinearOpMode {

    private DriveTrain drive;
    private Spindexer spinner;
    private Shooter outtake;
    private Turret turret;
    private Pusher push;
    private GamePad gp1, gp2;
    private boolean reversed = false;
    private double speedMultiplier = 0.9;
    public static double onIn = 0;
    int spindexerState = 0;

    boolean outakeOn = false;
    boolean lastAState = false;
    boolean lastSState = false;


    @Override
    public void runOpMode() {

        double pos = 0;

        Hardware.init(hardwareMap);

        telemetry.addLine("Driver Control ")
                .addData("Version", "2");
        telemetry.update();


        drive = new DriveTrain();
        spinner = new Spindexer(hardwareMap);
        outtake = new Shooter(hardwareMap);
        push = new Pusher();
        turret = new Turret(Hardware.turretMotor);

        boolean lastBack = false;
        boolean lastA = false;
        boolean lastB = false;

        // initialization
        push.moveDown();

        waitForStart();

        while (opModeIsActive()) {

            // GAMEPAD 1
            boolean backPressed = gamepad1.back && !lastBack;
            if (backPressed) reversed = !reversed;
            lastBack = gamepad1.back;

            if (gamepad1.dpad_down)  speedMultiplier = 0.25;
            if (gamepad1.dpad_left)  speedMultiplier = 0.75;
            if (gamepad1.dpad_right) speedMultiplier = 0.50;
            if (gamepad1.dpad_up)    speedMultiplier = 1.00;


            drive.setDriveVectorFromJoystick(
                    gamepad1.left_stick_x  * (float) speedMultiplier,
                    -gamepad1.right_stick_x * (float) speedMultiplier,
                    -gamepad1.left_stick_y * (float) speedMultiplier,
                    reversed
            );

            // spindexer
            if(gamepad1.a){
                spinner.moveZeroPos();
            }
            if(gamepad1.b){
                spinner.moveToSecond();
            }
            if(gamepad1.x){
                spinner.moveToThird();
            }
            spinner.Update();

            // outtake
            boolean currentAState = gamepad2.a;

            if(currentAState && !lastAState) {
                outakeOn = !outakeOn;
            }
            if(outakeOn){
                outtake.motorPowerMax();
            } else {
                outtake.motorPowerZero();
            }
            lastAState = currentAState;

            // intake
            double intakePower = gamepad2.right_trigger - gamepad2.left_trigger; // forward - reverse
            Hardware.intakeMotor.setPower(intakePower);

            // pusher
            if(gamepad1.y){
                push.comboMove();
            }

            // turret
            turret.update(
                    gamepad2.right_stick_x,
                    gamepad2.left_bumper,
                    gamepad2.x,
                    gamepad2.b,
                    gamepad2.y
            );

            // telemetry
            telemetry.addLine("Drive V1")
                    .addData("Front Left", Hardware.frontLeft.getPower())
                    .addData("Front Right", Hardware.frontRight.getPower())
                    .addData("Back Left", Hardware.backLeft.getPower())
                    .addData("Back Right", Hardware.backRight.getPower());

            telemetry.addLine("Shooter/Intake")
                    .addData("Intake Motor", Hardware.intakeMotor.getPower())
                    .addData("Outtake Motor", Hardware.outtakeMotor.getPower());

            telemetry.addLine("Spindexer")
                    .addData("Spin Motor", Hardware.spinnerMotor.getCurrentPosition());

            telemetry.addLine("Pusher")
                    .addData("Push Servo", Hardware.pushServo.getPosition())
                    .addData("Spin Position", spindexerState);

            updateTelemetry(telemetry);
        }
    }
}