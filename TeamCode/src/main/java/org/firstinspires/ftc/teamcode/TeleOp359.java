package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp
public class TeleOp359 extends OpMode{

    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor leftLatch;
    DcMotor rightLatch;
    DcMotor intakeRotation;
    CRServo Intake;

    public void init() {
        leftMotor = hardwareMap.dcMotor.get("leftMotor");
        rightMotor = hardwareMap.dcMotor.get("rightMotor");
        leftLatch = hardwareMap.dcMotor.get("leftLatch");
        rightLatch = hardwareMap.dcMotor.get("rightLatch");
        intakeRotation = hardwareMap.dcMotor.get("intakeRotation");
        Intake = hardwareMap.crservo.get("Intake");

        rightLatch.setDirection(DcMotorSimple.Direction.REVERSE);
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    public void loop() {

        leftMotor.setPower(Range.clip(((gamepad1.left_stick_y) * Math.abs(gamepad1.left_stick_y)), -1, 1));
        rightMotor.setPower(Range.clip(((gamepad1.right_stick_y) * Math.abs(gamepad1.right_stick_y)), -1, 1)); //let the chassis move according to the function y=x^2 or -x^2 depending on the value of x//

        if (gamepad2.left_bumper) {
            leftLatch.setPower(1);
            rightLatch.setPower(1);
        }

        if (gamepad2.right_bumper) {
            leftLatch.setPower(-1);
            rightLatch.setPower(-1);
        }

        if (gamepad2.a) {
            Intake.setPower(0.5);
        }

        if (gamepad2.b) {
            Intake.setPower(-0.5);
        }

        if (gamepad2.dpad_up) {
            intakeRotation.setPower(1);
        }

        if (gamepad2.dpad_down) {
            intakeRotation.setPower(-1);
        }

        else{
            leftMotor.setPower(0);
            rightMotor.setPower(0);
            leftLatch.setPower(0);
            rightLatch.setPower(0);
            intakeRotation.setPower(0);
            Intake.setPower(0);
        }

    }
}
