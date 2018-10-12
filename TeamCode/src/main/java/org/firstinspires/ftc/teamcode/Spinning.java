package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class Spinning extends OpMode {

    DcMotor Motor1;
    private double maxSpeed = 1;

    public void init()
    {
        Motor1 = hardwareMap.dcMotor.get("Motor1");
    }

    public void loop()
    {
        if (gamepad1.a)
        {
            Motor1.setPower(maxSpeed);
        }
        else
        {
            Motor1.setPower(0);
        }
    }
}