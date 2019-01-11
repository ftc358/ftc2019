package org.firstinspires.ftc.teamcode.Team358;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class Calibration_Coef extends LinearOpMode {
    DcMotor lF;
    DcMotor lB;
    DcMotor rF;
    DcMotor rB;

    public static void Forward(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, double power, int distance, double coef) {

        /**
         * Fill the number of ticks
         */

        int ticks = (int) (((distance / (4 * Math.PI) * 1130)) * coef + 0.5);

        //Reset Encoders358
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

    public void runOpMode() throws InterruptedException {
        lF = hardwareMap.dcMotor.get("lF");
        lB = hardwareMap.dcMotor.get("lB");
        rF = hardwareMap.dcMotor.get("rF");
        rB = hardwareMap.dcMotor.get("rB");

        rF.setDirection(DcMotor.Direction.REVERSE);
        rB.setDirection(DcMotor.Direction.REVERSE);

        double coef = 1.00;

        int distance = 10;

        waitForStart();

        while (opModeIsActive()) {

            distance += gamepad1.left_stick_y * 100;
            distance += gamepad1.right_stick_y * 10;

            if (gamepad1.a) {
                coef += 1;
            }
            if (gamepad2.b) {
                coef -= 1;
            }
            if (gamepad1.x) {
                Forward(lF, lB, rF, rB, 0.25, distance, coef);
            }
            telemetry.addData("Distance:", distance);
            telemetry.addData("Coef:", coef);
            telemetry.update();
        }
    }
}