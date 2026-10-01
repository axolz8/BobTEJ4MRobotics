package org.firstinsipires.ftc.teamcode.mechanism;

import org.firstinsipires.ftc.robotcore.external.Telemetry;
import org.firstinsipires.ftc.vision.VisionPortal;
import org.firstinsipires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinsipires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.ArrayList;
import java.util.List;

public class Apriltag {
private AprilTagProcessor aprilTagProcessor;

private VisionPortal visionPortal;

private List<AprilTagDetection> detectedTags = new ArrayList<>();

private Telemetry telemetry;

public void init(Telemetry telemetry)

this.telemetry = telemetry; //the parameter telemetry is equal to the privtae var Telemetry 

aprilTagProcessor = new AprilTagProcessor.builder();
    .setDrawTagID(true);
    .setDrawTagOutline(true);
    .setDrawAxes(true)
    .setOutputUnits(DistanceUnit.CM, AngleUnit.DEGREES);
    .build();

VisionPortal.Builder builder = new VisionPortal.Builder();
builder.setCamera(HardwareMap.get(WebcamName.class, /*"WEBCAM NAME"*/));
builder.setCameraResolution(new Size(/*width, height in pixels*/));
builder.addProcessor(aprilTagProcessor);

visionPortal = builder.build();
}