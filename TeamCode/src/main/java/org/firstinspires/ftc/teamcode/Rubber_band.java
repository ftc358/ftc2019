package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class Rubber_band extends OpMode{

    DcMotor Band;

    @Override

    public void init() {
        Band=hardwareMap.dcMotor.get("rB");
    }
    public void loop() {
        if (gamepad1.left_bumper){
            Band.setPower(0.3);
        }
        if (gamepad1.right_bumper){
            Band.setPower(-0.3);
        }

    }
}
