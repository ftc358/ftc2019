package org.firstinspires.ftc.teamcode.Archive;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp

public class Intake359 extends LinearOpMode {
    Servo servo = null;

    public void runOpMode() throws InterruptedException {

        servo = hardwareMap.servo.get("servo");

        while (opModeIsActive()) {
            servo.setPosition(gamepad1.left_stick_y);
            idle();
        }
    }
}