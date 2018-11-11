package org.firstinspires.ftc.teamcode.Archive;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
@TeleOp
public class CalvinTestDrive extends OpMode {

    DcMotor motorLeft;
    DcMotor motorRight;

    public void init() {
        motorLeft = hardwareMap.dcMotor.get("m1");
        motorRight = hardwareMap.dcMotor.get("m2");
        motorRight.setDirection(DcMotor.Direction.REVERSE);
    }

    public void loop() {
        motorLeft.setPower(-gamepad1.right_stick_x);
        motorRight.setPower(gamepad1.right_stick_x);
    }
}