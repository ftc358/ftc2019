package org.firstinspires.ftc.teamcode.Team358;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Team358.AutoLegacy.Robot358Main;

@Autonomous
public class AutonomousCalibration extends Robot358Main {

    public void runOpMode() throws InterruptedException {

        initialize();

        motorRun(latch, 0.5, 5);

    }
}