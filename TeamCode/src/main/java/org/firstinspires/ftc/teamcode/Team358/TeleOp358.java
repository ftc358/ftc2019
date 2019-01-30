package org.firstinspires.ftc.teamcode.Team358;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

@TeleOp

public class TeleOp358 extends Robot358Main {

    boolean notDefaultBoxPosition = false;
    double SCALE = 2;

    Integer baseArmPosition;

    //This function finds the magnitude of the left stick of a gamepad.
    private Double magnitudeLeftStick(Gamepad gamepad) {
        return sqrt(pow(gamepad.left_stick_x, 2) + pow(gamepad.left_stick_y, 2));
    }

    //This function finds the max value given 4 values.
    private Double findMax(Double d1, Double d2, Double d3, Double d4) {
        return max(max(d1, d2), max(d3, d4));
    }

    public void runOpMode() throws InterruptedException {

        waitForStart();

        initialize();

//        MediaPlayer mp = MediaPlayer.create(hardwareMap.appContext, R.raw.roundabout);

        while (opModeIsActive()) {

            //Drive code :) //////////////////////////////////////////////////////////////////////////////////////
            //Defining drive, strafe, and rotation power.                                                       //
            double drive = gamepad1.left_stick_y;                                                               //
            double strafe = gamepad1.left_stick_x;                                                              //
            double rotate = gamepad1.right_stick_x;                                                             //
                                                                                                                //
            //Defining the motor power distribution.                                                            //
            double flPower = drive - strafe + rotate;                                                           //
            double blPower = drive + strafe + rotate;                                                           //
            double frPower = drive + strafe - rotate;                                                           //
            double brPower = drive - strafe - rotate;                                                           //
                                                                                                                //
            double joyStick = Range.clip(max(magnitudeLeftStick(gamepad1), abs(rotate)), -1, 1);     //
            double POWER = -1 * joyStick * abs(joyStick);                                                       //
            telemetry.addData("POWER: ", POWER);                                                        //
            double maxPower = findMax(abs(flPower), abs(blPower), abs(frPower), abs(brPower));                  //
            // greatest value of all motor powers                                                               //
            telemetry.addData("maxPower: ", maxPower);                                                  //
                                                                                                                //
            //Sets the power for all the drive motors.                                                          //
            fL.setPower(-(POWER * flPower / maxPower) / SCALE);                                                 //
            bL.setPower(-(POWER * blPower / maxPower) / SCALE);                                                 //
            fR.setPower(-(POWER * frPower / maxPower) / SCALE);                                                 //
            bR.setPower(-(POWER * brPower / maxPower) / SCALE);                                                 //
            //Drive code :)///////////////////////////////////////////////////////////////////////////////////////

            if (gamepad1.left_bumper) {
                latch.setPower(-1);
            } else if (gamepad1.right_bumper) {
                latch.setPower(1);
            } else {
                latch.setPower(0);
            }

            //arm lift

            lift.setPower(-Range.clip((Math.pow(gamepad2.left_stick_y, 3) / Math.abs(gamepad2.left_stick_y)), -1, 1));

            //arm extend
            extend.setPower(-Range.clip((Math.pow(gamepad2.right_stick_y, 3) / Math.abs(gamepad2.right_stick_y)), -1, 1));

            //box
            if (gamepad2.x) {
                notDefaultBoxPosition = !notDefaultBoxPosition;
            }
            if (notDefaultBoxPosition) {
                box.setPosition(abs(gamepad2.right_trigger));
                telemetry.addData("box servo position", abs(gamepad2.right_trigger));
            } else { //make max 0.61
                box.setPosition(0.32 + (0.42 * abs(gamepad2.right_trigger)));
            }
            telemetry.update();

            //intake

            if (gamepad2.y) {
                baseArmPosition = lift.getCurrentPosition();
            }

            if (baseArmPosition != null) {
                if ((lift.getCurrentPosition() < baseArmPosition + 1500) && !gamepad2.a) {
                    intake.setPower(1);
                } else if (lift.getCurrentPosition() > baseArmPosition) {
                    intake.setPower(0);
                } else if ((lift.getCurrentPosition() < baseArmPosition + 1500) && gamepad2.a) {
                    intake.setPower(0);
                }
            }
        }
    }
}