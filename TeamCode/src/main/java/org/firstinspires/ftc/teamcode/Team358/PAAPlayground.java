package org.firstinspires.ftc.teamcode.Team358;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.util.ArrayList;
import java.util.List;

@TeleOp
public class PAAPlayground extends AutoEngine358 {

    private List<RobotPosition> autoRoute = new ArrayList<>();

    private List<Integer> turningPointIndices = new ArrayList<>();

    public void runOpMode() throws InterruptedException {

        initialize();

        autoRoute.add(new RobotPosition(12,12,45));
        autoRoute.add(new RobotPosition(13, 12));
        autoRoute.add(new RobotPosition(14, 12));
        autoRoute.add(new RobotPosition(15, 12));
        autoRoute.add(new RobotPosition(16, 12));
        autoRoute.add(new RobotPosition(17, 12));
        autoRoute.add(new RobotPosition(18, 12));
        autoRoute.add(new RobotPosition(19, 12));
        autoRoute.add(new RobotPosition(20, 12));
        autoRoute.add(new RobotPosition(20, 13));
        autoRoute.add(new RobotPosition(20, 14));
        autoRoute.add(new RobotPosition(20, 15));
        autoRoute.add(new RobotPosition(20, 16));
        autoRoute.add(new RobotPosition(20, 17));
        autoRoute.add(new RobotPosition(20, 18));
        autoRoute.add(new RobotPosition(20, 19));
        autoRoute.add(new RobotPosition(20, 20));
        autoRoute.add(new RobotPosition(19, 19));
        autoRoute.add(new RobotPosition(18, 18));
        autoRoute.add(new RobotPosition(17, 17));
        autoRoute.add(new RobotPosition(16, 16));
        autoRoute.add(new RobotPosition(15, 15));
        autoRoute.add(new RobotPosition(14, 14));
        autoRoute.add(new RobotPosition(13, 13));
        autoRoute.add(new RobotPosition(12, 12));

        Log.d("Heading of the first position", ": "+ autoRoute.get(0).getHeading());

        generateMoveActions(autoRoute);

        addAllMoveActions();

        runRobotActions();
    }
}
