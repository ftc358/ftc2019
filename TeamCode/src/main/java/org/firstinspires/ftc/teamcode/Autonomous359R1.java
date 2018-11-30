package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class Autonomous359R1 extends LinearOpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor leftLatch;
    DcMotor rightLatch;
    DcMotor intakeRotation;
    DcMotor drawerSlide;
    CRServo intake;

    public void runOpMode() throws InterruptedException {

        leftMotor=hardwareMap.dcMotor.get("lM");
        rightMotor=hardwareMap.dcMotor.get("rM");
        leftLatch=hardwareMap.dcMotor.get("lL");
        rightLatch=hardwareMap.dcMotor.get("rL");
        intakeRotation=hardwareMap.dcMotor.get("iR");
        drawerSlide=hardwareMap.dcMotor.get("dS");
        intake = hardwareMap.crservo.get("i");

        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightLatch.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()){

            //Move the robot down
            //Move the hook
            Encoders359.Forward(leftMotor,rightMotor,0.25,300);     //Get rid of the hook
            Encoders359.Turn(leftMotor,rightMotor,0.25,300);        //Turn toward the mineral
            Encoders359.Forward(leftMotor,rightMotor,0.25,1000);    //Move forward and knock the mineral
        }
    }
}
