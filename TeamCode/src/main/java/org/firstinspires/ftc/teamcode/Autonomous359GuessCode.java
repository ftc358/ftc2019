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
    DcMotor Rotation;
    DcMotor slideExtend;
    DcMotor slideRetract;

    public void runOpMode() throws InterruptedException {

        leftMotor = hardwareMap.dcMotor.get("lM");
        rightMotor = hardwareMap.dcMotor.get("rM");
        leftLatch = hardwareMap.dcMotor.get("lL");
        rightLatch = hardwareMap.dcMotor.get("rL");
        Rotation = hardwareMap.dcMotor.get("rotation");
        slideExtend = hardwareMap.dcMotor.get("sE");
        slideRetract = hardwareMap.dcMotor.get("sR");

        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightLatch.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {

            Encoders359.Forward(leftLatch,rightLatch,1,8000);        //Latch down and move from the hook
            Encoders359.Forward(leftMotor,rightMotor,0.25,4000);     //Knock the ball
            Encoders359.Intake(Rotation,0.25,1500);                  //Put team marker down
            //Move the robot down
            //Move the hook
            Encoders359.Forward(leftMotor, rightMotor, 0.25, 4000);     //Knock the ball
        }
    }
}
