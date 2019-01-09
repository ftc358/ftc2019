package org.firstinspires.ftc.teamcode.Team358.AutoLegacy;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Team358.Encoders358;
import org.firstinspires.ftc.teamcode.TimeLimitedCodeBlock;

import java.util.concurrent.TimeUnit;

@Autonomous
public class AutoD358_R extends Robot358Main {

    int detected = 0;

    public void runOpMode() throws InterruptedException {
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
                case UNLATCH:                                   // unlatch and orient 90 degrees
                    unlatchFromLander();
                    state358 = state.DETECT;
                    break;

                case DETECT:                       // detect
                    turn(new IMUTurner(-15, power, _imu1, .25, null), runUsingEncoders, true);
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
                    turn(new IMUTurner(15, power, _imu1, .25, null), runUsingEncoders, true);
                    telemetry.addData("Position of the cube", detected);
                    telemetry.update();
                    state358 = state.KNOCK;
                    break;

                case KNOCK:                                    // knock gold block
                    if (detected == 1) {
                        turn(new IMUTurner(-15, power, _imu1, .25, null), runUsingEncoders, true);
                        forward(0.5,34);
                    } else if (detected == 2) {
                        turn(new IMUTurner(25, power, _imu1, .25, null), runUsingEncoders, true);
                        forward(0.5,31);
                    } else if (detected == 3) {
                        turn(new IMUTurner(45, power, _imu1, .25, null), runUsingEncoders, true);
                        forward(0.5,34);
                    }
                    state358 = state.DROP;
                    break;

                case DROP:                                    // drive to depot & drop token
                    if (detected == 1) {
                        turn(new IMUTurner(40, power, _imu1, .25, null), runUsingEncoders, true);
                    } else if (detected == 2) {

                    } else if (detected == 3) {
                        turn(new IMUTurner(-40, power, _imu1, .25, null), runUsingEncoders, true);
                    }
                    extend(true);
                    state358 = state.DRIVE;
                    break;

                case DRIVE:                                    // drive to enemy crater
                    if (detected == 1) {
                        forward(0.5,-15);
                        turn(new IMUTurner(80, power, _imu1, .25, null), runUsingEncoders, true);
                        forward(0.5,38);
                        turn(new IMUTurner(30, power, _imu1, .25, null), runUsingEncoders, true);
                    } else if (detected == 2) {
                        forward(0.5,-15);
                        turn(new IMUTurner(90, power, _imu1, .25, null), runUsingEncoders, true);
                        forward(0.5,19);
                        turn(new IMUTurner(30, power, _imu1, .25, null), runUsingEncoders, true);
                    } else if (detected == 3) {
                        forward(0.5,-15);
                        turn(new IMUTurner(130, power, _imu1, .25, null), runUsingEncoders, true);
                    }
                    forward(0.5,17);
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

    public void unlatchFromLander() {
        //TODO: implement descend from lander & move to starting position & heading compensation with gyro
    }

    public void extend(Boolean drop) {
        //TODO: extend arm to either claim crater / drop token
    }
}
