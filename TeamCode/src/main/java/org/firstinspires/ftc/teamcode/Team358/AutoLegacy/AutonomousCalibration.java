package org.firstinspires.ftc.teamcode.Team358.AutoLegacy;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class AutonomousCalibration extends Robot358Main {

    public void runOpMode() throws InterruptedException {

        initialize();

        waitForStart();

        strafe(1, 30);
    }
}