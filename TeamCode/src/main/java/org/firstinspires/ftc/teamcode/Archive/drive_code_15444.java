package org.firstinspires.ftc.teamcode.Archive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class drive_code_15444 extends OpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor intake;

    @Override
    public void init() {

        leftMotor = hardwareMap.dcMotor.get("mL");
        rightMotor = hardwareMap.dcMotor.get("mR");
        intake = hardwareMap.dcMotor.get("iT");

        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void loop() {

        leftMotor.setPower(gamepad1.left_stick_y);
        rightMotor.setPower(gamepad1.right_stick_y);

        if (gamepad2.left_bumper) {
            intake.setPower(0.75);
        }
        if (gamepad2.right_bumper) {
            intake.setPower(-0.75);
        } else {
            leftMotor.setPower(0);
            rightMotor.setPower(0);
            intake.setPower(0);
        }
    }
}