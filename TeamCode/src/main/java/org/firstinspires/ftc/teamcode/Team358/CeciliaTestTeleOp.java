package org.firstinspires.ftc.teamcode.Team358;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp
public class CeciliaTestTeleOp extends LinearOpMode {

    DcMotor motor;
    Servo servo;
    CRServo continuousServo;

    public void runOpMode() throws InterruptedException {

        waitForStart();

        int currentPower = 0;

        while (opModeIsActive()) {

            //motor

//            motor = hardwareMap.dcMotor.get("latch");
//
//            motor.setPower(gamepad1.left_stick_y);

            //servo

//            servo = hardwareMap.servo.get("servo");
//
//            continuousServo = hardwareMap.crservo.get("continuousServo");
//
//            servo.setPosition(gamepad1.left_trigger);
//
//            if (gamepad1.left_bumper) {
//                currentPower = -1;
//            } else if (gamepad1.right_bumper) {
//                currentPower = 1;
//            } else if (gamepad1.b) {
//                currentPower = 0;
//            }
//
//            continuousServo.setPower(currentPower);

        }


    }

}
