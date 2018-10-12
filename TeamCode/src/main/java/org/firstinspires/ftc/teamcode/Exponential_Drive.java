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
        //Used for 2 motor drive//
    }
    double POWER = -1 * Range.clip(Math.max(Range.clip(Math.sqrt(Math.pow(gamepad1.left_stick_x, 2) + Math.pow(gamepad1.left_stick_y, 2)), -1, 1),
            Math.abs(gamepad1.right_stick_x)), -1, 1);
    double maxPower = Math.max(Math.max(Math.abs(gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x),
            Math.abs(gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x)),
            Math.max(Math.abs(gamepad1.left_stick_y),
                    Math.abs(gamepad1.right_stick_y)));
    //More edit needed in order to run the motor efficiently//

    public void loop() {
        leftMotor.setPower(POWER * (gamepad1.left_stick_y)/maxPower);
        rightMotor.setPower(POWER * (gamepad1.right_stick_y)/maxPower);
    }
    //This should run according to the function y=x^2//
}
