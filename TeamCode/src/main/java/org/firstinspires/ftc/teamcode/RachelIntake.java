package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class RachelIntake extends OpMode{
    DcMotor motor;
    public void init() {
        motor = hardwareMap.dcMotor.get("left_motor");
    }

    public void loop(){
        if(gamepad1.a){
            motor.setPower(0.3);
        }
        else{
            if(gamepad1.b){
                motor.setPower(0.3);
            }
            else{
                motor.setPower(0);
            }
        }
    }
}