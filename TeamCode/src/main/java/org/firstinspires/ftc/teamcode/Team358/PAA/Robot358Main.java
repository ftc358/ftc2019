package org.firstinspires.ftc.teamcode.Team358.PAA;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;

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

    private static final String VUFORIA_KEY = "AXzW9CD/////AAAAGTPAtr9HRUXZmowtd9p0AUwuXiBVONS/c5x1q8OvjMrQ8/XJGxEp0TP9Kl8PvqSzeXOWIvVa3AeB6MyAQboyW/Pgd/c4a4U/VBs1ouUsVBkEdbaq1iY7RR0cjYr3eLwEt6tmI37Ugbwrd5gmxYvOBQkGqzpbg2U2bVLycc5PkOixu7PqPqaINGZYSlvUzEMAenLOCxZFpsayuCPRbWz6Z9UJfLeAbfAPmmDYoKNXRFll8/jp5Ie7iAhSQgfFggWwyiqMRCFA3GPTsOJS4H1tSiGlMjVzbJnkusPKXfJ0dK3OH9u7ox9ESpi91T0MemXw3nn+/6QRvjGtgFH+wMDuQX7ta89+yW+wqdXX9ZQu8BzY";
    private static final VuforiaLocalizer.CameraDirection CAMERA_CHOICE = BACK;
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";
    VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;

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

    //TODO: test if this turns right with positive anngles

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

        //Distance is in inches

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

    /**
     * Vuforia Related
     */

    public int lookForwardAndCheck() {
        int position = 0;
        initVuforiaThingy();
        if (this.tfod != null) {
            tfod.activate();
        } else {
            return 0;
        }
        // getUpdatedRecognitions() will return null if no new information is available since
        // the last time that call was made.
        while (position == 0) {
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                telemetry.addData("updatedRecognitions", updatedRecognitions.toString());
                telemetry.update();
                if (updatedRecognitions.size() == 2) {
                    int goldMineralX = -1;
                    int silverMineralX = -1;
                    for (Recognition recognition : updatedRecognitions) {
                        if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                            goldMineralX = (int) recognition.getLeft();
                        } else if (silverMineralX == -1) {
                            silverMineralX = (int) recognition.getLeft();
                        } else {
                            return 3;
                        }
                    }
                    if (goldMineralX < silverMineralX) {
                        position = 1;
                    } else {
                        position = 2;
                    }
                }
            }
        }
        return position;
    }

    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }

    public void initVuforiaThingy() {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CAMERA_CHOICE;

        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }
    }
}
