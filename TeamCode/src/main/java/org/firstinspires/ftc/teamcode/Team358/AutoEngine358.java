package org.firstinspires.ftc.teamcode.Team358;

import android.util.Log;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

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
    private List<MoveAction> robotMoveActions = new ArrayList<>();
    private RobotPosition currentPosition;
    List<RobotPosition> robotPositionsWithHeadings = new ArrayList<>();

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

    public void addAllMoveActions() {
        robotActions.addAll(robotMoveActions);
    }

    public void runRobotActions() {
        for (RobotAction action : robotActions) {
            action.getActionMethod().run();
            if (action instanceof MoveAction) {
                // Update robot position
                currentPosition = ((MoveAction) action).getToPosition();
            }
//            telemetry.addData("Current position", currentPosition.x + ", " + currentPosition.y);
//            telemetry.addData("Current heading", currentPosition.heading);
//            telemetry.update();
        }
    }

    /**
     * Helper Functions
     */

    public void generateMoveActions(List<RobotPosition> positions) {

        for (RobotPosition currentPosition : positions) {

            RobotPosition targetPosition;

            try {
                targetPosition = positions.get(positions.indexOf(currentPosition) + 1);
            } catch (IndexOutOfBoundsException e) {
                robotPositionsWithHeadings.add(currentPosition);
                return;
            }

            final double absolutetTargetHeading = currentPosition.getRelativeHeading(targetPosition);

            if (absolutetTargetHeading == 0 || absolutetTargetHeading == 90 || absolutetTargetHeading == 180 || absolutetTargetHeading == 270) {
                robotMoveActions.add(new MoveAction(targetPosition, () -> {
                    try {
                        turn(new IMUTurner(calculateTurn(getAbsoluteCurrentHeading(), absolutetTargetHeading), POWER, _imu1, 1, null), RUN_USING_ENCODERS, true);
                        forward(POWER, 2);
                    } catch (InterruptedException e) {
                        Log.d("Error", "Failed to execute move runnable #1");
                    }
                }));
            } else {
                robotMoveActions.add(new MoveAction(targetPosition, () -> {
                    try {
                        turn(new IMUTurner(calculateTurn(getAbsoluteCurrentHeading(), absolutetTargetHeading), POWER, _imu1, 1, null), RUN_USING_ENCODERS, true);
                        forward(POWER, sqrt(8));
                    } catch (InterruptedException e) {
                        Log.d("Error", "Failed to execute move runnable #2");
                    }
                }));
            }

            Log.d("Headings", "current heading: (" + currentPosition.getHeading() + ")");
            Log.d("Headings", "current position: " + currentPosition.getX() + ", " + currentPosition.getY());
            Log.d("Headings", "target heading: (" + absolutetTargetHeading + ")");
            Log.d("Headings", "target position: " + targetPosition.getX() + ", " + targetPosition.getY());
            Log.d("Headings", "to turn: (" + calculateTurn(getAbsoluteCurrentHeading(), absolutetTargetHeading) + ")");

            targetPosition.setHeading(absolutetTargetHeading);

            robotPositionsWithHeadings.add(currentPosition);
        }
    }

    public void optimizeContinuousSegments() {
        List<Integer> turningIndices = computeTurningPointIndices(robotPositionsWithHeadings);

        Integer numberOfMoveActionsRemoved = 0;

        // finding segments of collinear robot positions
        for (int i = 0; i < turningIndices.size() - 1; i++) {
            final Integer first = turningIndices.get(i);
            if (turningIndices.size() > i + 1) {
                Integer second = turningIndices.get(i + 1);

                List<RobotPosition> collinearPositions = new ArrayList<>();

                collinearPositions.addAll(robotPositionsWithHeadings.subList(first, second + 1));

                //get heading of the segment
                double segmentHeading = robotPositionsWithHeadings.get(first).getRelativeHeading(robotPositionsWithHeadings.get(first + 1));

                //remove individual move actions
                robotMoveActions.subList(first, second).clear();

                numberOfMoveActionsRemoved += (second - first);

                //add optimized (continuous) action to the start of the original segment
                if (segmentHeading == 0 || segmentHeading == 90 || segmentHeading == 180 || segmentHeading == 270) {
                    robotMoveActions.add(first - numberOfMoveActionsRemoved, new MoveAction(robotPositionsWithHeadings.get(second), () -> {
                        try {
                            turn(new IMUTurner(calculateTurn(robotPositionsWithHeadings.get(first).getHeading(), segmentHeading), POWER, _imu1, 1, null), RUN_USING_ENCODERS, true);
                            forwardWithCheck(POWER, 2, second - first, collinearPositions);
                        } catch (InterruptedException e) {
                            Log.d("Error", "Failed to execute move runnable #3");
                        }
                    }));
                } else {
                    robotMoveActions.add(first - numberOfMoveActionsRemoved, new MoveAction(robotPositionsWithHeadings.get(second), () -> {
                        try {
                            turn(new IMUTurner(calculateTurn(robotPositionsWithHeadings.get(first).getHeading(), segmentHeading), POWER, _imu1, 1, null), RUN_USING_ENCODERS, true);
                            forwardWithCheck(POWER, sqrt(8), second - first, collinearPositions);
                        } catch (InterruptedException e) {
                            Log.d("Error", "Failed to execute move runnable #4");
                        }
                    }));
                }
            }
        }
    }

    /**
     * Robot Sensor
     */

    public double getAbsoluteCurrentHeading() {
        Orientation angles = _imu1.getAngularOrientation().toAxesReference(AxesReference.INTRINSIC).toAxesOrder(AxesOrder.ZYX).toAngleUnit(AngleUnit.DEGREES);
        double absoluteHeading;
        if (angles.firstAngle <= 0) {
            absoluteHeading = -angles.firstAngle;
        } else {
            absoluteHeading = 360 - angles.firstAngle;
        }
        return absoluteHeading + STARTING_POSITION.getHeading();
    }

    /**
     * Robot Motion
     */

    public void forwardWithCheck(double power, double distancePerSegment, int numberOfSegments, List<RobotPosition> collinearPositions) {

        runUsingEncoders();

        //Distance is in inches

        int ticks = (int) (((distancePerSegment * numberOfSegments / (4 * Math.PI) * 1130)) * 1.41 + 0.5);

        //Reset Encoders358
        fL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set to RUN_TO_POSITION mode
        fL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //Set Target Position
        fL.setTargetPosition(ticks);
        bL.setTargetPosition(ticks);
        fR.setTargetPosition(ticks);
        bR.setTargetPosition(ticks);

        //Set Drive Power
        fL.setPower(power);
        bL.setPower(power);
        fR.setPower(power);
        bR.setPower(power);

        int numberOfSegmentsLast = 0;
        while (fL.isBusy() && fR.isBusy() && bL.isBusy() && bR.isBusy()) {
            if (fL.getCurrentPosition() / (int) (((distancePerSegment / (4 * Math.PI) * 1130)) * 1.41 + 0.5) > numberOfSegmentsLast) {
                currentPosition = collinearPositions.get(fL.getCurrentPosition() / (int) (((distancePerSegment / (4 * Math.PI) * 1130)) * 1.41 + 0.5) - numberOfSegmentsLast);
                numberOfSegmentsLast = fL.getCurrentPosition() / (int) (((distancePerSegment / (4 * Math.PI) * 1130)) * 1.41 + 0.5);
            }
        }

        //Stop and Change Mode back to Normal
        fL.setPower(0);
        bL.setPower(0);
        fR.setPower(0);
        bR.setPower(0);
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
        indices.add(0);
        for (int i = 1; i < points.size() - 1; i++) {
            RobotPosition prev = points.get(i - 1);
            RobotPosition curr = points.get(i);
            RobotPosition next = points.get(i + 1);
            int dxPrev = prev.getX() - curr.getX();
            int dyPrev = prev.getY() - curr.getY();
            int dxNext = next.getX() - curr.getX();
            int dyNext = next.getY() - curr.getY();
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
