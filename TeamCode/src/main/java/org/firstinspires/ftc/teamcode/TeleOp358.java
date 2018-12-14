package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

@TeleOp

public class TeleOp358 extends LinearOpMode {

    DcMotor fL;
    DcMotor bL;
    DcMotor fR;
    DcMotor bR;
    DcMotor lift;
    DcMotor scoreLift;
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

        fL = hardwareMap.dcMotor.get("lF");
        bL = hardwareMap.dcMotor.get("lB");
        fR = hardwareMap.dcMotor.get("rF");
        bR = hardwareMap.dcMotor.get("rB");
        lift = hardwareMap.dcMotor.get("lift");
        scoreLift = hardwareMap.dcMotor.get("scoreLift");

        fL.setDirection(DcMotor.Direction.REVERSE);
        bL.setDirection(DcMotor.Direction.REVERSE);

        //latch.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        while (opModeIsActive()) {

            //Defining drive, strafe, and rotation power.
            double drive = gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double rotate = gamepad1.right_stick_x;

            //Defining the motor power distribution.
            double flPower = drive - strafe - rotate;
            double blPower = drive + strafe - rotate;
            double frPower = drive + strafe + rotate;
            double brPower = drive - strafe + rotate;

            double joyStick = Range.clip(max(magnitudeLeftStick(gamepad1), abs(rotate)), -1, 1);
            double POWER = -1 * joyStick * abs(joyStick);
            telemetry.addData("POWER: ", POWER);
            double maxPower = findMax(abs(flPower), abs(blPower), abs(frPower), abs(brPower)); // greatest value of all motor powers
            telemetry.addData("maxPower: ", maxPower);
            telemetry.update();

            //Sets the power for all the drive motors.
            fL.setPower((POWER * flPower / maxPower) / SCALE);
            bL.setPower((POWER * blPower / maxPower) / SCALE);
            fR.setPower((POWER * frPower / maxPower) / SCALE);
            bR.setPower((POWER * brPower / maxPower) / SCALE);

            if (gamepad1.dpad_up){
                lift.setPower(0.5);
            }
            else if (gamepad1.dpad_down){
                lift.setPower(-0.5);
            }

        }
    }
}