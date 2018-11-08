package org.firstinspires.ftc.teamcode;

import android.util.SparseBooleanArray;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous

public class CeciliaIntake extends LinearOpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;

    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.dcMotor.get("left_motor");
        rightMotor = hardwareMap.dcMotor.get("right_motor");

        rightMotor.setDirection(DcMotor.Direction.REVERSE);

        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while(gamepad1.dpad_up){
            EncoderWithOnlyTwoFrontMotors.Forward(leftMotor,rightMotor,0.1,336);
        }
        while(gamepad1.dpad_down){
                EncoderWithOnlyTwoFrontMotors.Forward(leftMotor,rightMotor,0.18,-336);
        }
    }
}