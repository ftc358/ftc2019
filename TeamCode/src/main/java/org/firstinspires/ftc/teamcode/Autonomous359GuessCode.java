package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class Autonomous359GuessCode extends LinearOpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor leftLatch;
    DcMotor rightLatch;

    public void runOpMode() throws InterruptedException {

        leftMotor=hardwareMap.dcMotor.get("lM");
        rightMotor=hardwareMap.dcMotor.get("rM");
        leftLatch=hardwareMap.dcMotor.get("lL");
        rightLatch=hardwareMap.dcMotor.get("rL");

        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightLatch.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()){

            //Move the robot down
            //Move the hook
            Encoders359.Forward(leftMotor,rightMotor,0.25,4000);     //Knock the ball
        }
    }
}
