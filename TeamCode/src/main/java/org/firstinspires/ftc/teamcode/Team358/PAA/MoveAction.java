package org.firstinspires.ftc.teamcode.Team358.PAA;

import android.graphics.Point;

public class MoveAction extends RobotAction {
    Boolean requireMultithreading = false;
    Point toPosition;
    Runnable actionMethod;

    MoveAction(Point toPosition, Runnable actionMethod) {
        this.toPosition = toPosition;
        this.actionMethod = actionMethod;
    }

    public Boolean getRequireMultithreading() {
        return requireMultithreading;
    }

    public Runnable getActionMethod() {
        return actionMethod;
    }
}
