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
    DcMotor intakeRotation;
    DcMotor drawerSlide;
    CRServo intake;

    public void init() {
        leftMotor = hardwareMap.dcMotor.get("lM");
        rightMotor = hardwareMap.dcMotor.get("rM");
        leftLatch = hardwareMap.dcMotor.get("lL");
        rightLatch = hardwareMap.dcMotor.get("rL");
        intakeRotation = hardwareMap.dcMotor.get("iR");
        drawerSlide = hardwareMap.dcMotor.get("dS");
        intake = hardwareMap.crservo.get("i");

        rightLatch.setDirection(DcMotorSimple.Direction.REVERSE);
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void loop() {

        leftMotor.setPower(Range.clip(((gamepad1.left_stick_y) * Math.abs(gamepad1.left_stick_y)), -1, 1));
        rightMotor.setPower(Range.clip(((gamepad1.right_stick_y) * Math.abs(gamepad1.right_stick_y)), -1, 1)); //let the chassis move according to the function y=x^2 or -x^2 depending on the value of x//

        if (gamepad2.left_bumper) {
            leftLatch.setPower(1);
            rightLatch.setPower(1);
        } else if (gamepad2.right_bumper) {
            leftLatch.setPower(-1);
            rightLatch.setPower(-1);
        } else {
            leftLatch.setPower(0);
            rightLatch.setPower(0);
        }

        if (gamepad2.a) {
            intake.setPower(1);
        } else if (gamepad2.b) {
            intake.setPower(-1);
        } else {
            intake.setPower(0);
        }

        if (gamepad2.dpad_up) {
            intakeRotation.setPower(0.5);
        } else if (gamepad2.dpad_down) {
            intakeRotation.setPower(-0.5);
        } else {
            intakeRotation.setPower(0);
        }

    }
}
