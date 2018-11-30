package org.firstinspires.ftc.teamcode.Archive;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp

public class Intake359 extends LinearOpMode {
    CRServo servo;

    public void runOpMode() throws InterruptedException {

        servo = hardwareMap.crservo.get("servo");

        while (opModeIsActive()) {
            servo.setPower(gamepad1.left_stick_y);
            idle();
        }
    }
}