package org.firstinspires.ftc.teamcode.Helper;
import static org.firstinspires.ftc.teamcode.Helper.Hardware.outtakeMotor;
import com.qualcomm.robotcore.hardware.DcMotor;
import static org.firstinspires.ftc.teamcode.Helper.Hardware.shooterServo;




public class Shoot {

    private final DcMotor leftMotor;
    private final DcMotor rightMotor;

    public static class Params {
        public double shootingspeed = 0.00;
    }

    public static double targetShooterPosition = 0.00;

    public static Params PARAMS = new Params();
    public Shoot(DcMotor leftMotor, DcMotor rightMotor) {
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;

        this.leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        this.leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setPower(double leftPower, double rightPower) {
        leftMotor.setPower(leftPower);
        rightMotor.setPower(rightPower);
    }
    public void stop() {
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
    public double getLeftPower() {
        return leftMotor.getPower();
    }
    public double getRightPower() {
        return rightMotor.getPower();
    }

    public void setShooterSpeed(double speed){
        outtakeMotor.setPower(speed);
        PARAMS.shootingspeed = speed;
    }

    public void increaseShooterSpeed (){
        PARAMS.shootingspeed += 0.01;
        setShooterSpeed(PARAMS.shootingspeed);
    }

    public void decreaseShooterSpeed (){
        PARAMS.shootingspeed -= 0.01;
        setShooterSpeed(PARAMS.shootingspeed);
    }

    //shooting angle
    public void setAnglePosition(double newPos){
        shooterServo.setPosition(newPos);
        targetShooterPosition = newPos;

    }

    public void increaseAnglePosition(){
        targetShooterPosition += 0.01;
        setAnglePosition(targetShooterPosition);

    }

    public void decreaseAnglePosition(){
        targetShooterPosition -= 0.01;
        setAnglePosition(targetShooterPosition);
    }

}
