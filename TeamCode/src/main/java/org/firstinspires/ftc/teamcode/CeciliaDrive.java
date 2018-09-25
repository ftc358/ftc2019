package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp

public class CeciliaDrive extends OpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;

    public void init(){
        leftMotor=hardwareMap.dcMotor.get("left_motor");
        rightMotor=hardwareMap.dcMotor.get("right_motor");
    }

    public void loop(){
        leftMotor.setPower(gamepad1.left_stick_y);
        rightMotor.setPower(-gamepad1.right_stick_y);
    }
}