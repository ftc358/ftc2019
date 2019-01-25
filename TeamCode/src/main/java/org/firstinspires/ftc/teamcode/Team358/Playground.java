package org.firstinspires.ftc.teamcode.Team358;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class Playground extends LinearOpMode {
    DcMotor motor1;
    DcMotor motor2;

    public void runOpMode() throws InterruptedException {
        motor1 = hardwareMap.dcMotor.get("latch");
//        motor2 = hardwareMap.dcMotor.get("motor2");

        waitForStart();

        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motor1.setPower(0.5);
//        motor2.setPower(0.5);

        while (opModeIsActive()) {
            telemetry.addData("REV Encoder reading", motor1.getCurrentPosition());
//            telemetry.addData("Tetrix Encoder reading", motor2.getCurrentPosition());
            telemetry.update();
        }
    }
}
