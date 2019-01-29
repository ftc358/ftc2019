package org.firstinspires.ftc.teamcode.Team358;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.TimeLimitedCodeBlock;

import java.util.concurrent.TimeUnit;

@Disabled
@Autonomous
public class AutoC358_L extends Robot358Main {

    int detected = 0;

    public void runOpMode() throws InterruptedException {

        initialize();

        telemetry.addData("Initialization:", "done");
        telemetry.update();
        double power = .5;
        boolean runUsingEncoders = true;

        state358 = state.UNLATCH;
        waitForStart();

        while (opModeIsActive()) {

            telemetry.addData("Going into state", state358);
            telemetry.update();
            switch (state358) {
                case UNLATCH:                                   // unlatch and orient to starting position
                    unlatchFromLander();
                    state358 = state.DETECT;
                    break;

                case DETECT:                       // detect
                    turn(new IMUTurner(-20, power, _imu1, .25, null), runUsingEncoders, true);
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
                    telemetry.addData("Position of the cube", detected);
                    telemetry.update();
                    state358 = state.KNOCK;
                    break;

                case KNOCK:                                    // knock gold block
                    if (detected == 1) {
                        turn(new IMUTurner(-10, power, _imu1, .25, null), runUsingEncoders, true);
                        forward(0.5, 26);
                    } else if (detected == 2) {
                        turn(new IMUTurner(20, power, _imu1, .25, null), runUsingEncoders, true);
                        forward(0.5, 22);
                    } else if (detected == 3) {
                        turn(new IMUTurner(50, power, _imu1, .25, null), runUsingEncoders, true);
                        forward(0.5, 24);
                    }
                    state358 = state.DROP;
                    break;

                case DROP:                                    // drive to depot & drop token
                    if (detected == 1) {
                        turn(new IMUTurner(-85, power, _imu1, .25, null), runUsingEncoders, true);
                        forward(0.5, 46);
                        turn(new IMUTurner(30, power, _imu1, .25, null), runUsingEncoders, true);
                    } else if (detected == 2) {
                        forward(0.5, -10);
                        turn(new IMUTurner(-90, power, _imu1, .25, null), runUsingEncoders, true);
                        forward(0.5, -45);
                        turn(new IMUTurner(-45, power, _imu1, .25, null), runUsingEncoders, true);
                        forward(0.5, -14);
                    } else if (detected == 3) {
                        forward(0.5, -10);
                        turn(new IMUTurner(120, power, _imu1, .25, null), runUsingEncoders, true);
                        forward(0.5, 52);
                        turn(new IMUTurner(-45, power, _imu1, .25, null), runUsingEncoders, true);
                        forward(0.5, 14);
                    }
                    extend(true);
                    state358 = state.DRIVE;
                    break;

                case DRIVE:                                    // drive to enemy crater
                    if (detected == 1) {
                        turn(new IMUTurner(-30, power, _imu1, .25, null), runUsingEncoders, true);
                    } else if (detected == 2) {

                    } else if (detected == 3) {

                    }
                    forward(0.5, 13);
                    turn(new IMUTurner(-45, power, _imu1, .25, null), runUsingEncoders, true);
                    forward(0.5, 32);
                    turn(new IMUTurner(-45, power, _imu1, .25, null), runUsingEncoders, true);
                    forward(0.5, 38);
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
        sleep(4700);
        latch.setPower(0);
        double descendedHeading = getCurrentHeading();
        double headingChange = descendedHeading - startingHeading;
        telemetry.addData("Heading change:", headingChange);
        telemetry.update();
        turn(new IMUTurner(headingChange, 0.5, _imu1, .25, null), true, true);
        forward(0.5, 3);
        strafe(0.5, 1);
        turn(new IMUTurner(-90, 0.5, _imu1, .25, null), true, true);
        strafe(0.5, 4);
    }

    public void extend(Boolean drop) {
        if (drop) {

        } else {
            lift.setPower(-0.2);
            sleep(2000);
            lift.setPower(0);
            runMotor(extend, 0.5, 4000);
        }
    }
}