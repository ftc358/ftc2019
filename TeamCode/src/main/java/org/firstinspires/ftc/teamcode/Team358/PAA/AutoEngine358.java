package org.firstinspires.ftc.teamcode.Team358.PAA;

import com.qualcomm.robotcore.util.RobotLog;

import java.util.ArrayList;
import java.util.List;

public abstract class AutoEngine358 extends Robot358Main {

    /**
     * Core Engine Properties
     */

    public List<RobotAction> robotActions = new ArrayList<>();

    public RobotPosition currentPosition = new RobotPosition(0, 0);
    //TODO: Change to actual starting position

    public List<MoveAction> robotMoveActions;

    /**
     * Config
     */

    double power = .5;
    boolean runUsingEncoders = true;


    /**
     * Functions
     */

    public void addRobotAction(RobotAction actionMethod) {
        robotActions.add(actionMethod);
    }

    public void runRobotActions() {
        for (RobotAction action : robotActions) {
            action.getActionMethod().run();
        }
    }

    public void generateMoveActions(List<RobotPosition> positions) {
        RobotPosition lastPosition = new RobotPosition(0, 0);
        //TODO: Change to actual starting position
        for (RobotPosition position : positions) {
            final double currentHeading = getCurrentHeading();
            final double positionChange = position.getRelativeHeading(lastPosition);

            robotMoveActions.add(new MoveAction(position, new Runnable() {
                @Override
                public void run() {
                    try {
                        turn(new IMUTurner(positionChange - currentHeading, power, _imu1, .25, null), runUsingEncoders, true);
                        EncoderDrive.Forward(fL, bL, fR, bR, 0.5, 2);
                    } catch (InterruptedException e) {
                        RobotLog.d("This should not happen.");
                    }
                }
            }));
        }
    }
}
