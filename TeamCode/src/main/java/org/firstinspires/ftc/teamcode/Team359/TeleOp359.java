package org.firstinspires.ftc.teamcode.Team359;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.RobotConfigNameable;
import com.qualcomm.robotcore.util.Range;

import java.util.Random;

@TeleOp
public class TeleOp359 extends OpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor leftLatch;
    DcMotor rightLatch;
    DcMotor slideExtend;
    DcMotor Rotation;
    CRServo Intake;
    boolean wiggle = false, boundedRotation = false;
    boolean prevX1 = false, prevX2 = false;
    int rotatingConstant = 500;
    int downPos = rotatingConstant;

    //random things for wiggling
    //int runTimeCount = 0, motorThing, slideThing;
    //int someConstant = 20;

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
        downPos = Rotation.getCurrentPosition() + rotatingConstant;
    }

    public void loop() {
        double forward;
        if (wiggle) {
            forward = gamepad1.right_stick_y;
        }
        else {
            forward = gamepad1.left_stick_y;
        }
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
                Rotation.setPower(-.5);
            } else if (gamepad2.dpad_down) {
                Rotation.setPower(.5);
            } else {
                Rotation.setPower(0);
            }
        }

        else {
            int pos = Rotation.getCurrentPosition() - downPos;
            telemetry.addData("Pos in BoundedRotation", pos);

            if (gamepad2.dpad_up){
                //Encoders359.Rotate(Rotation, slideExtend, 3, downPos);
                if (pos > - 500) {
                    if (pos > -100) {
                        Rotation.setPower(-0.7);
                    }
                    else
                    if (pos < -100) {
                        slideExtend.setPower(.4);
                    }
                    else {
                        slideExtend.setPower(0);
                    }
                }
                else {
                    Rotation.setPower(0);
                }


            } else if (gamepad2.dpad_down) {
                //Encoders359.Rotate(Rotation, slideExtend, 4, downPos);
                if (pos < 0) {
                    Rotation.setPower(0.8);
                }
                else {
                    Rotation.setPower(0);
                }
            }
        }


        if (gamepad1.x) {
            prevX1 = true;
            if (prevX1 == false) {
                wiggle = !wiggle;
                prevX1 = true;
            }
        }
        else {
            prevX1 = false;
        }

        if (gamepad2.x) {

            if (prevX2 == false) {
                boundedRotation = !boundedRotation;
                prevX2 = true;
            }
        }
        else {
            prevX2 = false;
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


        if (gamepad2.a)                                     //Intake Servo
        {
            Intake.setPower(1);
        } else if (gamepad2.b) {
            Intake.setPower(-1);
        } else {
            Intake.setPower(0);
        }

        /*
        if (gamepad1.a) {
            if (runTimeCount == 0) {
                double x = 8*Math.random();
                int y = (int) x;
                if (y > 3) { y++; }
                slideThing = y % 3-1;
                motorThing = (y - slideThing)/3-1;
            }
            if (runTimeCount < 2*Math.PI*someConstant) {
                runTimeCount += 1;
                slideExtend.setPower(slideThing*.8*Math.sin(runTimeCount/someConstant));
                leftMotor.setPower(motorThing*.8*Math.cos(runTimeCount/someConstant));
                rightMotor.setPower(-motorThing*.8*Math.cos(runTimeCount/someConstant));
            }
            else {
                runTimeCount = 0;
            }
        }*/

        telemetry.addData("position reading: ", Rotation.getCurrentPosition());
        telemetry.addData("downPos: ", downPos);
        telemetry.addData("slideExtend reading: ", slideExtend.getCurrentPosition());
        telemetry.addData("is wiggle: ", wiggle);
        telemetry.addData("is bounded rotation: ", boundedRotation);
        //telemetry.addData("runtime count: ", runTimeCount);
        telemetry.update();
        //do you get this update
    }
}
