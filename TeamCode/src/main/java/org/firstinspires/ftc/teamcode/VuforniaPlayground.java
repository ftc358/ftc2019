package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.Arrays;
import java.util.List;

import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;

@Autonomous
public class VuforniaPlayground extends LinearOpMode {

    private static final String VUFORIA_KEY = "AXzW9CD/////AAAAGTPAtr9HRUXZmowtd9p0AUwuXiBVONS/c5x1q8OvjMrQ8/XJGxEp0TP9Kl8PvqSzeXOWIvVa3AeB6MyAQboyW/Pgd/c4a4U/VBs1ouUsVBkEdbaq1iY7RR0cjYr3eLwEt6tmI37Ugbwrd5gmxYvOBQkGqzpbg2U2bVLycc5PkOixu7PqPqaINGZYSlvUzEMAenLOCxZFpsayuCPRbWz6Z9UJfLeAbfAPmmDYoKNXRFll8/jp5Ie7iAhSQgfFggWwyiqMRCFA3GPTsOJS4H1tSiGlMjVzbJnkusPKXfJ0dK3OH9u7ox9ESpi91T0MemXw3nn+/6QRvjGtgFH+wMDuQX7ta89+yW+wqdXX9ZQu8BzY";
    private static final VuforiaLocalizer.CameraDirection CAMERA_CHOICE = BACK;
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";

    VuforiaLocalizer vuforia;
    double[] list = {0, 0, 0, 0, 0, 0, 0};
    private TFObjectDetector tfod;

    public void runOpMode() {
        initVuforiaThingy();
        lookForThings();
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

    //TODO: Can modify this part should we be unable to enhance the camera's FOV

    public void lookForThings() {
        int position = 0;
        if (this.tfod != null) {
            tfod.activate();
        } else {
//            return 0;
        }
        // getUpdatedRecognitions() will return null if no new information is available since
        // the last time that call was made.
        while (position == 0) {
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
//            telemetry.addData("updatedRecognitions", updatedRecognitions);
            List<String> labels = Arrays.asList("");
            if (updatedRecognitions != null) {
                for (Recognition recognition : updatedRecognitions) {
                    labels.add(recognition.getLabel());
                }
            }
            telemetry.addData("labels", labels);
//            List<String> labels = Arrays.asList("");
//            if (updatedRecognitions != null) {
//                for (Recognition recognition : updatedRecognitions) {
//                    labels.add(recognition.getLabel());
//                }
//            }
//            telemetry.addData("labels", labels);
//            if (updatedRecognitions != null) {
//                if (updatedRecognitions.size() == 3) {
//                    int goldMineralX = -1;
//                    int silverMineral1X = -1;
//                    int silverMineral2X = -1;
//                    for (Recognition recognition : updatedRecognitions) {
//                        if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
//                            goldMineralX = (int) recognition.getLeft();
//                        } else if (silverMineral1X == -1) {
//                            silverMineral1X = (int) recognition.getLeft();
//                        } else {
//                            silverMineral2X = (int) recognition.getLeft();
//                        }
//                    }
//                    if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {
//                        if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
//                            position = 1;
//                        } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
//                            position = 3;
//                        } else {
//                            position = 2;
//                        }
//                    }
//                }
//                else {
//                    boolean goldVisible = false;
//                    double coord = 0;
//                    if (updatedRecognitions != null && updatedRecognitions.size() > 0) {
//                        for (Recognition recognition : updatedRecognitions) {
//                            if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
//                                goldVisible = true;
//                                coord = (recognition.getLeft()+ recognition.getRight())/2;
//                            }
//                        }
//                        if (goldVisible) {
//                            // idk if we need actual position
//                            // maybe that helps us be more accurate if we can see 2 at the same time.
//                            position = -(int)(100*coord) -1;
//                        }
//                        // silver
//                        // i think i'm writing bad logic things
//                        else position = 4;
//                    }
//                }
//            }
        }
//        return position;
    }
}
