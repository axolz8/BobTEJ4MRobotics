package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Cheers")
public class Teleop extends OpMode {

private DcMotor leftmotor;
private DcMotor rightmotor;
private DcMotor outtakemotor;
private static final double TRIGGER_THRESHOLD = 0.5;

@Override
public void init() {
leftmotor = hardwareMap.get(DcMotor.class, "left");
rightmotor = hardwareMap.get(DcMotor.class, "right");
outtakemotor = hardwareMap.get(DcMotor.class, "out-take");
leftmotor.setDirection(DcMotor.Direction.REVERSE);
}
@Override
public void loop() {
double rawY = -gamepad1.left_stick_y;
double rawX = gamepad1.left_stick_x;
double z = gamepad1.right_stick_x;
double rawO = -gamepad1.right_trigger; 


double o;
if (Math.abs(rawO) < 0.05) {
o = 0.0;
}else {
o = rawO;
}

double y;
if (Math.abs(rawY) < 0.05) {
y = 0.0;
} else {
y = rawY;
}


double x;
if (Math.abs(rawX) < 0.05) {
x = 0.0;
} else {
x = rawX;
}


double horiTurn;
if (Math.abs(z) < 0.05) {
horiTurn = x; 
} else {
horiTurn = z;    
}

   
double adjustedTurn = horiTurn * 0.5;

double outtakemotorpower = (o);
double leftmotorpower  = (y + adjustedTurn) * 0.6;
double rightmotorpower = (y - adjustedTurn) * 0.6;

leftmotor.setPower(leftmotorpower);
rightmotor.setPower(rightmotorpower);
outtakemotor.setPower(outtakemotorpower);
}

}


//PLANS: 
/*
Update the Auton.java for the outtake motor
Power the motors up since gear ratio is higher now
*/