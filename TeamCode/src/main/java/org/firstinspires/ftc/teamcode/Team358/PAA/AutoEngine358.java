package org.firstinspires.ftc.teamcode.Team358.PAA;

import android.graphics.Point;

public class AutoEngine358 {
//    public List<RobotAction> robotActions = new ArrayList<>();

    RobotAction currentAction;

    public Point position = new Point(1, 1);

    public void runRobotActions() {
        currentAction.getActionMethod().run();
    }
}
