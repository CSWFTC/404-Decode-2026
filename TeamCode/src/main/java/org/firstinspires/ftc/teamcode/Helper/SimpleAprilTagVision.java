package org.firstinspires.ftc.teamcode.Helper;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

public class SimpleAprilTagVision {
    private final HardwareMap hardwareMap;
    private VisionPortal vp;
    private AprilTagProcessor processor;
    private boolean enabled = false;

    public SimpleAprilTagVision(HardwareMap map) {
        this.hardwareMap = map;
    }

    public void init() {
        processor = new AprilTagProcessor.Builder().build();
        vp = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam1"))
                .addProcessor(processor)
                .build();
        enabled = false;
    }

    public void enable() {
        if (vp != null && !enabled) {
            vp.resumeStreaming();
            vp.setProcessorEnabled(processor, true);
            enabled = true;
        }
    }

    public void disable() {
        if (vp != null && enabled) {
            vp.setProcessorEnabled(processor, false);
            vp.stopStreaming();
            enabled = false;
        }
    }

    public boolean isEnabled() {
        return enabled;
    }

    public AprilTagDetection getLatestDetection() {
        if (processor == null || !enabled) return null;
        List<AprilTagDetection> det = processor.getDetections();
        if (det.isEmpty()) return null;
        return det.get(0);
    }
}
