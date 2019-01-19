package org.firstinspires.ftc.teamcode.Team358;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

//@TeleOp
@Autonomous
public class SingleMotorTest extends LinearOpMode {
    DcMotor motor;

    public void runOpMode() throws InterruptedException {
        motor = hardwareMap.dcMotor.get("motor");

        waitForStart();

        while (opModeIsActive()) {
//            motor.setPower(gamepad1.left_stick_y);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor.setTargetPosition(20);

            motor.setPower(1);

            while (motor.isBusy()) {
                //Wait Until Target Position is Reached
            }

            //Stop and Change Mode back to Normal
            motor.setPower(0);
        }
    }
}
