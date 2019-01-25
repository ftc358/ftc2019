package org.firstinspires.ftc.teamcode.Team358;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class Playground extends Robot358Main {

    public void runOpMode() throws InterruptedException {

        waitForStart();

        latchModule.start();
    }
}
