package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class Latching_Mechanism extends OpMode{

    DcMotor leftMotor;
    DcMotor rightMotor;

    public void init() {
        leftMotor = hardwareMap.dcMotor.get("mL");
        rightMotor = hardwareMap.dcMotor.get("mR");
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
    }
    public void loop() {
        leftMotor.setPower(gamepad1.left_stick_y);
        rightMotor.setPower(gamepad1.left_stick_y);
    }
}