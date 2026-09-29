package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Teleoptest")
public class Teleop extends OpMode {

private DcMotor leftmotor;
private DcMotor rightmotor;
private Servo clawServo;
private Servo armServo;
private DcMotor arm2;

private boolean clawOpen = false;
private boolean leftTriggerLast = false;
private boolean armUp = false;
private boolean rightTriggerLast = false;

private static final double TRIGGER_THRESHOLD = 0.5;

// --- Servo range restriction ---
// Instead of 0.0 to 1.0, restrict each servo to a safe sub-range.
// Tune these by testing carefully in small increments.
private static final double CLAW_MIN = -0.4;
private static final double CLAW_MAX = 0.5;

private static final double ARM_MIN = -1.0;
private static final double ARM_MAX = 0.5;

// --- Servo speed control ---
// How much position changes per loop cycle (~50 times/sec).
// Smaller = slower movement. Tune to taste.
private static final double CLAW_STEP = 0.10;
private static final double ARM_STEP = 0.10;

// Current actual position of each servo (since we move gradually)
private double clawCurrentPos;
private double armCurrentPos;

// Where we want the servo to end up
private double clawTargetPos;
private double armTargetPos;

@Override
public void init() {
leftmotor = hardwareMap.get(DcMotor.class, "left");
rightmotor = hardwareMap.get(DcMotor.class, "right");
leftmotor.setDirection(DcMotor.Direction.REVERSE);

clawServo = hardwareMap.get(Servo.class, "claw");
armServo = hardwareMap.get(Servo.class, "arm");
armServo.setDirection(Servo.Direction.REVERSE);


// Start both servos at their "closed/down" restricted position
clawCurrentPos = 1.0;
clawTargetPos = 1.0;
clawServo.setPosition(clawCurrentPos);

armCurrentPos = 0.2;
armTargetPos = 0.2;
armServo.setPosition(armCurrentPos);
}

@Override
public void loop() {
double y = -gamepad1.left_stick_y;
double x = gamepad1.right_stick_x;

double leftmotorpower = y + x;
double rightmotorpower = y - x;

leftmotor.setPower(leftmotorpower);
rightmotor.setPower(rightmotorpower);

/* Claw toggle on LEFT TRIGGER
boolean leftTriggerCurrent = gamepad1.left_trigger > TRIGGER_THRESHOLD;
if (leftTriggerCurrent && !leftTriggerLast) {
clawOpen = !clawOpen;
clawTargetPos = clawOpen ? CLAW_MAX : CLAW_MIN;
}
leftTriggerLast = leftTriggerCurrent;*/

// Arm toggle on RIGHT TRIGGER
boolean rightTriggerCurrent = gamepad1.right_trigger > TRIGGER_THRESHOLD;
if (rightTriggerCurrent && !rightTriggerLast) {
armUp = !armUp;
armTargetPos = armUp ? ARM_MAX : ARM_MIN;
clawOpen = !clawOpen;
clawTargetPos = clawOpen ? CLAW_MAX : CLAW_MIN;
}
rightTriggerLast = rightTriggerCurrent;

// Move claw gradually toward its target (this is the "speed" control)
clawCurrentPos = moveToward(clawCurrentPos, clawTargetPos, CLAW_STEP);
clawCurrentPos = clamp(clawCurrentPos, CLAW_MIN, CLAW_MAX);
clawServo.setPosition(clawCurrentPos);

// Move arm gradually toward its target
armCurrentPos = moveToward(armCurrentPos, armTargetPos, ARM_STEP);
armCurrentPos = clamp(armCurrentPos, ARM_MIN, ARM_MAX);
armServo.setPosition(armCurrentPos);
}

// Steps "current" toward "target" by at most "step" per call
private double moveToward(double current, double target, double step) {
if (Math.abs(target - current) <= step) {
return target;
}
return current + Math.signum(target - current) * step;
}

// Restricts a value to stay within [min, max]
private double clamp(double value, double min, double max) {
return Math.max(min, Math.min(max, value));
}
}

