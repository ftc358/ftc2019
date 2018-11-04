package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp
public class IntakeSystem extends OpMode{

    CRServo intakeServo;

    public void init(){

        intakeServo = hardwareMap.crservo.get("intakeServo");

    }

    public void loop() {

        if (gamepad1.a){

            intakeServo.setPower(1);

        }
        else if (gamepad1.b){

            intakeServo.setPower(-1);

        }
        else{

            intakeServo.setPower(0);

        }



    }

}
