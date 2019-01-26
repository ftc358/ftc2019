package org.firstinspires.ftc.teamcode.Team358;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class Playground extends Robot358Main {
    public void runOpMode() throws InterruptedException {

        waitForStart();

        initialize();

        while (opModeIsActive()) {
            if (gamepad1.a) {
                intake.setPower(1);
            } else  if (gamepad1.x) {
                intake.setPower(-1);
            } else if (gamepad1.y) {
                intake.setPower(0);
            }
        }
    }
}
