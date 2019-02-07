package org.firstinspires.ftc.teamcode.Team359;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.sun.tools.javac.util.Position;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

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


    public static List<Recognition> ForwardWithVuforia(DcMotor motor1, DcMotor motor2, TFObjectDetector tfod, double power, int distance) {

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

        int maxSize = 0;
        List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
        List<Recognition> newRecognitions;

        while (motor1.isBusy() && motor2.isBusy()) {
            newRecognitions = tfod.getUpdatedRecognitions();
                if (newRecognitions != null && newRecognitions.size() > maxSize){
                    updatedRecognitions = newRecognitions;
                    maxSize = newRecognitions.size();
            }
        }

        //Stop and Change Mode back to Normal
        motor1.setPower(0);
        motor2.setPower(0);

        return updatedRecognitions;
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

    public static void Rotate(DcMotor rotating, DcMotor spinning, int commandType, int downPos){
        int pos = rotating.getCurrentPosition() - downPos;

        switch (commandType) {
            case 3:
                if (pos > - 500) {
                    if (pos > -100) {
                        rotating.setPower(-0.7);
                    }
                    else {
                        rotating.setPower(-0.5);
                    }
                    if (pos < -100) {
                        spinning.setPower(.4);
                    }
                    else {
                        spinning.setPower(0);
                    }
                }
                else {
                    rotating.setPower(0);
                }
                break;
            case 4:
                if (pos < 0) {
                    rotating.setPower(0.8);
                }
                else {
                    rotating.setPower(0);
                }
                break;
        }

    }
}
