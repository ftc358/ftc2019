package org.firstinspires.ftc.teamcode.Team358;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class Playground extends LinearOpMode {
    DcMotor motor1;
    DcMotor motor2;

    public void runOpMode() throws InterruptedException {
        motor1 = hardwareMap.dcMotor.get("lF");
//        motor2 = hardwareMap.dcMotor.get("motor2");

        waitForStart();

        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        motor1.setTargetPosition(7000);

        motor1.setPower(1.0);

        while (opModeIsActive() && motor1.isBusy())
        {
//            telemetry.addData("encoder-fwd", motor1.getCurrentPosition() + "  busy=" + motor1.isBusy());
//            telemetry.update();
        }

        motor1.setPower(0.0);
    }
}
