package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;

import java.util.List;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;

public class Vuforia_Stuffs{

   static double[] Vuforia_Thingy_Thing (boolean targetVisible, List<VuforiaTrackable> allTrackables, OpenGLMatrix lastLocation, float mmPerInch){

       double[] returningStuff = {0, 0, 0, 0, 0, 0, 0};
       targetVisible = false;

       for (VuforiaTrackable trackable : allTrackables) {
           if (((VuforiaTrackableDefaultListener)trackable.getListener()).isVisible()) {
               targetVisible = true;

               // getUpdatedRobotLocation() will return null if no new information is available since
               // the last time that call was made, or if the trackable is not currently visible.
               OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener)trackable.getListener()).getUpdatedRobotLocation();
               if (robotLocationTransform != null) {
                   lastLocation = robotLocationTransform;
               }
               break;
           }
       }

       if (targetVisible) {
           VectorF translation = lastLocation.getTranslation();

           returningStuff[0] = 1;
           returningStuff[1] = translation.get(0) / mmPerInch;
           returningStuff[2] = translation.get(1) / mmPerInch;
           returningStuff[3] = translation.get(2) / mmPerInch;

           Orientation rotation = Orientation.getOrientation(lastLocation, EXTRINSIC, XYZ, DEGREES);

           returningStuff[4] = rotation.firstAngle;
           returningStuff[5] = rotation.secondAngle;
           returningStuff[6] = rotation.thirdAngle;

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
