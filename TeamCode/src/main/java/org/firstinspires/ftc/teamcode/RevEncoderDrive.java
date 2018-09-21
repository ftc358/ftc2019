package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.*;

import static java.lang.Math.*;

public class RevEncoderDrive {

    public static void RotateMotor (DcMotor motor1, DcMotor motor2, double power, int distance){

        motor1.setMode(STOP_AND_RESET_ENCODER);
        motor2.setMode(STOP_AND_RESET_ENCODER);

        motor1.setMode(RUN_TO_POSITION);
        motor2.setMode(RUN_TO_POSITION);

        motor1.setTargetPosition(distance);
        motor2.setTargetPosition(distance);

        motor1.setPower(power);
        motor1.setPower(power);

        while (motor1.isBusy() && motor2.isBusy()){

            if (motor1.getCurrentPosition() < 0.7 * distance){
                motor1.setPower(power);
            }
            else {
                motor1.setPower(power * sqrt(1 - Sq(((motor1.getCurrentPosition() / distance - 0.7) / 0.3))));
            }

            if (motor2.getCurrentPosition() < 0.7 * distance){
                motor2.setPower(power);
            }
            else {
                motor2.setPower(power * sqrt(1 - Sq(((motor2.getCurrentPosition() / distance - 0.7) / 0.3))));
            }

        }

        motor1.setPower(0);
        motor2.setPower(0);

    }

    public static double Sq (double num){

        return pow(num, 2);

    }

}

