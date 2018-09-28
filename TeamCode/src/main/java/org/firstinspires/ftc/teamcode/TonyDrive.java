package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class TonyDrive extends OpMode{

    //test

    DcMotor leftFrontMotor;
    DcMotor rightFrontMotor;
    DcMotor leftBackMotor;
    DcMotor rightBackMotor;

    public void init(){
        leftFrontMotor=hardwareMap.dcMotor.get("lf");
        leftBackMotor=hardwareMap.dcMotor.get("lb");
        rightFrontMotor=hardwareMap.dcMotor.get("rf");
        rightBackMotor=hardwareMap.dcMotor.get("rb");
    }

    public void loop(){
        leftFrontMotor.setPower(gamepad1.left_stick_y);
        leftBackMotor.setPower(gamepad1.left_stick_y);
        rightFrontMotor.setPower(-gamepad1.right_stick_y);
        rightBackMotor.setPower(-gamepad1.right_stick_y);
    }

}
