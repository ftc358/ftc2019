package org.firstinspires.ftc.teamcode.Team358;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Team358.AutoLegacy.Robot358Main;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

@TeleOp

public class TeleOp358 extends Robot358Main {

    boolean notDefaultBoxPosition = false;
    double SCALE = 2;

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

        while (opModeIsActive()) {

            //Drive code :) //////////////////////////////////////////////////////////////////////////////////////
            //Defining drive, strafe, and rotation power.                                                       //
            double drive = gamepad1.left_stick_y;                                                               //
            double strafe = gamepad1.left_stick_x;                                                              //
            double rotate = gamepad1.right_stick_x;                                                             //
            //
            //Defining the motor power distribution.                                                            //
            double flPower = drive - strafe - rotate;                                                           //
            double blPower = drive + strafe - rotate;                                                           //
            double frPower = drive + strafe + rotate;                                                           //
            double brPower = drive - strafe + rotate;                                                           //
            //
            double joyStick = Range.clip(max(magnitudeLeftStick(gamepad1), abs(rotate)), -1, 1);     //
            double POWER = -1 * joyStick * abs(joyStick);                                                       //
            telemetry.addData("POWER: ", POWER);                                                        //
            double maxPower = findMax(abs(flPower), abs(blPower), abs(frPower), abs(brPower));                  //
            // greatest value of all motor powers                                                               //
            telemetry.addData("maxPower: ", maxPower);                                                  //
            telemetry.update();                                                                                 //
            //
            //Sets the power for all the drive motors.                                                          //
            fL.setPower(-(POWER * flPower / maxPower) / SCALE);                                                  //
            bL.setPower(-(POWER * blPower / maxPower) / SCALE);                                                  //
            fR.setPower(-(POWER * frPower / maxPower) / SCALE);                                                  //
            bR.setPower(-(POWER * brPower / maxPower) / SCALE);                                                  //
            //Drive code /////////////////////////////////////////////////////////////////////////////////////////

            if (gamepad1.left_bumper) {
                latch.setPower(1);
            } else if (gamepad1.right_bumper) {
                latch.setPower(-1);
            } else {
                latch.setPower(0);
            }

            lift.setPower(gamepad2.left_stick_y);

            //Arm
            extend.setPower(-gamepad2.right_stick_y);

            //Fingers
            if (gamepad2.left_bumper) {
                intake.setPower(1);
                while (gamepad2.left_bumper) {
                    telemetry.addData("intakePower", intake.getPower());
                    telemetry.update();
                }
            } else if (gamepad2.right_bumper) {
                intake.setPower(-1);
                while (gamepad2.right_bumper) {
                    telemetry.addData("intakePower", intake.getPower());
                    telemetry.update();
                }
            } else if (gamepad2.a) {
                intake.setPower(0);
                while (gamepad2.a) {
                    telemetry.addData("intakePower", intake.getPower());
                    telemetry.update();
                }
            }
            telemetry.addData("intakePower", intake.getPower());
            telemetry.update();

            //Wrist
            if (gamepad2.x) {
                notDefaultBoxPosition = !notDefaultBoxPosition;
                while (gamepad2.x) {
                    telemetry.addData("notDefaultBoxPosition", notDefaultBoxPosition);
                    telemetry.update();
                }
            }
            if (notDefaultBoxPosition) {
                box.setPosition(abs(gamepad2.right_trigger));
                telemetry.addData("box servo position", abs(gamepad2.right_trigger));
            } else {
                box.setPosition(.2 + 0.6 * abs(gamepad2.right_trigger));
            }

        }
    }
}