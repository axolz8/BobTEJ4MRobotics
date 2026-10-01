package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Auton")


/**
* This file contains a minimal example of a Linear "OpMode". An OpMode is a 'program' that runs
* in either the autonomous or the TeleOp period of an FTC match. The names of OpModes appear on
* the menu of the FTC Driver Station. When an selection is made from the menu, the corresponding
* OpMode class is instantiated on the Robot Controller and executed.
*
* Remove the @Disabled annotation on the next line or two (if present) to add this OpMode to the
* Driver Station OpMode list, or add a @Disabled annotation to prevent this OpMode from being
* added to the Driver Station.
*/

public class Auton extends LinearOpMode {
private DcMotor leftmotor;
private DcMotor rightmotor;


@Override
public void runOpMode() {
leftmotor = hardwareMap.get(DcMotor.class, "left");
rightmotor = hardwareMap.get(DcMotor.class, "right");
leftmotor.setDirection(DcMotor.Direction.REVERSE);
leftmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
rightmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

telemetry.addData("Status", "Initialized");
telemetry.update();
// Wait for the game to start (driver presses PLAY)
waitForStart();


// run until the end of the match (driver presses STOP)
if (opModeIsActive()) {
telemetry.addData("Status", "Running");
telemetry.update();

leftmotor.setPower(1);
rightmotor.setPower(1);

sleep(1000);

leftmotor.setPower(1) ;
rightmotor.setPower(-1);

sleep(1000);

leftmotor.setPower(1);
rightmotor.setPower(1);

sleep(1000);

leftmotor.setPower(1);
rightmotor.setPower(-1);

sleep(1000);

leftmotor.setPower(1);
rightmotor.setPower(1);

sleep(1000);

leftmotor.setPower(1);
rightmotor.setPower(-1);

sleep(1000);

leftmotor.setPower(1);
rightmotor.setPower(1);
//one second is just a placeholder for now.Have to test if its enough for the robot to turn approximately 90 degrees
sleep(200);

}
}

}
