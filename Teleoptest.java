package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Cheers")
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
double z = gamepad1.right_stick_x
//right stick should have the priortiy above the variable x in terms of controlling the motor's power
//0.6 decreases the power of the motors, it caps the power at 60%

/*
if x != 0 {


}


*/

double leftmotorpower = (y + x)*0.6;
double rightmotorpower = (y - x)*0.6;

leftmotor.setPower(leftmotorpower);
rightmotor.setPower(rightmotorpower);

}
}
/*
TO:DO
Turning is sensitive
Kevin suggested a deadzone
Make it so that left stick controls the motors both horizontally and vertically but right stick only controls the motors horizontally 
but has the priority to control the motors horizontally over left stick
*/