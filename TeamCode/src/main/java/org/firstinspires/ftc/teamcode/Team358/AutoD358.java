package org.firstinspires.ftc.teamcode.Team358;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

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
                case UNLATCH: // unlatch and orient 90 degrees

                    runMotor(latch, 1, 7000);
                    forward(power, 3);
                    strafe(power, 4);
                    forward(power, -3);
                    runMotor(latch, 1, -7000);
                    state358 = state.DETECT;
                    break;

                case DETECT: // detects position of cube

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

                case KNOCK: // knocks gold block and drives to common position

                    turn(power, -3000);
                    if (detected == 1) {
                        turn(power, 1000);
                        forward(power, 34);
                        turn(power, 60);
                        forward(power, 2000);
                        turn(power, -1000);

                    } else if (detected == 2) {
                        forward(power, 31);
                        turn(power, 2000);
                        forward(power,50);

                    } else if (detected == 3) {
                        turn(power, -1000);
                        forward(power, 34);
                        turn(power, 60);
                        forward(power, 3000);
                        turn(power, -1000);
                    }
                    state358 = state.DROP;
                    break;

                case DROP: // drive to depot & drop token

                    state358 = state.DRIVE;
                    break;

                case DRIVE: // drive to enemy crater

                    state358 = state.CRATER;
                    break;

                case CRATER: // drop arm into crater

                    state358 = state.STOP;
                    break;

                case STOP: // self explanatory

                    stopMotors();

            }
        }
    }

    public void turn (double power, int distance) {

        //Reset Encoders
        fL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set to RUN_TO_POSITION mode
        fL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //Set Target Position
        fL.setTargetPosition(distance);
        bL.setTargetPosition(distance);
        fR.setTargetPosition(-distance);
        bR.setTargetPosition(-distance);

        //Set Drive Power
        fL.setPower(power);
        bL.setPower(power);
        fR.setPower(power);
        bR.setPower(power);

        while (fL.isBusy() && bL.isBusy() && fR.isBusy() && bR.isBusy()) {
            //Wait Until Target Position is Reached
        }

        //Stop and Change Mode back to Normal
        fL.setPower(0);
        bL.setPower(0);
        fR.setPower(0);
        bR.setPower(0);
    }
}

