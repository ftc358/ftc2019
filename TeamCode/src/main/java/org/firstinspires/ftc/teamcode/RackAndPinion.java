package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class RackAndPinion extends OpMode{

    DcMotor leftLatch;
    DcMotor rightLatch;

    double latchPower;

    public void init() {

        leftLatch = hardwareMap.dcMotor.get("leftLatch");
        rightLatch = hardwareMap.dcMotor.get("rightLatch");

        rightLatch.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    public void loop() {

        latchPower = gamepad1.left_stick_y;

        leftLatch.setPower(latchPower);
        rightLatch.setPower(latchPower);

    }

}
