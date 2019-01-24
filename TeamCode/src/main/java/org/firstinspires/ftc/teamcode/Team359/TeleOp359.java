package org.firstinspires.ftc.teamcode.Team359;

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
    DcMotor slideExtend;
    DcMotor Rotation;
    CRServo Intake;
    boolean boundedRotation = false;
    int downPos = 1500;

    public void init() {
        leftMotor = hardwareMap.dcMotor.get("lM");
        rightMotor = hardwareMap.dcMotor.get("rM");
        leftLatch = hardwareMap.dcMotor.get("lL");
        rightLatch = hardwareMap.dcMotor.get("rL");
        slideExtend = hardwareMap.dcMotor.get("sE");
        Rotation = hardwareMap.dcMotor.get("rotation");
        Intake = hardwareMap.crservo.get("intake");


        rightLatch.setDirection(DcMotorSimple.Direction.REVERSE);
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        Rotation.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        downPos = Rotation.getCurrentPosition() + 1500;
    }

    public void loop() {

        double forward = gamepad1.left_stick_y;
        double turning = gamepad1.right_stick_x;

        double maxpower = Range.clip(Math.sqrt((Math.pow(forward, 2) + Math.pow(turning, 2)) / 2), 0, 1);

        double lpower = forward - turning;
        double rpower = forward + turning;

        if (Math.abs(lpower) >= Math.abs(rpower)) {
            rpower *= maxpower / Math.abs(lpower);
            lpower = Math.signum(lpower) * maxpower;
        } else {
            lpower *= maxpower / Math.abs(rpower);
            rpower = Math.signum(rpower) * maxpower;
        }

        leftMotor.setPower(lpower);
        rightMotor.setPower(rpower);


        if (gamepad1.left_bumper)               //Latching Mechanism
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

        if (!boundedRotation){
            if (gamepad2.dpad_up)                   //Slide Rotation
            {
                Rotation.setPower(-1);
            } else if (gamepad2.dpad_down) {
                Rotation.setPower(1);
            } else {
                Rotation.setPower(0);
            }
        }
        else {
            if (gamepad2.dpad_up){
                Encoders359.Rotate(Rotation, slideExtend, 3, downPos);
            } else if (gamepad2.dpad_down) {
                Encoders359.Rotate(Rotation, slideExtend, 4, downPos);
            }
        }


        if (gamepad2.x) {
            boundedRotation = !boundedRotation;
        }
        if (gamepad2.y) {
            downPos = Rotation.getCurrentPosition();
        }


        if (gamepad2.left_bumper)                                   //Slide Extend
        {
            slideExtend.setPower(.8);
        } else if (gamepad2.right_bumper) {
            slideExtend.setPower(-.8);
        } else {
            slideExtend.setPower(0);
        }


        if (gamepad2.a)                                 //Intake Servo
        {
            Intake.setPower(1);
        } else if (gamepad2.b) {
            Intake.setPower(-1);
        } else {
            Intake.setPower(0);
        }

        telemetry.addData("position reading: ", Rotation.getCurrentPosition());
        telemetry.addData("slideExtend reading: ", slideExtend.getCurrentPosition());
        telemetry.addData("is new type of rotation: ", boundedRotation);
        telemetry.update();

    }
}
