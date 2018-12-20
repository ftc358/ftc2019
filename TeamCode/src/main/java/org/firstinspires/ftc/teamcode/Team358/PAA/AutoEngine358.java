package org.firstinspires.ftc.teamcode.Team358.PAA;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

public class AutoEngine358 {
    public List<RobotAction> robotActions = new ArrayList<>();

//    ExecutorService executor = Executors.newFixedThreadPool(10);

    public Point position = new Point(1, 1);

    public void addRobotAction(RobotAction actionMethod) {
        robotActions.add(actionMethod);
    }

    public void runRobotActions() {
        for (RobotAction action : robotActions) {
//            if (action.getRequireMultithreading()) {
//                Thread t = new Thread(action.getActionMethod());
//                RobotLog.a("Thread created");
//                t.start();
////                new Thread(action.getActionMethod()).start();
////                executor.execute(action.getActionMethod());
//            } else {
            action.getActionMethod().run();
        }
    }
}
