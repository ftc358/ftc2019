package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp

public class Intake358 extends LinearOpMode {

    Servo Servo;

    public void runOpMode() throws InterruptedException {

        Servo = hardwareMap.servo.get("servo");

        waitForStart();

        while (opModeIsActive())
        {
            Servo.setPosition(gamepad1.left_stick_y);
//            idle();
        }
    }
}