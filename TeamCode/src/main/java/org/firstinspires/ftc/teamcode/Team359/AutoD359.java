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
    DcMotor slideExtend;
    DcMotor Rotation;
    CRServo Intake;

    state state359;
    int detected = 0;
    VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;


    List<Recognition> updatedRecognitions;

    public void runOpMode() throws InterruptedException {

        leftMotor = hardwareMap.dcMotor.get("lM");
        rightMotor = hardwareMap.dcMotor.get("rM");
        leftLatch = hardwareMap.dcMotor.get("lL");
        rightLatch = hardwareMap.dcMotor.get("rL");
        slideExtend = hardwareMap.dcMotor.get("sE");
        Rotation  = hardwareMap.dcMotor.get("rotation");
        Intake = hardwareMap.crservo.get("intake");

        rightLatch.setDirection(DcMotorSimple.Direction.REVERSE);
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        state359 = state.LATCH;
        waitForStart();

        while (opModeIsActive()) {

            telemetry.addData("Going into state", state359);
            telemetry.update();

            switch (state359) {

                case LATCH:
                    Encoders359.Forward(leftLatch, rightLatch, 1, -15800);
                    Encoders359.Forward(leftMotor,rightMotor,0.5,400);
                    Encoders359.Turn(leftMotor,rightMotor,0.5,250);
                    state359 = state.DETECT;
                    break;

                case DETECT:
                    //initVuforiaThingy();
                    //initTfod();
                    //detected = lookForThings();
                    detected = lookForwardAndCheck();
                    // detected values: 0 if nothing detected, 1 is left, 2 is center, 3 is right
                    telemetry.addData("Position of the cube", detected);
                    telemetry.update();
                    state359 = state.KNOCK;
                    break;

                case KNOCK:
                    telemetry.addData("Detected", detected);
                    if (detected == 1) {
                        Encoders359.Turn(leftMotor, rightMotor, 0.5, 450);          //Go Left
                        Encoders359.Forward(leftMotor,rightMotor,0.5,5000);
                    } else if (detected == 2) {
                        Encoders359.Turn(leftMotor,rightMotor,0.5,-250);            //Go forward
                        Encoders359.Forward(leftMotor, rightMotor, 0.5, 6000);
                    } else if (detected == 3) {
                        Encoders359.Turn(leftMotor, rightMotor, 0.5, -750);         //Go right
                        Encoders359.Forward(leftMotor,rightMotor,0.5,6300);
                        Encoders359.Turn(leftMotor,rightMotor,0.5,500);
                        Encoders359.Forward(leftMotor,rightMotor,1,-7000);
                    }
                    state359 = state.DRIVE;
                    break;

                case DRIVE:
                    if (detected == 1) {
                        Encoders359.Turn(leftMotor,rightMotor,0.5,-1200);
                        Encoders359.Forward(leftMotor,rightMotor,0.5,2000);
                        Encoders359.Intake(Rotation,0.5,-1000);
                        Encoders359.Intake(Rotation,0.5,1000);                      //Push the cube
                        Encoders359.Forward(leftMotor,rightMotor,0.5,-3000);
                        Encoders359.Turn(leftMotor,rightMotor,0.5,2600);
                        Encoders359.Forward(leftMotor,rightMotor,0.5,5000);
                    } else if (detected == 2) {
                        Encoders359.Intake(Rotation,0.5,-1000);
                        Encoders359.Intake(Rotation,0.5,1000);                      //Push the cube
                        Encoders359.Turn(leftMotor,rightMotor,0.5,-1700);
                        Encoders359.Forward(leftMotor,rightMotor,0.5,-2500);
                        Encoders359.Turn(leftMotor, rightMotor,0.5,850);            //Avoid he ball
                        Encoders359.Forward(leftMotor,rightMotor,0.5,-10000);
                    } else if (detected == 3) {
//                        Encoders359.Turn(leftMotor,rightMotor,0.5,1200);
//                        Encoders359.Forward(leftMotor,rightMotor,0.5,1500);
//                        Encoders359.Intake(Rotation,0.5,-1000);
//                        Encoders359.Intake(Rotation,0.5,1000);                      //Push the cube
//                        Encoders359.Forward(leftMotor,rightMotor,0.5,-3000);
//                        Encoders359.Turn(leftMotor,rightMotor,0.5,-2800);
//                        Encoders359.Forward(leftMotor,rightMotor,0.5,4300);
                    }
                    state359 = state.STOP;
                    break;

                case STOP:
                    leftMotor.setPower(0);
                    rightMotor.setPower(0);
                    leftLatch.setPower(0);
                    rightLatch.setPower(0);
                    slideExtend.setPower(0);
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
        sleep(1500);
        while (position == 0) {
            updatedRecognitions = tfod.getUpdatedRecognitions();
            int maxSize = 0;
            for (int i = 0; i < 50; i++){
                List<Recognition> newRecognitions = tfod.getUpdatedRecognitions();
                if (newRecognitions != null && newRecognitions.size() > maxSize){
                    updatedRecognitions = newRecognitions;
                    maxSize = newRecognitions. size();
                }
                sleep(10);
            }

            if (updatedRecognitions != null) {
                telemetry.addData("updatedRecognitions", updatedRecognitions.toString());
                telemetry.update();

                if (updatedRecognitions.size() == 2) {
                    int goldMineralX = -1;
                    int silverMineralX = -1;
                    for (Recognition recognition : updatedRecognitions) {
                        if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                            goldMineralX = (int) recognition.getTop();
                        } else if (silverMineralX == -1) {
                            silverMineralX = (int) recognition.getTop();
                        } else {
                            return 3;
                        }
                    }

                    if (goldMineralX < silverMineralX) {
                        position = 2;
                    } else {
                        position = 1;
                    }
                }
                else if (updatedRecognitions.size() == 1) {
                    int THRESHOLD_UP = 850, THRESHOLD_DOWN = 850;
                    for (Recognition recognition : updatedRecognitions) {
                        if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                            int topCoord = (int) recognition.getTop();
                            if (topCoord > THRESHOLD_UP) {
                                position = 1;
                            }
                            else if (topCoord < THRESHOLD_DOWN) {
                                position = 2;
                            }
                        }
                        else if (recognition.getLabel().equals(LABEL_SILVER_MINERAL)){
                                position = 3;
                        }
                    }
                }
                else position = 3;
            }
        }
        return position;
    }

    enum state {

        LATCH, DETECT, KNOCK, DRIVE, STOP

    }
}
