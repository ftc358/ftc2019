package org.firstinspires.ftc.teamcode.Team358.PAA;

import java.util.concurrent.Callable;

public abstract class RobotAction {

    abstract public Boolean getRequireMultithreading();

    abstract public Runnable getActionMethod();
}