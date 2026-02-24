package org.firstinspires.ftc.teamcode.vision;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

public class VisionClassifier extends OpenCvPipeline {

    public static class Detection {
        public final Rect box;
        public final String color;
        public final double distanceIn;
        public final double offsetIn;

        public Detection(Rect b, String c, double d, double o) {
            box = b; color = c; distanceIn = d; offsetIn = o;
        }
    }

    private final Scalar greenLow  = new Scalar(35, 70, 80);
    private final Scalar greenHigh = new Scalar(85, 255, 255);

    private final Scalar purpleLow = new Scalar(125, 70, 80);
    private final Scalar purpleHigh= new Scalar(155, 255, 255);

    private final Mat hsv = new Mat();
    private final Mat greenMask = new Mat();
    private final Mat purpleMask = new Mat();

    private final List<Detection> detections = new ArrayList<>();

    private static final double BALL_DIAMETER_IN = 5.0;
    private static final double FOCAL_LENGTH_PX = 660.0;

    @Override
    public Mat processFrame(Mat input) {
        detections.clear();

        Imgproc.cvtColor(input, hsv, Imgproc.COLOR_RGB2HSV);

        Core.inRange(hsv, greenLow, greenHigh, greenMask);
        Core.inRange(hsv, purpleLow, purpleHigh, purpleMask);

        Imgproc.morphologyEx(greenMask, greenMask, Imgproc.MORPH_OPEN, new Mat());
        Imgproc.morphologyEx(purpleMask, purpleMask, Imgproc.MORPH_OPEN, new Mat());

        detectColor(input, greenMask, "GREEN", new Scalar(0,255,0));
        detectColor(input, purpleMask, "PURPLE", new Scalar(255,0,255));

        return input;
    }

    private void detectColor(Mat input, Mat mask, String label, Scalar drawColor) {
        List<MatOfPoint> contours = new ArrayList<>();
        Imgproc.findContours(mask, contours, new Mat(),
                Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        List<Rect> boxes = new ArrayList<>();
        for (MatOfPoint c : contours) {
            if (Imgproc.contourArea(c) < 400) continue;
            boxes.add(Imgproc.boundingRect(c));
        }

        List<Rect> finalBoxes = new ArrayList<>();
        for (Rect a : boxes) {
            boolean insideAnother = false;
            for (Rect b : boxes) {
                if (b == a) continue;
                if (b.contains(new Point(a.x, a.y)) &&
                        b.contains(new Point(a.x + a.width, a.y + a.height))) {
                    insideAnother = true;
                    break;
                }
            }
            if (!insideAnother) finalBoxes.add(a);
        }

        int frameCenterX = input.width() / 2;

        for (Rect r : finalBoxes) {
            double distance = (BALL_DIAMETER_IN * FOCAL_LENGTH_PX) / (double)r.width;

            int ballCenterX = r.x + r.width / 2;
            double offsetPx = ballCenterX - frameCenterX;
            double inchesPerPixel = BALL_DIAMETER_IN / (double)r.width;
            double offset = offsetPx * inchesPerPixel;

            detections.add(new Detection(r, label, distance, offset));

            Imgproc.rectangle(input, r, drawColor, 2);
            Imgproc.putText(input,
                    String.format("%s %.1f in, off %.1f %d px", label, distance, offset, r.width),
                    new Point(r.x, r.y - 8),
                    Imgproc.FONT_HERSHEY_SIMPLEX, 0.5, drawColor, 2);
        }
    }

    public List<Detection> getDetections() { return detections; }
}
