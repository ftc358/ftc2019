package org.firstinspires.ftc.teamcode.Team358.PAA;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

public abstract class Robot358Main extends LinearOpMode {
    protected DcMotor fL;
    protected DcMotor bL;
    protected DcMotor fR;
    protected DcMotor bR;
    protected DcMotor lift;
    protected DcMotor latch;
    protected DcMotor extend;

    protected CRServo intake;
    protected Servo box;

    protected BNO055IMU _imu1;

    VuforiaLocalizer _vuforia;

    Orientation angles;
    float pitchAngle;
    float rollAngle;

    public void initialize() throws InterruptedException {
        fL = hardwareMap.dcMotor.get("lF");
        fL.setDirection(DcMotor.Direction.REVERSE);
        fL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bL = hardwareMap.dcMotor.get("lB");
        bL.setDirection(DcMotor.Direction.REVERSE);
        bL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fR = hardwareMap.dcMotor.get("rF");
        fR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bR = hardwareMap.dcMotor.get("rB");
        bR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift = hardwareMap.dcMotor.get("lift");
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        latch = hardwareMap.dcMotor.get("latch");
        latch.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extend = hardwareMap.dcMotor.get("extend");
        extend.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake = hardwareMap.crservo.get("intake");
        box = hardwareMap.servo.get("box");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";

        _imu1 = hardwareMap.get(BNO055IMU.class, "imu");
        _imu1.initialize(parameters);
    }

    public double getCurrentHeading() {
        Orientation angles = _imu1.getAngularOrientation().toAxesReference(AxesReference.INTRINSIC).toAxesOrder(AxesOrder.ZYX).toAngleUnit(AngleUnit.DEGREES);
        return angles.firstAngle;
    }


    public void resetAllDriveMotorEncoders() throws InterruptedException {
        RobotControl.resetMotorEncoder(fL, this);
        RobotControl.resetMotorEncoder(bL, this);
        RobotControl.resetMotorEncoder(fR, this);
        RobotControl.resetMotorEncoder(bR, this);
    }

    public void runUsingEncoders() throws InterruptedException {
        fL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void runWithoutEncoders() throws InterruptedException {
        fL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void stopMotors() {
        fL.setPower(0);
        bL.setPower(0);
        fR.setPower(0);
        bR.setPower(0);
    }

    public void resetNonDriveMotorEncoders() throws InterruptedException {
        RobotControl.resetMotorEncoder(lift, this);
        RobotControl.resetMotorEncoder(latch, this);
        RobotControl.resetMotorEncoder(extend, this);
    }

    public void runNonDriveUsingEncoders() throws InterruptedException {
        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        latch.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        extend.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void runNonDriveWithoutEncoders() throws InterruptedException {
        lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        latch.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        extend.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void turn(TurnerIF turner, boolean runUsingEncoders, boolean stopMotors) throws InterruptedException {
        turner.start();

        if (runUsingEncoders) {
            runUsingEncoders();
        } else {
            runWithoutEncoders();
        }

        boolean keepGoing = true;

        double lastScaleFactor = 0;
        double lastPower = 0;

        while (keepGoing) {
            int positionA = fL.getCurrentPosition();
            int positionB = fR.getCurrentPosition();
            int positionC = bL.getCurrentPosition();
            int positionD = bR.getCurrentPosition();

            double power = turner.getPower();
            double scaleFactor = turner.getScaleFactor();

            RobotLog.d("Robot358Main::turn()::power: " + power);
            RobotLog.d("Robot358Main::turn()::scaleFactor: " + scaleFactor);

            keepGoing = turner.keepGoing(0);

            if (keepGoing && (power != lastPower || scaleFactor != lastScaleFactor)) {
                if (Double.isNaN(scaleFactor)) {
                    keepGoing = false;
                } else {
                    RobotLog.d("Robot358Main::turn()::power * scaleFactor: " + power * scaleFactor);

                    fL.setPower(-power * scaleFactor);
                    bL.setPower(-power * scaleFactor);
                    fR.setPower(power * scaleFactor);
                    bR.setPower(power * scaleFactor);
                }

                lastPower = power;
                lastScaleFactor = scaleFactor;
            }

            Thread.sleep(1);

        }

        if (stopMotors) {
            stopMotors();
        }
    }

    public void forward(double power, int distance) {

        /**
         * Distance is in inches!
         */

        int ticks = (int) (((distance / (4 * Math.PI) * 1130)) * 1.41 + 0.5);

        //Reset Encoders358
        fL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set to RUN_TO_POSITION mode
        fL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //Set Target Position
        fL.setTargetPosition(-ticks);
        bL.setTargetPosition(-ticks);
        fR.setTargetPosition(-ticks);
        bR.setTargetPosition(-ticks);

        //Set Drive Power
        fL.setPower(power);
        bL.setPower(power);
        fR.setPower(power);
        bR.setPower(power);

        while (fL.isBusy() && fR.isBusy() && bL.isBusy() && bR.isBusy()) {
            //Wait Until Target Position is Reached
        }

        //Stop and Change Mode back to Normal
        fL.setPower(0);
        bL.setPower(0);
        fR.setPower(0);
        bR.setPower(0);
    }
}
