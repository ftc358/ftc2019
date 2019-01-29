package org.firstinspires.ftc.teamcode.Team358;

import com.qualcomm.robotcore.util.RobotLog;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public abstract class AutoEngine358 extends Robot358Main {

    /**
     * Core Engine Properties
     */

    private List<RobotAction> robotActions = new ArrayList<>();
    //    private List<MoveAction> robotMoveActions;
    private RobotPosition currentPosition;

    /**
     * Config
     */

    private double POWER = 1.0;
    private Boolean RUN_USING_ENCODERS = true;
    private RobotPosition STARTING_POSITION;

    /**
     * Engine Functions
     */

    public void initialize(RobotPosition STARTING_POSITION) throws InterruptedException {
        super.initialize();
        this.STARTING_POSITION = STARTING_POSITION;
    }


    public void addRobotAction(RobotAction actionMethod) {
        robotActions.add(actionMethod);
    }

    public void runRobotActions() {
        for (RobotAction action : robotActions) {
            action.getActionMethod().run();
            if (action instanceof MoveAction) {
                // Update robot position
                currentPosition = ((MoveAction) action).getToPosition();
            }
            telemetry.addData("Current Position", currentPosition);
            telemetry.update();
        }
    }

    /**
     * Helper Functions
     */

    public void generateMoveActions(List<RobotPosition> positions) {
        RobotPosition lastPosition = STARTING_POSITION;
        //TODO: use @findingTurns here to optimize driving
        for (RobotPosition position : positions) {
            final double currentHeading = lastPosition.heading;
            final double targetHeading = lastPosition.getRelativeHeading(position);
            if (targetHeading == 0 || targetHeading == 90 || targetHeading == 180 || targetHeading == 270) {
                robotActions.add(new MoveAction(position, new Runnable() {
                    @Override
                    public void run() {
                        try {
                            turn(new IMUTurner(calculateTurn(currentHeading, targetHeading), POWER, _imu1, 1, null), RUN_USING_ENCODERS, true);
                            forward(POWER, 2);
                        } catch (InterruptedException e) {
                            RobotLog.d("This should not happen.");
                        }
                    }
                }));

                lastPosition = position;
                lastPosition.heading = targetHeading;

            } else {
                robotActions.add(new MoveAction(position, new Runnable() {
                    @Override
                    public void run() {
                        try {
                            turn(new IMUTurner(calculateTurn(currentHeading, targetHeading), POWER, _imu1, 1, null), RUN_USING_ENCODERS, true);
                            forward(POWER, sqrt(8));
                        } catch (InterruptedException e) {
                            RobotLog.d("This should not happen.");
                        }
                    }
                }));

                lastPosition = position;
            }
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

    public List<Integer> computeTurningPointIndices(List<RobotPosition> points) {
        List<Integer> indices = new ArrayList<Integer>();
        for (int i = 1; i < points.size() - 1; i++) {
            RobotPosition prev = points.get(i - 1);
            RobotPosition curr = points.get(i);
            RobotPosition next = points.get(i + 1);
            int dxPrev = prev.x - curr.x;
            int dyPrev = prev.y - curr.y;
            int dxNext = next.x - curr.x;
            int dyNext = next.y - curr.y;
            if (dxPrev != dxNext && dyPrev != dyNext) {
                indices.add(i);
            }
        }
        return indices;
    }

    public List<RobotPosition> calculateTurningPoints(List<RobotPosition> points) {
        {
            List<Integer> indices = computeTurningPointIndices(points);
//            System.out.println("Turning points are at " + indices);

            List<RobotPosition> turningPoints = indices.stream().map(i -> points.get(i))
                    .collect(Collectors.toList());
//            System.out.println("They are " + turningPoints);
            return turningPoints;

//            System.out.println("Collinear:");
//            indices.add(0, 0);
//            indices.add(points.size() - 1);
//            for (int i = 0; i < indices.size() - 1; i++)
//            {
//                int i0 = indices.get(i);
//                int i1 = indices.get(i + 1);
//                List<RobotPosition> collinear = points.subList(i0, i1 + 1);
//
//                System.out.println("    " + collinear);
//            }
        }
    }
}
