package org.firstinspires.ftc.teamcode.Team358;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.TimeLimitedCodeBlock;

import java.util.concurrent.TimeUnit;

@Autonomous
public class AutoC358 extends Robot358Main {

    int detected = 0;

    public void runOpMode() throws InterruptedException {

        initialize();

        telemetry.addData("Initialization:", "done");
        telemetry.update();

        double power = 1;
        boolean runUsingEncoders = true;
        state358 = state.UNLATCH;
        box.setPosition(0.6);
        waitForStart();

        while (opModeIsActive()) {

            telemetry.addData("Going into state", state358);
            telemetry.update();
            switch (state358) {
                case UNLATCH:                                   // unlatch and orient 90 degrees

                    double startingHeading = getCurrentHeading();

                    runMotor(latch, 1, 7000);

//                    forward(power, 3);
//                    double descendedHeading = getCurrentHeading();
//                    double headingChange = descendedHeading - startingHeading;
//                    turn(new IMUTurner(-90 + headingChange, power, _imu1, 1, null), true, true);
////                    strafe(power, 3);

                    forward(power, 3);
                    double descendedHeading = getCurrentHeading();
                    double headingChange = descendedHeading + startingHeading;
                    telemetry.addData("headingChange",headingChange);
                    telemetry.addData("Turning by",-90 - headingChange);
                    telemetry.update();
                    turn(new IMUTurner(-90 - headingChange, power, _imu1, 1, null), true, true);
//                    strafe(power, 4);
//                    strafe(power, -2);
                    strafe(power, 2);

                    state358 = state.DETECT;
                    break;

                case DETECT:                       // detect
//                    turn(new IMUTurner(-10, power, _imu1, 1, null), runUsingEncoders, true);
                    turn(new IMUTurner(-5, power, _imu1, 1, null), runUsingEncoders, true);
                    try {
                        TimeLimitedCodeBlock.runWithTimeout(new Runnable() {
                            @Override
                            public void run() {
                                detected = lookForwardAndCheck();
                            }
                        }, 5, TimeUnit.SECONDS);
                    } catch (Exception e) {
                        telemetry.addData("Timed out detecting", "setting detected = 2");
                        telemetry.update();
                        detected = 2;
                    }
                    deactivateVuforia();
                    telemetry.addData("Position of the cube", detected);
                    telemetry.update();
                    state358 = state.KNOCK;
                    break;

                case KNOCK:                                    // knock gold block
                    if (detected == 1) {
                        turn(new IMUTurner(-15, power, _imu1, 1, null), runUsingEncoders, true);
                        forward(0.5, 28);
                    } else if (detected == 2) {
                        turn(new IMUTurner(5, power, _imu1, 1, null), runUsingEncoders, true);
                        forward(0.5, 24);
                    } else if (detected == 3) {
                        turn(new IMUTurner(25, power, _imu1, 1, null), runUsingEncoders, true);
                        forward(0.5, 28);
                    }
                    state358 = state.DROP;
                    break;

                case DROP:                                    // drive to depot & drop token
                    if (detected == 1) {
                        turn(new IMUTurner(-100, power, _imu1, 1, null), runUsingEncoders, true);
                        forward(0.5, 49);
//                        turn(new IMUTurner(-20, power, _imu1, 1, null), runUsingEncoders, true);
                    } else if (detected == 2) {
                        forward(0.5, -12);
                        turn(new IMUTurner(-90, power, _imu1, 1, null), runUsingEncoders, true);
                        forward(0.5, 45);
                        turn(new IMUTurner(-45, power, _imu1, 1, null), runUsingEncoders, true);
                        forward(0.5, 14);
                    } else if (detected == 3) {
                        forward(0.5, -15);
                        turn(new IMUTurner(-115, power, _imu1, 1, null), runUsingEncoders, true);
                        forward(0.5, 51);
                        turn(new IMUTurner(-55, power, _imu1, 1, null), runUsingEncoders, true);
                        forward(0.5, 14);
                    }

                    runMotor(lift, 1, 2000);
                    // extend motor: 200 ticks for 1 inch
                    runMotor(extend, 1, 3000);
                    box.setPosition(0);
                    intake.setPower(-1);
                    sleep(500);
                    intake.setPower(0);
                    runMotor(lift, 1, -2000);

                    state358 = state.DRIVE;
                    break;

                case DRIVE:                                    // drive to enemy crater
                    forward(0.5, -30);
                    turn(new IMUTurner(180, power, _imu1, 1, null), runUsingEncoders, true);
                    state358 = state.CRATER;
                    break;

                case CRATER:
                    runMotor(lift, 1, 5000);
                    state358 = state.STOP;
                    break;

                case STOP:                                      // self explanatory

                    stopMotors();
            }
        }
    }
}
