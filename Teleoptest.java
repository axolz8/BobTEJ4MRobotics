package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Cheers")
public class Teleop extends OpMode {

    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;
    private DcMotor outtakemotor;
    private DcMotor intakemotor;
    private static final double TRIGGER_THRESHOLD = 0.5;

    @Override
    public void init() {
        leftFront = hardwareMap.get(DcMotor.class, "left_front");
        rightFront = hardwareMap.get(DcMotor.class, "right_front");
        leftBack = hardwareMap.get(DcMotor.class, "left_back");
        rightBack = hardwareMap.get(DcMotor.class, "right_back");
        outtakemotor = hardwareMap.get(DcMotor.class, "out-take");
        intakemotor = hardwareMap.get(DcMotor.class, "in-take");

        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {
        double rawY = -gamepad1.left_stick_y;
        double rawX = gamepad1.left_stick_x;
        double z = gamepad1.right_stick_x;
        double rawO = -gamepad1.right_trigger; 
        double rawI = gamepad1.left_trigger;

        double o;
        if (Math.abs(rawO) < 0.05) {
            o = 0.0;
        } else {
            o = rawO;
        }

        double i;
        if (Math.abs(rawI) < 0.05) {
            i = 0.0;
        } else {
            i = rawI;
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

        // Anti-turbo logic: Cap at 50% when RB is held, otherwise standard 70%
        double driveSpeedCap;
        if (gamepad1.right_bumper) {
            driveSpeedCap = 0.5;
        } else {
            driveSpeedCap = 0.7;
        }

        double outtakemotorpower = (o);
        double intakemotorpower = (i);
        double leftmotorpower  = (y + adjustedTurn) * driveSpeedCap;
        double rightmotorpower = (y - adjustedTurn) * driveSpeedCap;

        leftFront.setPower(leftmotorpower);
        leftBack.setPower(leftmotorpower);
        rightFront.setPower(rightmotorpower);
        rightBack.setPower(rightmotorpower);
        
        outtakemotor.setPower(outtakemotorpower);
        intakemotor.setPower(intakemotorpower);
    }
}