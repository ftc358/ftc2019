package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareDevice;

@Autonomous

public class RevEncoderTest extends LinearOpMode{

    DcMotor Motor1 = null;
    DcMotor Motor2 = null;

    public void runOpMode() throws InterruptedException {

        Motor1 = hardwareMap.dcMotor.get("Motor1");
        Motor2 = hardwareMap.dcMotor.get("Motor2");

    }

}
