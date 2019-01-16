package org.firstinspires.ftc.teamcode.Team358.AutoLegacy;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.TimeLimitedCodeBlock;

import java.util.concurrent.TimeUnit;

@Autonomous
public class AutoD358_L extends Robot358Main {

    int detected = 0;

    public void runOpMode() throws InterruptedException {

        initialize();

        telemetry.addData("Initialization:", "done");
        telemetry.update();

        double power = .5;
        boolean runUsingEncoders = true;
        state358 = state.UNLATCH;
        box.setPosition(0.6);
        waitForStart();

        while (opModeIsActive()) {

            telemetry.addData("Going into state", state358);
            telemetry.update();
            switch (state358) {
                case UNLATCH:                                   // unlatch and orient 90 degrees
                    unlatchFromLander();
                    state358 = state.DETECT;
                    break;

                case DETECT:                       // detect
                    turn(new IMUTurner(-10, power, _imu1, .25, null), runUsingEncoders, true);
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
                        turn(new IMUTurner(-20, power, _imu1, .25, null), runUsingEncoders, true);
                        forward(0.5, 34);
                        turn(new IMUTurner(60, power, _imu1, .25, null), runUsingEncoders, true);
                    } else if (detected == 2) {
                        turn(new IMUTurner(10, power, _imu1, .25, null), runUsingEncoders, true);
                        forward(0.5, 31);
                    } else if (detected == 3) {
                        turn(new IMUTurner(40, power, _imu1, .25, null), runUsingEncoders, true);
                        forward(0.5, 34);
                        turn(new IMUTurner(-60, power, _imu1, .25, null), runUsingEncoders, true);
                    }
                    state358 = state.CRATER;
                    break;

                case DROP:                                    // drive to depot & drop token
                    extend(true);
                    state358 = state.DRIVE;
                    break;

                case DRIVE:                                    // drive to enemy crater
                    if (detected == 1) {
                        turn(new IMUTurner(-130, power, _imu1, .25, null), runUsingEncoders, true);
                        forward(0.5, 25);
                    } else if (detected == 2) {
                        forward(0.5, -15);
                        turn(new IMUTurner(-90, power, _imu1, .25, null), runUsingEncoders, true);
                        forward(0.5, 33);
                        turn(new IMUTurner(-30, power, _imu1, .25, null), runUsingEncoders, true);
                    } else if (detected == 3) {
                        forward(0.5, -15);
                        turn(new IMUTurner(-80, power, _imu1, .25, null), runUsingEncoders, true);
                        forward(0.5, 53);
                        turn(new IMUTurner(-30, power, _imu1, .25, null), runUsingEncoders, true);
                    }
                    state358 = state.CRATER;
                    break;

                case CRATER:
                    extend(false);
                    state358 = state.STOP;
                    break;

                case STOP:                                      // self explanatory

                    stopMotors();
            }
        }
    }

    public void unlatchFromLander() throws InterruptedException {
        double startingHeading = getCurrentHeading();
        latch.setPower(-1);
        sleep(4900);
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

    public void extend(Boolean drop) {
        if (drop) {
            lift.setPower(-0.2);
            sleep(1500);
            lift.setPower(0);
            motorRun(extend, 0.5, 3000);
            box.setPosition(0);
            intake.setPower(-1);
            sleep(1000);
            intake.setPower(0);
            box.setPosition(0.4);
            motorRun(extend, 0.5, -3000);
            lift.setPower(0.2);
            sleep(1000);
            lift.setPower(0);
        } else {
            lift.setPower(-0.2);
            sleep(1500);
            lift.setPower(0);
            motorRun(extend, 0.5, 3000);
        }
    }
}
