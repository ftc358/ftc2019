package org.firstinspires.ftc.teamcode.Team358;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.TimeLimitedCodeBlock;

import java.util.concurrent.TimeUnit;

@Autonomous
public class AutoD358 extends Robot358Main {

    int detected = 0;

    public void runOpMode() throws InterruptedException {

        initialize();

        telemetry.addData("Initialization:", "done");
        telemetry.update();

        double power = 1;
        boolean runUsingEncoders = true;
        state358 = state.DETECT;
        box.setPosition(0.6);
        waitForStart();

        while (opModeIsActive()) {

            telemetry.addData("Going into state", state358);
            telemetry.update();
            switch (state358) {
                case UNLATCH:                                   // unlatch and orient 90 degrees

                    double startingHeading = getCurrentHeading();

                    runMotor(latch, 1, 7000);

                    forward(power, 3);
                    double descendedHeading = getCurrentHeading();
                    double headingChange = descendedHeading + startingHeading;
                    turn(new IMUTurner(-90 - headingChange, power, _imu1, 1, null), true, true);
                    strafe(power, 4);

                    state358 = state.DETECT;
                    break;

                case DETECT:   // detect

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
                        turn(new IMUTurner(-30, power, _imu1, 1, null), runUsingEncoders, true);
//                        turn(new IMUTurner(-15, power, _imu1, 1, null), runUsingEncoders, true);
                        forward(power, 34);
                        turn(new IMUTurner(60, power, _imu1, 1, null), runUsingEncoders, true);
                    } else if (detected == 2) {
//                        turn(new IMUTurner(10, power, _imu1, 1, null), runUsingEncoders, true);
//                        turn(new IMUTurner(5, power, _imu1, 1, null), runUsingEncoders, true);
                        forward(power, 31);
                    } else if (detected == 3) {
                        turn(new IMUTurner(30, power, _imu1, 1, null), runUsingEncoders, true);
//                        turn(new IMUTurner(25, power, _imu1, 1, null), runUsingEncoders, true);
                        forward(power, 34);
                        turn(new IMUTurner(-50, power, _imu1, 1, null), runUsingEncoders, true);
                    }
                    state358 = state.DROP;
                    break;

                case DROP:                                    // drive to depot & drop token

                    runMotor(lift, 1, -2000);
                    // extend motor: 200 ticks for 1 inch
                    runMotor(extend, 1, 3000);
                    box.setPosition(0);
                    intake.setPower(-1);
                    sleep(500);
                    intake.setPower(0);
                    runMotor(lift, 1, 2000);

                    state358 = state.DRIVE;
                    break;

                case DRIVE:// drive to enemy crater

                    if (detected == 1) {
                        turn(new IMUTurner(-150, power, _imu1, 1, null), runUsingEncoders, true);
                        forward(power, 25);
                    } else if (detected == 2) {
                        forward(power, -15);
                        turn(new IMUTurner(-90, power, _imu1, 1, null), runUsingEncoders, true);
                        forward(power, 36);
                        turn(new IMUTurner(-30, power, _imu1, 1, null), runUsingEncoders, true);
                    } else if (detected == 3) {
                        forward(power, -15);
                        turn(new IMUTurner(135, power, _imu1, 1, null), runUsingEncoders, true);
                        forward(power, 18);
                        turn(new IMUTurner(30, power, _imu1, 1, null), runUsingEncoders, true);
                    }
                    state358 = state.CRATER;
                    break;

                case CRATER:

                    runMotor(lift, 1, -5000);
                    state358 = state.STOP;
                    break;

                case STOP:                                      // self explanatory

                    stopMotors();

            }
        }
    }
}

