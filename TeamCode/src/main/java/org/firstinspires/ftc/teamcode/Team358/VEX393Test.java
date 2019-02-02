package org.firstinspires.ftc.teamcode.Team358;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class VEX393Test extends LinearOpMode {

    CRServo vex393;
    CRServo theOther;

    @Override
    public void runOpMode() throws InterruptedException {

        vex393 = hardwareMap.crservo.get("vex393");
        theOther = hardwareMap.crservo.get("other");

        waitForStart();

        while (opModeIsActive()) {
            vex393.setPower(gamepad1.left_stick_y);
            theOther.setPower(gamepad1.right_stick_y);
            telemetry.addData("Power", gamepad1.left_stick_y);
            telemetry.update();
        }
    }
}
