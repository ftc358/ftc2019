package org.firstinspires.ftc.teamcode.Archive;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
@TeleOp
public class RachelIntake extends OpMode {
    DcMotor motor;

    public void init() {
        motor = hardwareMap.dcMotor.get("motor");
    }

    public void loop() {
        if (gamepad1.a) {
            motor.setPower(0.3);
        } else {
            if (gamepad1.b) {
                motor.setPower(0.3);
            } else {
                motor.setPower(0);
            }
        }
    }
}