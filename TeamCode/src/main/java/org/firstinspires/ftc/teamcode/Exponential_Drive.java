package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp
public class Exponential_Drive extends OpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor Band;
    DcMotor LatchLeft;
    DcMotor LatchRight;

    @Override
    public void init() {
        leftMotor = hardwareMap.dcMotor.get("fM");
        rightMotor = hardwareMap.dcMotor.get("rM");
        Band = hardwareMap.dcMotor.get("rB");
        LatchLeft = hardwareMap.dcMotor.get("Ll");
        LatchRight = hardwareMap.dcMotor.get("Lr");
        //Used for 2 motor omniwheel drive//
    }
    //double POWER = -1 * Range.clip(Math.max(Range.clip(Math.sqrt(Math.pow(gamepad1.left_stick_x, 2) + Math.pow(gamepad1.left_stick_y, 2)), -1, 1),
    //Math.abs(gamepad1.right_stick_x)), -1, 1);//
    //More edit needed in order to run the motor efficiently//

    public void loop() {
        leftMotor.setPower(Range.clip((Math.pow(gamepad1.left_stick_y, 3) / Math.abs(gamepad1.left_stick_y)), -1, 1));
        rightMotor.setPower(Range.clip((Math.pow(gamepad1.right_stick_y, 3) / Math.abs(gamepad1.right_stick_y)), -1, 1));

        if (gamepad1.left_bumper) {
            Band.setPower(0.75);
        }
        if (gamepad1.right_bumper) {
            Band.setPower(-0.75);
            //this part of code is subject to change, if the joystick version is better, this code will be operated in gamepad 2//
        }
        if (gamepad1.dpad_up) {
            LatchLeft.setPower(1);
            LatchRight.setPower(1);
        }
        if (gamepad1.dpad_down) {
            LatchLeft.setPower(-1);
            LatchRight.setPower(-1);
        }
    }

    //This should run according to the function y=x^2//
}
