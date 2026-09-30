package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Teleoptest")
public class Teleop extends OpMode {

private DcMotor leftmotor;
private DcMotor rightmotor;
private static final double TRIGGER_THRESHOLD = 0.5;

@Override
public void init() {
leftmotor = hardwareMap.get(DcMotor.class, "left");
rightmotor = hardwareMap.get(DcMotor.class, "right");
leftmotor.setDirection(DcMotor.Direction.REVERSE);
}
@Override
public void loop() {
double y = -gamepad1.left_stick_y;
double x = gamepad1.left_stick_x;

//0.4 decreases the power of the motors, it caps the power at 40%
double leftmotorpower = (y + x)*0.4;
double rightmotorpower = (y - x)*0.4;

leftmotor.setPower(leftmotorpower);
rightmotor.setPower(rightmotorpower);

}
}
