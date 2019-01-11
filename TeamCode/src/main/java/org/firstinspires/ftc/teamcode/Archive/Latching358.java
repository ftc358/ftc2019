package org.firstinspires.ftc.teamcode.Archive;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp
@Disabled
public class Latching358 extends LinearOpMode {
    CRServo servo358;

    public void runOpMode() throws InterruptedException {
        servo358 = hardwareMap.crservo.get("servo358");

        waitForStart();
        while (opModeIsActive()) {

            servo358.setPower(gamepad1.left_stick_y);

        }
    }
}