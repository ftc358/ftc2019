package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class TeleOp358 extends OpMode {

    DcMotor lF;
    DcMotor lB;
    DcMotor rF;
    DcMotor rB;
    //DcMotor lL;         // left lift
    DcMotor rL;         // right lift
    boolean ExpState = false;

    public void init() {

        lF = hardwareMap.dcMotor.get("lF");
        lB = hardwareMap.dcMotor.get("lB");
        rF = hardwareMap.dcMotor.get("rF");
        rB = hardwareMap.dcMotor.get("rB");
        //lL = hardwareMap.dcMotor.get("lL");
        rL = hardwareMap.dcMotor.get("rL");

        rF.setDirection(DcMotor.Direction.REVERSE);
        rB.setDirection(DcMotor.Direction.REVERSE);
        //lL.setDirection(DcMotor.Direction.REVERSE);
        rL.setDirection(DcMotor.Direction.REVERSE);

    }

    public void loop() {

        telemetry.addData("Exponential Drive State: ", ExpState);
        telemetry.update();

        if (gamepad1.right_bumper) {
            ExpState = !ExpState;
            //TODO: bug, fix!
            while (gamepad1.right_bumper) {
                if (ExpState) {
                    lF.setPower(Math.pow(gamepad1.left_stick_y, 5));
                    lB.setPower(Math.pow(gamepad1.left_stick_y, 5));
                    rF.setPower(Math.pow(gamepad1.right_stick_y, 5));
                    rB.setPower(Math.pow(gamepad1.right_stick_y, 5));
                } else {
                    lF.setPower(gamepad1.left_stick_y);
                    lB.setPower(gamepad1.left_stick_y);
                    rF.setPower(gamepad1.right_stick_y);
                    rB.setPower(gamepad1.right_stick_y);
                }
            }
        }

        if (ExpState) {
            lF.setPower(Math.pow(gamepad1.left_stick_y, 5));
            lB.setPower(Math.pow(gamepad1.left_stick_y, 5));
            rF.setPower(Math.pow(gamepad1.right_stick_y, 5));
            rB.setPower(Math.pow(gamepad1.right_stick_y, 5));
        } else {
            lF.setPower(gamepad1.left_stick_y);
            lB.setPower(gamepad1.left_stick_y);
            rF.setPower(gamepad1.right_stick_y);
            rB.setPower(gamepad1.right_stick_y);
        }

        if (gamepad1.y) {
            if (gamepad1.left_bumper) {
                //goes down
                rL.setPower(0.5);
                //lL.setPower(0.25);
                //Encoders359.Forward(lL, rL, 0.4, -60);
            }
            else if (gamepad1.right_bumper) {
                //goes up
                rL.setPower(-0.5);
                //lL.setPower(-0.25);
                //Encoders359.Forward(lL, rL, 0.4, 60);
            }
        }
        else {
            if (gamepad1.left_bumper) {
                //rL.setPower(0.25);
                //lL.setPower(0.25);
                //Encoders359.Forward(lL, rL, 0.4,-240);
            }
            else if (gamepad1.right_bumper) {
                //rL.setPower(-0.25);
                //lL.setPower(-0.25);
                //Encoders359.Forward(lL, rL, 0.4,240);
            }
            else {
                rL.setPower(0);
                //lL.setPower(0);
            }
        }
    }
}