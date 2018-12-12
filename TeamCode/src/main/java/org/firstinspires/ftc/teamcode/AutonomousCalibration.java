package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous
public class AutonomousCalibration extends LinearOpMode {

    DcMotor lF;
    DcMotor lB;
    DcMotor rF;
    DcMotor rB;

    public static void Forward(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, double power, int distance) {

        /**
         * Distance is in inches!
         */

        int ticks = (int) (((distance / (4 * Math.PI) * 1130)) * 1.05 + 0.5);

        //Reset Encoders
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set to RUN_TO_POSITION mode
        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //Set Target Position
        motor1.setTargetPosition(-ticks);
        motor2.setTargetPosition(-ticks);
        motor3.setTargetPosition(-ticks);
        motor4.setTargetPosition(-ticks);

        //Set Drive Power
        motor1.setPower(power);
        motor2.setPower(power);
        motor3.setPower(power);
        motor4.setPower(power);

        while (motor1.isBusy() && motor2.isBusy() && motor3.isBusy() && motor4.isBusy()) {
            //Wait Until Target Position is Reached
        }

        //Stop and Change Mode back to Normal
        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);
    }

    public static void Turn(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, double power, Encoders.Direction direction, int degrees) {

        /**
         Angle in degrees!
         */

        int ticks = (int) (degrees / 180 * 3850 + 0.5);

        if (direction == Encoders.Direction.left) {
            ticks = -ticks;
        }


        //Reset Encoders
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set to RUN_TO_POSITION mode
        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //Set Target Position
        motor1.setTargetPosition(-ticks);
        motor2.setTargetPosition(-ticks);
        motor3.setTargetPosition(ticks);
        motor4.setTargetPosition(ticks);

        //Set Drive Power
        motor1.setPower(power);
        motor2.setPower(power);
        motor3.setPower(power);
        motor4.setPower(power);

        while (motor1.isBusy() && motor2.isBusy() && motor3.isBusy() && motor4.isBusy()) {
            //Wait Until Target Position is Reached
        }

        //Stop and Change Mode back to Normal
        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);
    }

    public void runOpMode() throws InterruptedException {

        lF = hardwareMap.dcMotor.get("lF");
        lB = hardwareMap.dcMotor.get("lB");
        rF = hardwareMap.dcMotor.get("rF");
        rB = hardwareMap.dcMotor.get("rB");
//        lL = hardwareMap.dcMotor.get("lL");
//        rL = hardwareMap.dcMotor.get("rL");

        rF.setDirection(DcMotor.Direction.REVERSE);
        rB.setDirection(DcMotor.Direction.REVERSE);
//        rL.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

//        Forward(lF, lB, rF, rB, 0.25, 30);
        Turn(lF, lB, rF, rB, 0.25, Encoders.Direction.left, 180);
        Turn(lF, lB, rF, rB, 0.25, Encoders.Direction.right, 90);
        Turn(lF, lB, rF, rB, 0.25, Encoders.Direction.left, 45);

    }
}