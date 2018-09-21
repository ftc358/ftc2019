package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Encoders {

    public static void Forward (DcMotor leftMotor, DcMotor rightMotor, double power, int distance) {
//test
        //Reset Encoders
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set to RUN_TO_POSITION mode
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //Set Target Position
        leftMotor.setTargetPosition(distance);
        rightMotor.setTargetPosition(distance);

        //Set Drive Power
        leftMotor.setPower(power);
        rightMotor.setPower(power);

        while (leftMotor.isBusy() && rightMotor.isBusy())
        {
            //Wait Until Target Position is Reached
        }

        //Stop and Change Mode back to Normal
        leftMotor.setPower(0);
        rightMotor.setPower(0);

    }

    //negative distance to turn left; positive distance to turn right

    public static void Turn (DcMotor leftMotor, DcMotor rightMotor, double power, int distance) {

        //Reset Encoders
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set to RUN_TO_POSITION mode
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //Set Target Position
        leftMotor.setTargetPosition(distance);
        rightMotor.setTargetPosition(-1*distance);

        //Set Drive Power
        leftMotor.setPower(power);
        rightMotor.setPower(power);

        while (leftMotor.isBusy() && rightMotor.isBusy())
        {
            //Wait Until Target Position is Reached
        }

        //Stop and Change Mode back to Normal
        leftMotor.setPower(0);
        rightMotor.setPower(0);

    }

}