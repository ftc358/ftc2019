package org.firstinspires.ftc.teamcode.Team358.PAA;

public class ScoreAction extends RobotAction {
    Boolean requireMultithreading = false;
    Runnable actionMethod;

    ScoreAction(Boolean requireMultithreading, Runnable actionMethod) {
        this.requireMultithreading = requireMultithreading;
        this.actionMethod = actionMethod;
    }

    public Boolean getRequireMultithreading() {
        return requireMultithreading;
    }

    public Runnable getActionMethod() {
        return actionMethod;
    }
}
