package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class EncoderWithOnlyTwoMotors {

    public static void Forward(DcMotor motor1, DcMotor motor2, double power, int distance) {

        /**
         Distance is in inches!
         */

        int ticks = (int) (((distance / (4 * Math.PI) * 720)) * 1.07 + 0.5);

        //Reset Encoders
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        //Set to RUN_TO_POSITION mode
        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        //Set Target Position
        motor1.setTargetPosition(-ticks);
        motor2.setTargetPosition(-ticks);


        //Set Drive Power
        motor1.setPower(power);
        motor2.setPower(power);


        while (motor1.isBusy() && motor2.isBusy()) {
            //Wait Until Target Position is Reached
        }

        //Stop and Change Mode back to Normal
        motor1.setPower(0);
        motor2.setPower(0);
    }

    public static void Turn(DcMotor motor1, DcMotor motor2, double power, Direction direction, double degrees) {

        /**
         Angle in degrees!
         */

        int ticks = (int) (degrees / 180 * 2000 + 0.5);

        if (direction == Direction.left) {
            ticks = -ticks;
        }


        //Reset Encoders
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set to RUN_TO_POSITION mode
        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //Set Target Position
        motor1.setTargetPosition(-ticks);
        motor2.setTargetPosition(-ticks);

        //Set Drive Power
        motor1.setPower(power);
        motor2.setPower(power);

        while (motor1.isBusy() && motor2.isBusy()) {
            //Wait Until Target Position is Reached
        }

        //Stop and Change Mode back to Normal
        motor1.setPower(0);
        motor2.setPower(0);

    }

    enum Direction {left, right}
}