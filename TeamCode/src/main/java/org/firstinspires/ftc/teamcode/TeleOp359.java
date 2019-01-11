package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

@TeleOp
public class TeleOp359 extends OpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor leftLatch;
    DcMotor rightLatch;
    DcMotor Rotation;
    DcMotor slideExtend;
    CRServo Intake;

    public void init() {

        leftMotor = hardwareMap.dcMotor.get("lM");
        rightMotor = hardwareMap.dcMotor.get("rM");
        leftLatch = hardwareMap.dcMotor.get("lL");
        rightLatch = hardwareMap.dcMotor.get("rL");
        Rotation = hardwareMap.dcMotor.get("rotation");
        slideExtend = hardwareMap.dcMotor.get("sE");
        Intake = hardwareMap.crservo.get("intake");


        rightLatch.setDirection(DcMotorSimple.Direction.REVERSE);
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void loop() {

        leftMotor.setPower(Range.clip(((gamepad1.left_stick_y) * Math.abs(gamepad1.left_stick_y)), -1, 1));
        rightMotor.setPower(Range.clip(((gamepad1.right_stick_y) * Math.abs(gamepad1.right_stick_y)), -1, 1)); //let the chassis move according to the function y=x^2 or -x^2 depending on the value of x//

        if (gamepad1.left_bumper)                                   //Latching Mechanism
        {
            leftLatch.setPower(1);
            rightLatch.setPower(1);
        } else if (gamepad1.right_bumper) {
            leftLatch.setPower(-1);
            rightLatch.setPower(-1);
        } else {
            leftLatch.setPower(0);
            rightLatch.setPower(0);
        }



        if (gamepad2.dpad_up)                                       //Slide Rotation

        {
            Rotation.setPower(1);
        } else if (gamepad2.dpad_down) {
            Rotation.setPower(-1);
        } else {
            Rotation.setPower(0);
        }


        if (gamepad2.left_bumper)                                   //Slide Extend
        {
            slideExtend.setPower(1);
        } else if (gamepad2.right_bumper)
        {
            slideExtend.setPower(-1);
        }

            {
            slideExtend.setPower(0);
        }


        if (gamepad2.a){                                            //Intake Servo Rotation
            Intake.setPower(1);
        }
        else if (gamepad2.b){
            Intake.setPower(-1);
        }
        else{
            Intake.setPower(0);
        }
    }
}
