package org.firstinspires.ftc.teamcode.Team358.PAA;

import java.util.ArrayList;
import java.util.List;

public class AutoEngine358 {
    public List<RobotAction> robotActions = new ArrayList<>();

    public RobotPosition currentPosition = new RobotPosition(0, 0);

    public void addRobotAction(RobotAction actionMethod) {
        robotActions.add(actionMethod);
    }

    public void runRobotActions() {
        for (RobotAction action : robotActions) {
            action.getActionMethod().run();
        }
    }
}
