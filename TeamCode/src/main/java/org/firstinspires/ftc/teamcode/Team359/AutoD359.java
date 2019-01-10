package org.firstinspires.ftc.teamcode.Team359;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;

@Autonomous
public class AutoD359 extends LinearOpMode {

    private static final String VUFORIA_KEY = "AXzW9CD/////AAAAGTPAtr9HRUXZmowtd9p0AUwuXiBVONS/c5x1q8OvjMrQ8/XJGxEp0TP9Kl8PvqSzeXOWIvVa3AeB6MyAQboyW/Pgd/c4a4U/VBs1ouUsVBkEdbaq1iY7RR0cjYr3eLwEt6tmI37Ugbwrd5gmxYvOBQkGqzpbg2U2bVLycc5PkOixu7PqPqaINGZYSlvUzEMAenLOCxZFpsayuCPRbWz6Z9UJfLeAbfAPmmDYoKNXRFll8/jp5Ie7iAhSQgfFggWwyiqMRCFA3GPTsOJS4H1tSiGlMjVzbJnkusPKXfJ0dK3OH9u7ox9ESpi91T0MemXw3nn+/6QRvjGtgFH+wMDuQX7ta89+yW+wqdXX9ZQu8BzY";
    private static final VuforiaLocalizer.CameraDirection CAMERA_CHOICE = BACK;
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";

    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor leftLatch;
    DcMotor rightLatch;
    DcMotor Rotation;
    DcMotor slideExtend;
    CRServo Intake;

    state state359;
    int detected = 0;
    VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;

    public void runOpMode() throws InterruptedException {

        leftMotor = hardwareMap.dcMotor.get("lM");
        rightMotor = hardwareMap.dcMotor.get("rM");
        leftLatch = hardwareMap.dcMotor.get("lL");
        rightLatch = hardwareMap.dcMotor.get("rL");
        Rotation = hardwareMap.dcMotor.get("rotation");
        slideExtend = hardwareMap.dcMotor.get("sE");
        Intake = hardwareMap.crservo.get("intake");

        rightLatch.setDirection(DcMotorSimple.Direction.REVERSE);
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        state359 = state.DETECT;
        waitForStart();

        while (opModeIsActive()) {

            telemetry.addData("Going into state", state359);
            telemetry.update();
            switch (state359) {

                case LATCH:
                    Encoders359.Forward(leftLatch, rightLatch, 1, 8000);
                    state359 = state.DETECT;
                    break;

                case DETECT:
                    Encoders359.Turn(leftMotor, rightMotor, 0.25, 15);
                    //initVuforiaThingy();
                    //initTfod();
                    //detected = lookForThings();
                    Encoders359.Turn(leftMotor, rightMotor, 0.25, 15);
                    detected = lookForwardAndCheck();
                    Encoders359.Turn(leftMotor, rightMotor, 0.25, 15);
                    // detected values: 0 if nothing detected, 1 is left, 2 is center, 3 is right
                    telemetry.addData("Position of the cube", detected);
                    telemetry.update();
                    state359 = state.KNOCK;
                    break;

                case KNOCK:
                    telemetry.addData("Detected", detected);
                    if (detected == 1) {
                        Encoders359.Forward(leftMotor, rightMotor, 0.25, 500);     //Go left
                        Encoders359.Turn(leftMotor, rightMotor, 0.25, 300);

                    } else if (detected == 2) {
                        Encoders359.Forward(leftMotor, rightMotor, 0.25, 1000);    //Go forward

                    } else if (detected == 3) {
                        Encoders359.Forward(leftMotor, rightMotor, 0.25, 500);     //Go right
                        Encoders359.Turn(leftMotor, rightMotor, 0.25, 300);
                        //Codes to knock the mineral at the left
                    }
//                    sleep(10000);
                    state359 = state.DROP;
                    break;

                case DRIVE:
                    if (detected == 1) {
                        //Codes to go to the crater after knocking at the left
                    } else if (detected == 2) {
                        //Codes to go to the crater after knocking at the center
                    } else if (detected == 3) {
                        //Codes to go to the crater after knocking at the right
                    }
                    state359 = state.DRIVE;
                    break;

                case STOP:
                    leftMotor.setPower(0);
                    rightMotor.setPower(0);
                    leftLatch.setPower(0);
                    rightLatch.setPower(0);
                    /*Rotation.setPower(0);
                    slideExtend.setPower(0);
                    slideRetract.setPower(0);*/
            }
        }
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
                            return 1;
                        }
                    }

                    if (goldMineralX < silverMineralX) {
                        position = 2;
                    } else {
                        position = 3;
                    }
                }

            }

        }
        return position;
    }

    enum state {

        LATCH, DETECT, KNOCK, DROP, DRIVE, STOP

    }
}
