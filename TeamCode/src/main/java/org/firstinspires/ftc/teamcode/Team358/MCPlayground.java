package org.firstinspires.ftc.teamcode.Team358;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class MCPlayground extends Robot358Main {

    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        latchModule = new LatchModule(latch);
//            @Override
//            public void run() {
//                super.run();
//                super.unlatch();
//                if (Thread.interrupted()) {
//                    telemetry.addData("Latch module operation interrupted", "!!!");
//                }


//            @Override
//            public void unlatch() {
//                latchMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//                latchMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//                latchMotor.setTargetPosition(7000);
//
//                latchMotor.setPower(1);
//
//                 while (latchMotor.isBusy()) {
//                    //Wait Until Target Position is Reached
//                    telemetry.addData("Current Position", latch.getCurrentPosition());
//                }
//
//                latchMotor.setPower(0);
//            }
//        };

        latchModule.start();

    }
}
