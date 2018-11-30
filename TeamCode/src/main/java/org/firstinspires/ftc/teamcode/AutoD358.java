package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;

@Autonomous
public class AutoD358 extends LinearOpMode {

    private static final String VUFORIA_KEY = "AXzW9CD/////AAAAGTPAtr9HRUXZmowtd9p0AUwuXiBVONS/c5x1q8OvjMrQ8/XJGxEp0TP9Kl8PvqSzeXOWIvVa3AeB6MyAQboyW/Pgd/c4a4U/VBs1ouUsVBkEdbaq1iY7RR0cjYr3eLwEt6tmI37Ugbwrd5gmxYvOBQkGqzpbg2U2bVLycc5PkOixu7PqPqaINGZYSlvUzEMAenLOCxZFpsayuCPRbWz6Z9UJfLeAbfAPmmDYoKNXRFll8/jp5Ie7iAhSQgfFggWwyiqMRCFA3GPTsOJS4H1tSiGlMjVzbJnkusPKXfJ0dK3OH9u7ox9ESpi91T0MemXw3nn+/6QRvjGtgFH+wMDuQX7ta89+yW+wqdXX9ZQu8BzY";
    private static final VuforiaLocalizer.CameraDirection CAMERA_CHOICE = BACK;
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";
    DcMotor lF;
    DcMotor lB;
    DcMotor rF;
    DcMotor rB;
    DcMotor lL;         // left lift
    DcMotor rL;         // right lift
    state state358;
    int detected = 0;
    VuforiaLocalizer vuforia;
    double[] list = {0, 0, 0, 0, 0, 0, 0};
    private TFObjectDetector tfod;

    public void runOpMode() throws InterruptedException {

        lF = hardwareMap.dcMotor.get("lF");
        lB = hardwareMap.dcMotor.get("lB");
        rF = hardwareMap.dcMotor.get("rF");
        rB = hardwareMap.dcMotor.get("rB");
        lL = hardwareMap.dcMotor.get("lL");
        rL = hardwareMap.dcMotor.get("rL");

        rF.setDirection(DcMotor.Direction.REVERSE);
        rB.setDirection(DcMotor.Direction.REVERSE);
        rL.setDirection(DcMotor.Direction.REVERSE);

        state358 = state.DETECT;
        waitForStart();

        while (opModeIsActive()) {

            telemetry.addData("Going into state", state358);
            telemetry.update();
            switch (state358) {

                case DETECT:

                    //initVuforiaThingy();
                    //initTfod();
                    //detected = lookForThings();
                    Encoders.Turn(lF, lB, rF, rB, 0.25, Encoders.Direction.left, 15);
                    detected = lookForwardAndCheck();
                    Encoders.Turn(lF, lB, rF, rB, 0.25, Encoders.Direction.right, 15);
                    // detected values: 0 if nothing detected, 1 is left, 2 is center, 3 is right
                    telemetry.addData("Position of the cube", detected);
                    telemetry.update();
                    state358 = state.KNOCK;
                    break;

                case KNOCK:
                    if (detected == 1) {
                        Encoders.Turn(lF, lB, rF, rB, 0.25, Encoders.Direction.left, 30);
                        Encoders.Forward(lF, lB, rF, rB, 0.25, 43);
                        Encoders.Turn(lF, lB, rF, rB, 0.25, Encoders.Direction.right, 70);
                        Encoders.Forward(lF, lB, rF, rB, 0.25, 20);
                    } else if (detected == 2) {
                        Encoders.Forward(lF, lB, rF, rB, 0.25, 45);
                    } else if (detected == 3) {
                        Encoders.Turn(lF, lB, rF, rB, 0.25, Encoders.Direction.right, 30);
                        Encoders.Forward(lF, lB, rF, rB, 0.25, 47);
                        Encoders.Turn(lF, lB, rF, rB, 0.25, Encoders.Direction.left, 75);
                        Encoders.Forward(lF, lB, rF, rB, 0.25, 10);
                    }
                    state358 = state.DROP;
                    break;

                case DROP:
                    EncoderWithOnlyTwoMotors.Forward(lL, rL, 0.25, 3);
                    state358 = state.POSITION;
                    break;

                case POSITION:
                    if (detected == 1) {
                        Encoders.Forward(lF, lB, rF, rB, 0.25, -8);
                        Encoders.Turn(lF, lB, rF, rB, 0.25, Encoders.Direction.left, 135);
                        Encoders.Forward(lF, lB, rF, rB, 0.25, -33);
                        Encoders.Turn(lF, lB, rF, rB, 0.25, Encoders.Direction.right, 60);
                    } else if (detected == 2) {
                        Encoders.Forward(lF, lB, rF, rB, 0.25, -15);
                        Encoders.Turn(lF, lB, rF, rB, 0.25, Encoders.Direction.right, 45);
                        Encoders.Forward(lF, lB, rF, rB, 0.25, 22);
                        Encoders.Turn(lF, lB, rF, rB, 0.25, Encoders.Direction.left, 90);
                    } else if (detected == 3) {
                        Encoders.Forward(lF, lB, rF, rB, 0.25, -10);
                    }
                    state358 = state.DRIVE;
                    break;

                case DRIVE:
                    Encoders.Forward(lF, lB, rF, rB, 0.25, -52);
                    EncoderWithOnlyTwoMotors.Forward(lL, rL, 0.25, 7);
                    state358 = state.STOP;
                    break;

                case STOP:

                    lF.setPower(0);
                    lB.setPower(0);
                    rF.setPower(0);
                    rB.setPower(0);
                    lL.setPower(0);
                    rL.setPower(0);
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


    enum state {

        DETECT, KNOCK, DROP, POSITION, DRIVE, STOP

    }

}