package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

@TeleOp
public class TeleOp359 extends OpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor leftLatch;
    DcMotor rightLatch;
    DcMotor slideExtend;
    DcMotor Rotation;
    CRServo Intake;

    public void init() {
        leftMotor = hardwareMap.dcMotor.get("lM");
        rightMotor = hardwareMap.dcMotor.get("rM");
        leftLatch = hardwareMap.dcMotor.get("lL");
        rightLatch = hardwareMap.dcMotor.get("rL");
        slideExtend = hardwareMap.dcMotor.get("sE");
        Rotation = hardwareMap.dcMotor.get("rotation");
        Intake = hardwareMap.crservo.get("intake");


        rightLatch.setDirection(DcMotorSimple.Direction.REVERSE);
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void loop() {

        double forward = gamepad1.left_stick_y;
        double turning = gamepad1.right_stick_x;

        double maxpower = Range.clip(Math.sqrt((Math.pow(forward,2) + Math.pow(turning,2))/2),0,1);

        double lpower = forward + turning;
        double rpower = forward - turning;

        if (Math.abs(lpower) >= Math.abs(rpower)) {
            rpower *= maxpower / Math.abs(lpower);
            lpower = Math.signum(lpower) * maxpower;
        }
        else {
            lpower *= maxpower / Math.abs(rpower);
            rpower = Math.signum(rpower) * maxpower;
        }

        leftMotor.setPower(lpower);
        rightMotor.setPower(rpower);



        if (gamepad1.left_bumper)               //Latching Mechanism
        {
            leftLatch.setPower(.3);
            rightLatch.setPower(.3);
        } else if (gamepad1.right_bumper) {
            leftLatch.setPower(-.3);
            rightLatch.setPower(-.3);
        } else {
            leftLatch.setPower(0);
            rightLatch.setPower(0);
        }



        if (gamepad2.dpad_up)                   //Slide Rotation
        {
            Rotation.setPower(0.3);
        } else if (gamepad2.dpad_down) {
            Rotation.setPower(-0.3);
        } else {
            Rotation.setPower(0);
        }



        if (gamepad2.left_bumper)                                   //Slide Extend
        {
            slideExtend.setPower(1);
        } else if (gamepad2.right_bumper){
            slideExtend.setPower(-1);
        } else {
            slideExtend.setPower(0);
        }



        if (gamepad2.a)                                 //Intake Servo
        {
            Intake.setPower(1);
        }else if (gamepad2.b)
        {
            Intake.setPower(-1);
        }else {
            Intake.setPower(0);
        }
    }
}
