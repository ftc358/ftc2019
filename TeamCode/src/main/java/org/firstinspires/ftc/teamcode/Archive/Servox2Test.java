package org.firstinspires.ftc.teamcode.Archive;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import static java.lang.Math.abs;

//@TeleOp
@Disabled
public class Servox2Test extends LinearOpMode {
    CRServo intake;
    Servo box;

    public void runOpMode() throws InterruptedException {
        intake = hardwareMap.crservo.get("intake");
        box = hardwareMap.servo.get("box");

        waitForStart();

        while (opModeIsActive()) {
            box.setPosition(abs(gamepad2.right_trigger));
            if (gamepad2.left_bumper) {

                intake.setPower(1);

                while (gamepad2.left_bumper) {
                    intake.setPower(1);
                }
            } else if (gamepad2.right_bumper) {

                intake.setPower(-1);

                while (gamepad2.right_bumper) {
                    intake.setPower(-1);
                }

            }
        }
    }
}
