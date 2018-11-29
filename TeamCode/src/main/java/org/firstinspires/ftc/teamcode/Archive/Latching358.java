package org.firstinspires.ftc.teamcode.Archive;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class Latching358 extends LinearOpMode {
    Servo servo;

    public void runOpMode() throws InterruptedException{
        servo = hardwareMap.servo.get("servo");

        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.a) {
                servo.setPosition(0.5);
            } else if(gamepad1.b){
                servo.setPosition(-0.5);
            }else{
                servo.setPosition(0);
            }
        }
    }
}