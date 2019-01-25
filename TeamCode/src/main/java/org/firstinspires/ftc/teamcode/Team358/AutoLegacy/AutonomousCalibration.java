package org.firstinspires.ftc.teamcode.Team358.AutoLegacy;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class AutonomousCalibration extends Robot358Main {

    public void runOpMode() throws InterruptedException {

        initialize();

        latch.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        latch.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        waitForStart();

        latch.setTargetPosition(4500);

        latch.setPower(1);

        while (opModeIsActive() && latch.isBusy())
        {
            telemetry.addData("encoder-fwd", latch.getCurrentPosition() + "  busy=" + latch.isBusy());
            telemetry.update();
        }

        // set motor power to zero to turn off motors. The motors stop on their own but
        // power is still applied so we turn off the power.

        latch.setPower(0.0);
    }
}