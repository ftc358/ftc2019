package org.firstinspires.ftc.teamcode.Team358.PAA;

import com.qualcomm.robotcore.util.RobotLog;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public abstract class AutoEngine358 extends Robot358Main {

    /**
     * Core Engine Properties
     */

    public List<RobotAction> robotActions = new ArrayList<>();

    public List<MoveAction> robotMoveActions;

    /**
     * Config
     */

    double power = .5;
    boolean runUsingEncoders = true;
    public RobotPosition currentPosition;


    /**
     * Engine Functions
     */

    public void addRobotAction(RobotAction actionMethod) {
        robotActions.add(actionMethod);
    }

    public void runRobotActions() {
        for (RobotAction action : robotActions) {
            action.getActionMethod().run();
        }
    }

    /**
     * Helper Functions
     */

    public void generateMoveActions(List<RobotPosition> positions) {
        RobotPosition lastPosition = new RobotPosition(0, 0);
        //TODO: Change to actual starting position
        for (RobotPosition position : positions) {
            final double currentHeading = getCurrentHeading();
            final double targetHeading = position.getRelativeHeading(lastPosition);

            robotMoveActions.add(new MoveAction(position, new Runnable() {
                @Override
                public void run() {
                    try {
                        turn(new IMUTurner(calculateTurn(currentHeading, targetHeading), power, _imu1, .25, null), runUsingEncoders, true);
                        forward(0.5, 2);
                    } catch (InterruptedException e) {
                        RobotLog.d("This should not happen.");
                    }
                }
            }));
        }
    }

    /**
     * Gimme Functions
     */

    public double calculateTurn(double current, double target) {
        double diff = target - current;
        if (diff < 0)
            diff += 360;
        if (diff > 180)
            return -(180 - abs(abs(current - target) - 180));
        else
            return (180 - abs(abs(current - target) - 180));
    }
}
