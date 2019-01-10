package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class MotorTestTeleop extends OpMode {

    DcMotor Motor1;

    public void init() {
        Motor1 = hardwareMap.dcMotor.get("Motor1");
    }

    public void loop() {

        if (gamepad1.left_bumper)                                   //Slide Extend
        {
            Motor1.setPower(1);
        } else if (gamepad1.right_bumper) {
            Motor1.setPower(-1);
        } else {
            Motor1.setPower(0);
        }
    }
}