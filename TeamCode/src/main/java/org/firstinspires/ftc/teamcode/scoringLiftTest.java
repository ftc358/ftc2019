package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp

public class scoringLiftTest extends LinearOpMode {

    DcMotor lift;
    double power = 0;

    public void runOpMode() throws InterruptedException {

        lift = hardwareMap.dcMotor.get("lift");

        waitForStart();

        while (opModeIsActive()) {

            lift.setPower(gamepad1.left_stick_y);
            telemetry.addData("joystick", gamepad1.left_stick_y);

        }
    }
}