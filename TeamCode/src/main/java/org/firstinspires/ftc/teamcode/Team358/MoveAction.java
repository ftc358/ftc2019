package org.firstinspires.ftc.teamcode.Team358;

public class MoveAction extends RobotAction {
    Boolean requireMultithreading = false;
    RobotPosition toPosition;
    Runnable actionMethod;

    MoveAction(RobotPosition toPosition, Runnable actionMethod) {
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
