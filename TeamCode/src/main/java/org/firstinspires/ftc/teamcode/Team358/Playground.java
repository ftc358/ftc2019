package org.firstinspires.ftc.teamcode.Team358;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class Playground extends AutoEngine358 {
    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        while (opModeIsActive()) {
            telemetry.addData("Abs heading", getAbsoluteCurrentHeading());
            telemetry.addData("Raw heading", getCurrentHeading());
            telemetry.update();
        }
    }
}
