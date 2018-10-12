package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp
public class Exponential_Drive extends OpMode{

    DcMotor leftMotor;
    DcMotor rightMotor;

    @Override
    public void init() {
        leftMotor=hardwareMap.dcMotor.get("fM");
        rightMotor=hardwareMap.dcMotor.get("rM");
    }
    double POWER = -1 * Range.clip(Math.max(Range.clip(Math.sqrt(Math.pow(gamepad1.left_stick_x, 2) + Math.pow(gamepad1.left_stick_y, 2)), -1, 1),
            Math.abs(gamepad1.right_stick_x)), -1, 1);
    double maxPower = Math.max(Math.max(Math.abs(gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x),
            Math.abs(gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x)),
            Math.max(Math.abs(gamepad1.left_stick_y),
                    Math.abs(gamepad1.right_stick_y)));

    public void loop() {
        leftMotor.setPower(POWER * (gamepad1.left_stick_y)/maxPower);
        rightMotor.setPower(POWER * (gamepad1.right_stick_y)/maxPower);
    }
}
