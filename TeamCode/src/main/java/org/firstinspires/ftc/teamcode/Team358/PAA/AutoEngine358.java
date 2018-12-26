package org.firstinspires.ftc.teamcode.Team358.PAA;

import com.qualcomm.robotcore.util.RobotLog;

import java.util.ArrayList;
import java.util.List;

import java8.util.stream.Collectors;
import java8.util.stream.Stream;

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
        //TODO: use @findingTurns here to optimize driving
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

            lastPosition = position;
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

//    public List<RobotPosition> findingTurns(final List<RobotPosition> positions) {
////        List<Point> points = new ArrayList<Point>();
////        points.add(createPoint("A", 1, 0));
////        points.add(createPoint("B", 1, 1));
////        points.add(createPoint("C", 1, 2));
////        points.add(createPoint("D", 2, 2));
////        points.add(createPoint("E", 3, 1));
////        points.add(createPoint("F", 4, 1));
////        points.add(createPoint("G", 5, 1));
////        points.add(createPoint("H", 5, 2));
//
//        List<Integer> indices = computeTurningPointIndices(positions);
//        System.out.println("Turning points are at " + indices);
//
////        List<RobotPosition> turningPoints = Stream.of(indices).map(i -> positions.get(i))
////                .collect(Collectors.toList());
//
////        System.out.println("Collinear:");
////        indices.add(0, 0);
////        indices.add(positions.size() - 1);
////        for (int i = 0; i < indices.size() - 1; i++)
////        {
////            int i0 = indices.get(i);
////            int i1 = indices.get(i + 1);
////            List<RobotPosition> collinear = positions.subList(i0, i1 + 1);
////
////            System.out.println("    " + collinear);
////        }
//    }

    private static List<Integer> computeTurningPointIndices(List<RobotPosition> points) {
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
}
