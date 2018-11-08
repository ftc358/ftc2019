package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class EncoderWithOnlyTwoFrontMotors {

    public static void driveForward(DcMotor frontMotor1, DcMotor frontMotor2, DcMotor backMotor1, DcMotor backMotor2, double power, int distance) {

        frontMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontMotor1.setTargetPosition(distance);
        frontMotor2.setTargetPosition(distance);

        double runPower1 = power;
        double runPower2 = power;

        frontMotor1.setPower(runPower1);
        frontMotor2.setPower(runPower2);

        backMotor1.setPower(frontMotor1.getPower());
        backMotor2.setPower(frontMotor2.getPower());

        int diff1 = Math.abs(frontMotor1.getTargetPosition() - frontMotor1.getCurrentPosition());
        int diff2 = Math.abs(frontMotor2.getTargetPosition() - frontMotor2.getCurrentPosition());

        while (frontMotor1.isBusy() && frontMotor2.isBusy()) {

            if (diff1 > 500) {
                runPower1 = power;
            }
            else {
                runPower1 = power * Math.pow(1 - Math.pow(1 - (diff1 / 500), 2), 0.5);
            }

            if (diff2 > 500) {
                runPower2 = power;
            }
            else {
                runPower2 = power * Math.pow(1 - Math.pow(1 - (diff2 / 500), 2), 0.5);
            }

            frontMotor1.setPower(runPower1);
            frontMotor2.setPower(runPower2);

            backMotor1.setPower(frontMotor1.getPower());
            backMotor2.setPower(frontMotor2.getPower());

        }

        frontMotor1.setPower(0);
        frontMotor2.setPower(0);
        backMotor1.setPower(0);
        backMotor2.setPower(0);

    }

}