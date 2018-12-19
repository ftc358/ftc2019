package org.firstinspires.ftc.teamcode.Team358.PAA;

import java.util.ArrayList;
import java.util.List;

public class AutoEngine358 {
    public List<Runnable> robotActions = new ArrayList<>();

    public void addRobotAction(Runnable actionMethod) {
        robotActions.add(actionMethod);
    }

    public void runRobotActions()
    {
        for(Runnable aMethod : robotActions) {
            aMethod.run();
        }
    }
}
