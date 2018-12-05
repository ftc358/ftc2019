package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class Autonomous359L1 extends LinearOpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor leftLatch;
    DcMotor rightLatch;
    DcMotor intakeRotation;
    DcMotor drawerSlide;
    CRServo intake;

    public void runOpMode() throws InterruptedException {

        leftMotor = hardwareMap.dcMotor.get("lM");
        rightMotor = hardwareMap.dcMotor.get("rM");
        leftLatch = hardwareMap.dcMotor.get("lL");
        rightLatch = hardwareMap.dcMotor.get("rL");
        intakeRotation = hardwareMap.dcMotor.get("iR");
        drawerSlide = hardwareMap.dcMotor.get("dS");
        intake = hardwareMap.crservo.get("i");

        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightLatch.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {

            Encoders359.Forward(leftLatch, rightLatch, 1, -3000);      //Move the robot down
            //Move the hook
            Encoders359.Forward(leftMotor, rightMotor, 0.3, 5000);     //Move Forward to get rid of the hook
            Encoders359.Turn(leftMotor, rightMotor, 0.3, 888);         //Turn toward the mineral
            Encoders359.Forward(leftMotor, rightMotor, 0.3, 1000);     //Go forward and knock the mineral
        }
    }
}
