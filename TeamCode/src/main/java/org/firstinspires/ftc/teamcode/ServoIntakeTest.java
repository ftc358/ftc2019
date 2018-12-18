package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp

public class ServoIntakeTest extends LinearOpMode {

    CRServo intake;

    public void runOpMode() throws InterruptedException {

        intake = hardwareMap.crservo.get("intake");

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad2.left_bumper){

                intake.setPower(1);

                while (gamepad2.left_bumper) {
                    intake.setPower(1);
                }
            }
            else if (gamepad2.right_bumper){

                intake.setPower(-1);

                while (gamepad2.right_bumper) {
                    intake.setPower(-1);
                }

            }
            else if (gamepad2.a){

                intake.setPower(0);

                while (gamepad2.a) {
                    intake.setPower(0);
                }
            }


        }

    }

}
