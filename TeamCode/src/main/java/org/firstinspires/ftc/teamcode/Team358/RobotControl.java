package org.firstinspires.ftc.teamcode.Team358;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class RobotControl {

    public static void resetMotorEncoder(DcMotor dcMotor, LinearOpMode opMode) throws InterruptedException {

        int currentPosition = Integer.MAX_VALUE;

        do {
            dcMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            currentPosition = dcMotor.getCurrentPosition();

            opMode.idle();
        } while (currentPosition != 0);
    }

    public static void resetAllDriveMotorEncoders(DcMotor motorA, DcMotor motorB, DcMotor motorC, DcMotor motorD, LinearOpMode opMode) throws InterruptedException {
        resetMotorEncoder(motorA, opMode);
        resetMotorEncoder(motorB, opMode);
        resetMotorEncoder(motorC, opMode);
        resetMotorEncoder(motorD, opMode);
    }

    public static void runUsingEncoders(DcMotor motorA, DcMotor motorB, DcMotor motorC, DcMotor motorD) throws InterruptedException {
        motorA.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorC.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorD.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public static void stopMotors(DcMotor motorA, DcMotor motorB, DcMotor motorC, DcMotor motorD) {
        motorA.setPower(0);
        motorB.setPower(0);
        motorC.setPower(0);
        motorD.setPower(0);
    }
}
