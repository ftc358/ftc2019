package org.firstinspires.ftc.teamcode.Team358.AutoLegacy;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous
public class AutonomousCalibration extends Robot358Main {

//    public void runOpMode() throws InterruptedException {
////
////        initialize();
////
////        latch.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
////
////        latch.setMode(DcMotor.RunMode.RUN_TO_POSITION);
////
////        waitForStart();
////
////        latch.setTargetPosition(7000);
////
////        latch.setPower(1);
////
////        while (opModeIsActive() && latch.isBusy())
////        {
////            telemetry.addData("encoder-fwd", latch.getCurrentPosition() + "  busy=" + latch.isBusy());
////            telemetry.update();
////        }
////
////        latch.setPower(0.0);
////    }

    public void runOpMode() throws InterruptedException {

        initialize();

        latch.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        latch.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        waitForStart();

        latch.setTargetPosition(7000);

        latch.setPower(1);

        while (opModeIsActive() && latch.isBusy())
        {
            telemetry.addData("encoder-fwd", latch.getCurrentPosition() + "  busy=" + latch.isBusy());
            telemetry.update();
        }

        latch.setPower(0.0);
    }
}