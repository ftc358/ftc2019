package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;



@TeleOp(name="Intake")
public class Intake extends LinearOpMode
{
    //Declare servos
    private Servo servo1=null;
    private Servo servo2=null;

    private static final double servoExtend=0.8;
    private static final double servoRetract=0.2;

    public void runOpMode() throws InterruptedException {

        //Initialize Servos
        servo1=hardwareMap.servo.get("servo1");
        servo2=hardwareMap.servo.get("servo2");

        waitForStart();

        while(opModeIsActive())
        {
            if (gamepad1.a)
            {
                servo1.setPosition(servoExtend);
                servo2.setPosition(servoExtend);
            }

            if (gamepad1.b)
            {
                servo1.setPosition(servoRetract);
                servo2.setPosition(servoRetract);
            }

            idle();
        }
    }
}
