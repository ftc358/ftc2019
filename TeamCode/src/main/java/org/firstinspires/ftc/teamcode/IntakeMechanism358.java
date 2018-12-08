package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp

public class IntakeMechanism358 extends OpMode {
    DcMotor Motor;

    public void init() {
        Motor = hardwareMap.dcMotor.get("Motor");
        Motor.setDirection(DcMotor.Direction.REVERSE);
    }

    public void loop() {
        Motor.setPower(gamepad1.left_stick_y);
    }
}