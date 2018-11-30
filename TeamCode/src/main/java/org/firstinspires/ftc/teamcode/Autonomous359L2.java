package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class Autonomous359L2 extends LinearOpMode {

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

        /*Encoders359.Forward(leftLatch, rightLaunch, 0.5,8888);  //Launch down
        Encoders359.Turn(leftMotor,rightMotor,0.2,233);        //Turn to get rid of the hook*/

            Encoders359.Forward(leftLatch,rightLatch,1,-5000);      //Latch mechanism up
            //Move the hook
        }
    }
}
