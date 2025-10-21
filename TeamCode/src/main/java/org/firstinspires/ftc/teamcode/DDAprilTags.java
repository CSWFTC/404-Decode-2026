package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.rframeworks.di.Inject;
import com.rframeworks.di.Resolved;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;// no name yet
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.ExposureControl;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.GainControl;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static android.os.SystemClock.sleep;

public class DDAprilTags extends Resolved {
    @Inject("HardwareMap")
    HardwareMap hardwareMap;

    private VisionPortal visionPortal;
    private AprilTagProcessor aprilTagProcessor;
    private AprilTagDetection lastDetection;

    public DDAprilTags() {
    }

    public void init() {
        aprilTagProcessor = new AprilTagProcessor.Builder().build();
        aprilTagProcessor.setDecimation(2);
        visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam Back"))
                .addProcessor(aprilTagProcessor)
                .build();
        setManualExposure(6, 250);
    }

    public void update() {
        if (aprilTagProcessor == null) return;
        List<AprilTagDetection> detections = aprilTagProcessor.getDetections();
        if (detections.isEmpty()) {
            lastDetection = null;
        } else {
            for (AprilTagDetection d : detections) {
                if (d.metadata != null) {
                    lastDetection = d;
                    break;
                }
            }
        }
    }

    private void setManualExposure(int exposureMS, int gain) {
        if (visionPortal == null) return;
        if (visionPortal.getCameraState() != VisionPortal.CameraState.STREAMING) {
            while (visionPortal.getCameraState() != VisionPortal.CameraState.STREAMING) {
                sleep(20);
            }
        }
        ExposureControl exposureControl = visionPortal.getCameraControl(ExposureControl.class);
        if (exposureControl.getMode() != ExposureControl.Mode.Manual) {
            exposureControl.setMode(ExposureControl.Mode.Manual);
            sleep(50);
        }
        exposureControl.setExposure(exposureMS, TimeUnit.MILLISECONDS);
        sleep(20);
        GainControl gainControl = visionPortal.getCameraControl(GainControl.class);
        gainControl.setGain(gain);
        sleep(20);
    }

    public boolean hasDetection() {
        return lastDetection != null;
    }

    public AprilTagDetection getLastDetection() {
        return lastDetection;
    }

    public String getDetectionInfo() {
        if (lastDetection == null) return "No AprilTag detected";
        return String.format(
                "ID: %d | X: %.1f | Y: %.1f | Bearing: %.1f°",
                lastDetection.id,
                lastDetection.ftcPose.x,
                lastDetection.ftcPose.y,
                lastDetection.ftcPose.bearing
        );
    }
}
