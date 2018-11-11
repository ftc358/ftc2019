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
    CRServo Intake;

    public void runOpMode() throws InterruptedException {

        leftMotor=hardwareMap.dcMotor.get("leftMotor");
        rightMotor=hardwareMap.dcMotor.get("rightMotor");
        leftLatch=hardwareMap.dcMotor.get("leftLatch");
        rightLatch=hardwareMap.dcMotor.get("rightLatch");
        intakeRotation=hardwareMap.dcMotor.get("intakeRotation");
        Intake = hardwareMap.crservo.get("Intake");

        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightLatch.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()){

        /*Encoders359.Forward(leftLatch, rightLaunch, 0.5,8888);  //Launch down
        Encoders359.Turn(leftMotor,rightMotor,0.2,233);        //Turn to get rid of the hook*/

            Encoders359.Forward(leftLatch,rightLatch,1,-5000);      //Latch mechanism up
            Encoders359.Intake(intakeRotation,0.2,-50);             //Intake down
            Encoders359.Forward(leftMotor,rightMotor,0.5,2560);     //Go forward and hit the ball
        }
    }
}
