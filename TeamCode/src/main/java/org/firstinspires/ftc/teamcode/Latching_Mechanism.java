package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class Latching_Mechanism extends OpMode{

    DcMotor leftMotor;

    public void init() {
        leftMotor = hardwareMap.dcMotor.get("M");
    }
    public void loop() {
        leftMotor.setPower(gamepad1.left_stick_y);
    }
}
