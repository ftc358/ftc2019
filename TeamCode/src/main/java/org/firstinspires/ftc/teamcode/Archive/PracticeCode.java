package org.firstinspires.ftc.teamcode.Archive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Team359.Encoders359;

@TeleOp
public class PracticeCode extends OpMode {

    //Claim your motors
    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor middleMotor;

    public void init() {
        leftMotor = hardwareMap.dcMotor.get("lM");
        rightMotor = hardwareMap.dcMotor.get("rM");
        middleMotor = hardwareMap.dcMotor.get("mM");
    }

    public void loop(){
        if(gamepad1.a){
            leftMotor.setPower(1);
        }else if(gamepad1.b){
            leftMotor.setPower(-1);
        }else{
            leftMotor.setPower(0);
        }



        if(gamepad2.dpad_up){
            rightMotor.setPower(1);
        }else if(gamepad2.dpad_down){
            rightMotor.setPower(-1);
        }else{
            rightMotor.setPower(0);
        }



        if(gamepad1.left_bumper){
            middleMotor.setPower(1);
        }else if(gamepad1.right_bumper){
            middleMotor.setPower(-1);
        }else{
            middleMotor.setPower(0);
        }
    }
}
