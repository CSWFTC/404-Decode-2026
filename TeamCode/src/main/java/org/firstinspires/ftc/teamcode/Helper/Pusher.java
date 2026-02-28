package org.firstinspires.ftc.teamcode.Helper;
import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import android.os.SystemClock;
public class Pusher {
/*Notes
*
*  */

    public static class Params{

        public double maxPos = 0.087;
        public double minPos = 0.00;
        public int comboTime = 150;
    }
    public static Pusher.Params PARAMS = new Pusher.Params();


    public static double targetPusherPosition = -1;
    public void moveUp(){
        Hardware.pushServo.setPosition(PARAMS.maxPos);
        targetPusherPosition = PARAMS.maxPos;
    }

    public void moveDown() {
        Hardware.pushServo.setPosition(PARAMS.minPos);
        targetPusherPosition = PARAMS.minPos;
    }

    public void comboMove(){
        moveUp();
        SystemClock.sleep(PARAMS.comboTime);
        moveDown();

    }
}
