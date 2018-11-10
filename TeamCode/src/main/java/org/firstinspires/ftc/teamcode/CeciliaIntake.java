package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous

public class CeciliaIntake extends LinearOpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;

    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.dcMotor.get("left_motor");
        rightMotor = hardwareMap.dcMotor.get("right_motor");

        rightMotor.setDirection(DcMotor.Direction.REVERSE);

        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.dpad_up) {
                EncoderWithOnlyTwoFrontMotors.Forward(leftMotor, rightMotor, 0.1, 720);
            } else if (gamepad1.dpad_down) {
                EncoderWithOnlyTwoFrontMotors.Forward(leftMotor, rightMotor, 0.1, -720);
            }
        }
    }
}