package org.firstinspires.ftc.teamcode.Team358.AutoLegacy;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class AutonomousCalibration extends Robot358Main {

    public void runOpMode() throws InterruptedException {

        initialize();

        waitForStart();

        double startingHeading = getCurrentHeading();
        latch.setPower(-1);
        sleep(4700);
        latch.setPower(0);
        double descendedHeading = getCurrentHeading();
        double headingChange = descendedHeading - startingHeading;
        telemetry.addData("Heading change:", headingChange);
        telemetry.update();
        turn(new IMUTurner(headingChange, 0.5, _imu1, .25, null), true, true);
        forward(0.5, 3);
        strafe(0.5,1);
        turn(new IMUTurner(-90, 0.5, _imu1, .25, null), true, true);
        strafe(0.5, 4);
    }
}