package org.firstinspires.ftc.teamcode.Team359;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Encoders359 {

    public static void Forward(DcMotor motor1, DcMotor motor2, double power, int distance) {

        //Reset Encoders\\
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set to RUN_TO_POSITION mode
        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //Set Target Position
        motor1.setTargetPosition(-distance);
        motor2.setTargetPosition(-distance);

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

    public static void Turn(DcMotor motor1, DcMotor motor2, double power, int distance) {

        //Reset Encoders
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set to RUN_TO_POSITION mode
        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //Set Target Position
        motor1.setTargetPosition(distance);
        motor2.setTargetPosition(-distance);

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

    public static void Slide(DcMotor motor1, double power, int distance) {

        //Reset Encoders
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set to RUN_TO_POSITION mode
        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        //Set Target Position
        motor1.setTargetPosition(distance);

        //Set Drive Power
        motor1.setPower(power);

        while (motor1.isBusy()) {
            //Wait Until Target Position is Reached
        }

        //Stop and Change Mode back to Normal
        motor1.setPower(0);
    }

    public static void Intake(DcMotor motor1, double power, int distance) {

        //Reset Encoders
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set to RUN_TO_POSITION mode
        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //Set Target Position
        motor1.setTargetPosition(-distance);

        //Set Drive Power
        motor1.setPower(power);

        while (motor1.isBusy()) {
            //Wait Until Target Position is Reached
        }

        //Stop and Change Mode back to Normal
        motor1.setPower(0);
    }
}
