package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.ArrayList;

public class Encoders {


    public static void Forward(ArrayList<DcMotor> motorArray, double power, int distance) {

        ArrayList<DcMotor> motors = new ArrayList<>();

        for (DcMotor motor : motorArray) {

            //Reset Encoders
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            //Set to RUN_TO_POSITION mode
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            //Set Target Position
            motor.setTargetPosition(distance);
            //Set Drive Power
            motor.setPower(power);

            motors.add(motor);
        }

        while (allMotorStatus(motors)) {
            //Wait Until Target Position is Reached
        }

        //Stop and Change Mode back to Normal
        stopAllMotors(motors);
    }

    public static void Turn(ArrayList<DcMotor> motorArray, double power, int distance) {

        /**
         * Please enter left motors on odd positions in the motorArray and right motors on even positions.
         */

        ArrayList<DcMotor> motors = new ArrayList<>();
        int i = 1;

        for (DcMotor motor : motorArray) {

            //Reset Encoders
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            //Set to RUN_TO_POSITION mode
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            //Set Target Position
            //Negative distance to turn left; positive distance to turn right
            if ((i % 2) == 0) {
                motor.setTargetPosition(distance);
            } else {
                motor.setTargetPosition(-distance);
            }
            //Set Drive Power
            motor.setPower(power);

            motors.add(motor);

            i++;
        }

        while (allMotorStatus(motors)) {
            //Wait Until Target Position is Reached
        }

        //Stop and Change Mode back to Normal
        stopAllMotors(motors);
    }

    public static boolean allMotorStatus(ArrayList<DcMotor> motorList) {
        for (DcMotor motor : motorList) {
            if (motor.isBusy()) {
                return true;
            }
        }
        return false;
    }

    public static void stopAllMotors(ArrayList<DcMotor> motorList) {
        for (DcMotor motor : motorList) {
            motor.setPower(0);
        }
    }
}