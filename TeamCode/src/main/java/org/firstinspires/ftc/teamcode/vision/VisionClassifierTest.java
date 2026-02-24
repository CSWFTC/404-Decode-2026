package org.firstinspires.ftc.teamcode.vision;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.*;

import org.firstinspires.ftc.teamcode.vision.VisionClassifier;

@TeleOp(name="VisionClassifier Test", group="Test")
public class VisionClassifierTest extends LinearOpMode {

    @Override
    public void runOpMode() {
        WebcamName webcam = hardwareMap.get(WebcamName.class, "Webcam1");

        int viewId = hardwareMap.appContext.getResources()
                .getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        OpenCvCamera camera =
                OpenCvCameraFactory.getInstance().createWebcam(webcam, viewId);

        VisionClassifier pipeline = new VisionClassifier();
        camera.setPipeline(pipeline);

        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT);
            }
            @Override
            public void onError(int errorCode) {}
        });

        waitForStart();

        while (opModeIsActive()) {
            java.util.List<VisionClassifier.Detection> detections = pipeline.getDetections();

            telemetry.addData("# balls", detections.size());
            try {
                for (int i = 0; i < detections.size(); i++) {
                    telemetry.addData(i + "",
                            detections.get(i).color + " @ " + detections.get(i).box.toString());
                }
            } catch(Exception e) {

            }
            telemetry.update();
        }

        camera.stopStreaming();
        camera.closeCameraDevice();
    }
}
