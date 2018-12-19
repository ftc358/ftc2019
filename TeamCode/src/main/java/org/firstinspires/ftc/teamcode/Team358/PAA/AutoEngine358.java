package org.firstinspires.ftc.teamcode.Team358.PAA;

import java.util.ArrayList;
import java.util.List;

public class AutoEngine358 {
    public List<RobotAction> robotActions = new ArrayList<>();

    public void addRobotAction(RobotAction actionMethod) {
        robotActions.add(actionMethod);
    }

    public void runRobotActions()
    {
        for(RobotAction action : robotActions) {
            if (action.requireMultithreading) {
                new Thread(action.actionMethod).start();
                // Probably creating a new thread for every action that requires this;
                // I only run this for 30s so should probably get away from this
            } else {
                action.actionMethod.run();
            }
        }
    }
}
