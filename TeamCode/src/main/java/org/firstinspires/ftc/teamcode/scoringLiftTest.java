package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp

public class scoringLiftTest extends LinearOpMode {

    DcMotor lift;

    public void runOpMode() throws InterruptedException {

        lift = hardwareMap.dcMotor.get("lift");

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.dpad_up){
                lift.setPower(1);
            }
            else if (gamepad1.dpad_down){
                lift.setPower(-1);
            }
            lift.setPower(0);

        }
    }
}