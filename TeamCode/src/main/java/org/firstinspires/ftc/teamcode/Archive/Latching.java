package org.firstinspires.ftc.teamcode.Archive;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@TeleOp(name = "Latching")

public class Latching extends LinearOpMode {
    private static final double servoextend = 0.8;
    private static final double servoretracted = 0.2;
    //Declare servos
    Servo servo1 = null;
    Servo servo2 = null;

    public void runOpMode() throws InterruptedException {
        //Initialize Servos
        servo1 = hardwareMap.servo.get("servo1");

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.a) {
                servo1.setPosition(servoextend);
            }
            if (gamepad1.b) {
                servo1.setPosition(servoretracted);
            }
            idle();
        }
    }
}