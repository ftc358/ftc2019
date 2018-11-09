package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous
public class TeleOp359 extends OpMode{

    DcMotor leftMotor;
    DcMotor rightMotor;

    double drive;
    double rotation;

    double power;

    double leftPower;
    double rightPower;

    double maxPower;

    public void init(){

        leftMotor = hardwareMap.dcMotor.get("leftMotor");
        rightMotor = hardwareMap.dcMotor.get("rightMotor");

        rightMotor.setDirection(DcMotor.Direction.REVERSE);

    }

    public void loop() {

        drive = gamepad1.left_stick_y;
        rotation = gamepad1.right_stick_x;

        power = Math.max(Math.abs(drive), Math.abs(rotation));

        leftPower = drive + rotation;
        rightPower = drive - rotation;

        maxPower = Math.max(Math.abs(leftPower), Math.abs(rightPower));

        leftMotor.setPower(leftPower * power / maxPower);
        rightMotor.setPower(rightPower * power / maxPower);

    }

}
