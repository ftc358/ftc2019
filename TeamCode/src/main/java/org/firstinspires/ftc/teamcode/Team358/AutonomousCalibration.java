package org.firstinspires.ftc.teamcode.Team358;

import android.media.MediaPlayer;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.R;

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

//    public void runOpMode() throws InterruptedException {
//
//        initialize();
//
//        runMotor(lift,1,-2000);
//
//        // extend motor: 200 ticks for 1 inch
//
//        runMotor(extend,1,3000);
//
//        box.setPosition(0);
//
//        sleep(1000);
//
//        box.setPosition(0.6);
//
//    }


    public void runOpMode() throws InterruptedException {
        MediaPlayer mp = MediaPlayer.create(hardwareMap.appContext, R.raw.roundabout);
        mp.start();
    }


}