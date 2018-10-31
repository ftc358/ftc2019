package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp

public class CeciliaIntake extends OpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;

    public void init() {
        leftMotor = hardwareMap.dcMotor.get("left_motor");
        rightMotor = hardwareMap.dcMotor.get("right_motor");

        rightMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    public void loop(){
        if(gamepad1.b){
            leftMotor.setPower(0.1);
            rightMotor.setPower(0.1);
        }
        else{
            if(gamepad1.a){
                leftMotor.setPower(-0.18);
                rightMotor.setPower(-0.18);
                leftMotor.setTargetPosition(336);
                rightMotor.setTargetPosition(336);
            }
            else{
                leftMotor.setPower(0);
                rightMotor.setPower(0);
            }
        }
    }
}