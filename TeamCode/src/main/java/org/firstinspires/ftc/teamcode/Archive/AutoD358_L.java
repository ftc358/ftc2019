//package org.firstinspires.ftc.teamcode.Archive;
//
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//
//import org.firstinspires.ftc.teamcode.Team358.IMUTurner;
//import org.firstinspires.ftc.teamcode.Team358.Robot358Main;
//import org.firstinspires.ftc.teamcode.Archive.TimeLimitedCodeBlock;
//
//import java.util.concurrent.TimeUnit;
//
////@Autonomous
//@Disabled
//public class AutoD358_L extends Robot358Main {
//
//    int detected = 0;
//
//    public void runOpMode() throws InterruptedException {
//
//        initialize();
//
//        telemetry.addData("Initialization:", "done");
//        telemetry.update();
//
//        double power = 1;
//        boolean runUsingEncoders = true;
//        state358 = state.UNLATCH;
//        box.setPosition(0.6);
//        waitForStart();
//
//        while (opModeIsActive()) {
//
//            telemetry.addData("Going into state", state358);
//            telemetry.update();
//            switch (state358) {
//                case UNLATCH:                                   // unlatch and orient 90 degrees
//
//                    double startingHeading = getCurrentHeading();
//
//                    runMotor(latch, 1, 7000);
//
//                    double descendedHeading = getCurrentHeading();
//                    double headingChange = descendedHeading - startingHeading;
//                    forward(power, 3);
//                    turn(new IMUTurner(-90 + headingChange, power, _imu1, 1, null), true, true);
//                    strafe(power, 4);
//
//                    state358 = state.DETECT;
//                    break;
//
//                case DETECT:                       // detect
//                    turn(new IMUTurner(-10, power, _imu1, 1, null), runUsingEncoders, true);
//                    try {
//                        TimeLimitedCodeBlock.runWithTimeout(new Runnable() {
//                            @Override
//                            public void run() {
//                                detected = lookForwardAndCheck();
//                            }
//                        }, 5, TimeUnit.SECONDS);
//                    } catch (Exception e) {
//                        telemetry.addData("Timed out detecting", "setting detected = 2");
//                        telemetry.update();
//                        detected = 2;
//                    }
//                    deactivateVuforia();
//                    telemetry.addData("Position of the cube", detected);
//                    telemetry.update();
//                    state358 = state.KNOCK;
//                    break;
//
//                case KNOCK:                                    // knock gold block
//                    if (detected == 1) {
//                        turn(new IMUTurner(-20, power, _imu1, 1, null), runUsingEncoders, true);
//                        forward(power, 34);
//                        turn(new IMUTurner(60, power, _imu1, 1, null), runUsingEncoders, true);
//                    } else if (detected == 2) {
//                        turn(new IMUTurner(10, power, _imu1, 1, null), runUsingEncoders, true);
//                        forward(power, 31);
//                    } else if (detected == 3) {
//                        turn(new IMUTurner(40, power, _imu1, 1, null), runUsingEncoders, true);
//                        forward(power, 34);
//                        turn(new IMUTurner(-50, power, _imu1, 1, null), runUsingEncoders, true);
//                    }
//                    state358 = state.DROP;
//                    break;
//
//                case DROP:                                    // drive to depot & drop token
//
//                    runMotor(lift, 1, -2000);
//                    // extend motor: 200 ticks for 1 inch
//                    runMotor(extend, 1, 3000);
//                    box.setPosition(0);
//                    sleep(500);
//                    box.setPosition(0.6);
//                    runMotor(lift, 1, 2000);
//
//                    state358 = state.DRIVE;
//                    break;
//
//                case DRIVE:                                    // drive to enemy crater
//                    if (detected == 1) {
//                        turn(new IMUTurner(-130, power, _imu1, 1, null), runUsingEncoders, true);
//                        forward(power, 25);
//                    } else if (detected == 2) {
//                        forward(power, -15);
//                        turn(new IMUTurner(-90, power, _imu1, 1, null), runUsingEncoders, true);
//                        forward(power, 33);
//                        turn(new IMUTurner(-30, power, _imu1, 1, null), runUsingEncoders, true);
//                    } else if (detected == 3) {
//                        forward(power, -15);
//                        turn(new IMUTurner(-70, power, _imu1, 1, null), runUsingEncoders, true);
//                        forward(power, 53);
//                        turn(new IMUTurner(-30, power, _imu1, 1, null), runUsingEncoders, true);
//                    }
//                    state358 = state.CRATER;
//                    break;
//
//                case CRATER:
//                    runMotor(lift, 1, -2000);
//                    state358 = state.STOP;
//                    break;
//
//                case STOP:                                      // self explanatory
//
//                    stopMotors();
//            }
//        }
//    }
//
////    public void unlatchFromLander() throws InterruptedException {
////        double startingHeading = getCurrentHeading();
////
////        runMotor(latch, 1, 7000);
////
////        double descendedHeading = getCurrentHeading();
////        double headingChange = descendedHeading - startingHeading;
////        telemetry.addData("Heading change:", headingChange);
////        telemetry.update();
//////        turn(new IMUTurner(headingChange, power, _imu1, 1, null), true, true);
////        forward(power, 3);
////        strafe(power, 1);
////        turn(new IMUTurner(-90 + headingChange, power, _imu1, 1, null), true, true);
////        strafe(power, 4);
////    }
//
////    public void extend(Boolean drop) throws InterruptedException {
////        if (drop) {
////            runMotor(lift, 1, -2000);
////
////            // extend motor: 200 ticks for 1 inch
////
////            runMotor(extend, 1, 3000);
////
////            box.setPosition(0);
////
////            sleep(1000);
////
////            box.setPosition(0.6);
////
////            runMotor(lift, 1, 2000);
////        } else {
////            runMotor(lift, 1, -2000);
////        }
////    }
//}
