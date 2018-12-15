package org.firstinspires.ftc.teamcode.Archive;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
@Disabled
public class ExpansionHubTest extends LinearOpMode {

    DcMotor Motor;
   // CRServo Servo;

    public void runOpMode() {
        Motor = hardwareMap.dcMotor.get("motor");

        waitForStart();
        while (opModeIsActive()) {

            Motor.setPower(gamepad1.left_stick_y);
            //Servo.setPower(gamepad1.right_stick_y);
        }
    }
}