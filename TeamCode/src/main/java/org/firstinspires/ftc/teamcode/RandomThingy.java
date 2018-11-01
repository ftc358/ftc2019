package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class RandomThingy extends OpMode{

    DcMotor lF;
    DcMotor lB;
    DcMotor rF;
    DcMotor rB;
    boolean ExpState = false;

    public void init(){

        lF = hardwareMap.dcMotor.get("lF");
        lB = hardwareMap.dcMotor.get("lB");
        rF = hardwareMap.dcMotor.get("rF");
        rB = hardwareMap.dcMotor.get("rB");

        rF.setDirection(DcMotor.Direction.REVERSE);
        rB.setDirection(DcMotor.Direction.REVERSE);

    }

    public void loop(){

        /*

        lF.setPower(gamepad1.left_stick_y);
        lB.setPower(gamepad1.left_stick_y);
        rF.setPower(gamepad1.right_stick_y);
        rB.setPower(gamepad1.right_stick_y);


        // right trigger for precise movement
        if (gamepad1.right_trigger>0.1f){

            lF.setPower(((Math.abs(gamepad1.left_stick_y)*gamepad1.left_stick_y)/3)/2);
            lB.setPower(((Math.abs(gamepad1.left_stick_y)*gamepad1.left_stick_y)/3)/2);
            rF.setPower(((Math.abs(gamepad1.right_stick_y)*gamepad1.right_stick_y)/3)/2);
            rB.setPower(((Math.abs(gamepad1.right_stick_y)*gamepad1.right_stick_y)/3)/2);

        }

        */

        if (gamepad1.right_bumper){
            ExpState = !ExpState;
            while (gamepad1.right_bumper){
                if (ExpState){
                    lF.setPower(Math.abs(gamepad1.left_stick_y)*gamepad1.left_stick_y/10);
                    lB.setPower(Math.abs(gamepad1.left_stick_y)*gamepad1.left_stick_y/10);
                    rF.setPower(Math.abs(gamepad1.right_stick_y)*gamepad1.right_stick_y/10);
                    rB.setPower(Math.abs(gamepad1.right_stick_y)*gamepad1.right_stick_y/10);
                }
                else {
                    lF.setPower(gamepad1.left_stick_y);
                    lB.setPower(gamepad1.left_stick_y);
                    rF.setPower(gamepad1.right_stick_y);
                    rB.setPower(gamepad1.right_stick_y);
                }
            }
        }

        if (ExpState){
            lF.setPower(Math.abs(gamepad1.left_stick_y)*gamepad1.left_stick_y/10);
            lB.setPower(Math.abs(gamepad1.left_stick_y)*gamepad1.left_stick_y/10);
            rF.setPower(Math.abs(gamepad1.right_stick_y)*gamepad1.right_stick_y/10);
            rB.setPower(Math.abs(gamepad1.right_stick_y)*gamepad1.right_stick_y/10);
        }
        else {
            lF.setPower(gamepad1.left_stick_y);
            lB.setPower(gamepad1.left_stick_y);
            rF.setPower(gamepad1.right_stick_y);
            rB.setPower(gamepad1.right_stick_y);
        }
    }
}
