package archive;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;

import java.util.List;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;

@Disabled

public class Vuforia_Stuffs{

    static double[] Vuforia_Thingy_Thing (List<VuforiaTrackable> allTrackables, OpenGLMatrix lastLocation, float mmPerInch){

        double[] returningStuff = {0, 0, 0, 0, 0, 0, 0, 0};
        boolean targetVisible = false;

        for (VuforiaTrackable trackable : allTrackables) {
            if (((VuforiaTrackableDefaultListener)trackable.getListener()).isVisible()) {
                targetVisible = true;
                returningStuff[0] = 1;

                if (trackable.getName() == "Blue-Rover"){
                    returningStuff[1] = 1;
                }
                else if (trackable.getName() == "Red-Footprint"){
                    returningStuff[1] = 2;
                }
                else if (trackable.getName() == "Front-Craters"){
                    returningStuff[1] = 3;
                }
                else if (trackable.getName() == "Back-Space"){
                    returningStuff[1] = 4;
                }

                OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener)trackable.getListener()).getUpdatedRobotLocation();
                if (robotLocationTransform != null) {
                    lastLocation = robotLocationTransform;
                }
                break;
            }
        }

        if (targetVisible) {
            VectorF translation = lastLocation.getTranslation();


            returningStuff[2] = translation.get(0) / mmPerInch;
            returningStuff[3] = translation.get(1) / mmPerInch;
            returningStuff[4] = translation.get(2) / mmPerInch;

            Orientation rotation = Orientation.getOrientation(lastLocation, EXTRINSIC, XYZ, DEGREES);

            returningStuff[5] = rotation.firstAngle;
            returningStuff[6] = rotation.secondAngle;
            returningStuff[7] = rotation.thirdAngle;

        }
        else {

            returningStuff[0] = 0;
            returningStuff[1] = 0;
            returningStuff[2] = 0;
            returningStuff[3] = 0;
            returningStuff[4] = 0;
            returningStuff[5] = 0;
            returningStuff[6] = 0;
        }

        return returningStuff;

    }

}