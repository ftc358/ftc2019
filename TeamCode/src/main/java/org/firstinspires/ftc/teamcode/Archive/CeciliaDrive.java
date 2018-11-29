package org.firstinspires.ftc.teamcode.Archive;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
@TeleOp

public class CeciliaDrive extends OpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;

    public void init() {
        leftMotor = hardwareMap.dcMotor.get("left_motor");
        rightMotor = hardwareMap.dcMotor.get("right_motor");

        rightMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    public void loop() {
        leftMotor.setPower(gamepad1.left_stick_y);
        rightMotor.setPower(gamepad1.right_stick_y);
        //for precise movement
        if (gamepad1.right_trigger > 0.1f) {
            leftMotor.setPower(((Math.abs(gamepad1.left_stick_y) * gamepad1.left_stick_y) / 3) / 2);
            rightMotor.setPower(((Math.abs(gamepad1.right_stick_y) * gamepad1.right_stick_y) / 3) / 2);
        }
    }
}
