package org.firstinspires.ftc.teamcode.Team358;

import android.util.Log;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public abstract class AutoEngine358 extends Robot358Main {

    List<RobotPosition> robotPositionsWithHeadings = new ArrayList<>();
    /**
     * Core Engine Properties
     */

    private List<RobotAction> robotActions = new ArrayList<>();
    private List<MoveAction> robotMoveActions = new ArrayList<>();
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
        currentPosition = STARTING_POSITION;
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
            telemetry.addData("Current position", currentPosition.getX() + ", " + currentPosition.getY());
            telemetry.addData("Current heading", currentPosition.getHeading());
            telemetry.update();
        }
    }

    public void clearMoveActions() {
        robotMoveActions.clear();
    }

    public void clearRobotActions() {
        robotActions.clear();
    }

    /**
     * Navigation Functions
     */

    public void generateMoveActions(List<RobotPosition> positions, boolean optimize) {

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

            targetPosition.setHeading(absolutetTargetHeading);

            robotPositionsWithHeadings.add(currentPosition);
        }

        if (optimize) {
            optimizeContinuousSegments();
        }
    }

    public void optimizeContinuousSegments() {
        boolean strafe = false;

        List<Integer> turningIndices = computeTurningPointIndices(robotPositionsWithHeadings);

        List<MoveAction> monitor = robotMoveActions;

        Integer numberOfMoveActionsRemoved = 0;

        // finding segments of collinear robot positions
        for (int i = 0; i < turningIndices.size() - 1; i++) {
            final Integer first = turningIndices.get(i);
            if (turningIndices.size() > i + 1) {
                Integer second = turningIndices.get(i + 1);

                List<RobotPosition> collinearPositions = new ArrayList<>();

                collinearPositions.addAll(robotPositionsWithHeadings.subList(first, second + 1));

                //remove individual move actions
                robotMoveActions.subList(first - numberOfMoveActionsRemoved, second - numberOfMoveActionsRemoved).clear();

                if (strafe) {
                    if (robotPositionsWithHeadings.get(first).getHeading() == robotPositionsWithHeadings.get(second).getHeading()) {
                        //get heading of the segment
                        double segmentHeading = robotPositionsWithHeadings.get(first).getHeading();
                        //add optimized (continuous) action to the start of the original segment
                        if (segmentHeading == 0 || segmentHeading == 90 || segmentHeading == 180 || segmentHeading == 270) {
                            robotMoveActions.add(first - numberOfMoveActionsRemoved, new MoveAction(robotPositionsWithHeadings.get(second), () -> {
                                strafeWithCheck(POWER, 2, second - first, collinearPositions);
                            }));
                        } else {
                            robotMoveActions.add(first - numberOfMoveActionsRemoved, new MoveAction(robotPositionsWithHeadings.get(second), () -> {
                                strafeWithCheck(POWER, sqrt(8), second - first, collinearPositions);
                            }));
                        }
                        numberOfMoveActionsRemoved = numberOfMoveActionsRemoved + (second - first - 1);
                    }
                } else {
                    //get heading of the segment
                    double segmentHeading = robotPositionsWithHeadings.get(first).getRelativeHeading(robotPositionsWithHeadings.get(first + 1));
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
                    numberOfMoveActionsRemoved = numberOfMoveActionsRemoved + (second - first - 1);
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

        int ticks = (int) (((distancePerSegment * numberOfSegments / (4 * Math.PI) * 1120)) * 4 / 3 + 0.5);

//        //Reset Encoders358
//        fL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        bL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        fR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        bR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set to RUN_TO_POSITION mode
        fL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        int fLStartingPosition = fL.getCurrentPosition();

        //Set Target Position
        fL.setTargetPosition(fL.getCurrentPosition() + ticks);
        bL.setTargetPosition(bL.getCurrentPosition() + ticks);
        fR.setTargetPosition(fR.getCurrentPosition() + ticks);
        bR.setTargetPosition(bR.getCurrentPosition() + ticks);

        //Set Drive Power
        fL.setPower(power);
        bL.setPower(power);
        fR.setPower(power);
        bR.setPower(power);

        int numberOfSegmentsLast = 0;
        while (fL.isBusy() && fR.isBusy() && bL.isBusy() && bR.isBusy()) {
            int segmentsTraveled = ((fL.getCurrentPosition() - fLStartingPosition) / (int) (((distancePerSegment / (4 * Math.PI) * 1120)) * 4 / 3 + 0.5));
            if (segmentsTraveled > numberOfSegmentsLast) {
                currentPosition = collinearPositions.get(segmentsTraveled);
                numberOfSegmentsLast = segmentsTraveled;
                Log.d("Current Position updated inside forwardWithCheck", currentPosition.toString());
            }
        }

        //Stop and Change Mode back to Normal
        fL.setPower(0);
        bL.setPower(0);
        fR.setPower(0);
        bR.setPower(0);
    }

    public void strafeWithCheck(double power, double distancePerSegment, int numberOfSegments, List<RobotPosition> collinearPositions) {

        runUsingEncoders();

        int ticks = (int) (distancePerSegment * numberOfSegments * 133);

        //Reset Encoders358
//        fL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        bL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        fR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        bR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set to RUN_TO_POSITION mode
        fL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        int fLStartingPosition = fL.getCurrentPosition();

        //Set Target Position
        fL.setTargetPosition(fL.getCurrentPosition() - ticks);
        bL.setTargetPosition(bL.getCurrentPosition() + ticks);
        fR.setTargetPosition(fR.getCurrentPosition() + ticks);
        bR.setTargetPosition(bR.getCurrentPosition() - ticks);

        //Set Drive Power
        fL.setPower(power);
        bL.setPower(power);
        fR.setPower(power);
        bR.setPower(power);

        int numberOfSegmentsLast = 0;
        while (fL.isBusy() && fR.isBusy() && bL.isBusy() && bR.isBusy()) {
            //Wait Until Target Position is Reached
            int segmentsTraveled = ((fL.getCurrentPosition() - fLStartingPosition) / 133);
            if (segmentsTraveled > numberOfSegmentsLast) {
                currentPosition = collinearPositions.get(segmentsTraveled);
                numberOfSegmentsLast = segmentsTraveled;
                Log.d("Current Position updated inside strafeWithCheck", currentPosition.toString());
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
        ArrayList<RobotPosition> turns = new ArrayList<>();

        ArrayList<Integer> turningIndices = new ArrayList<>();

        turningIndices.add(0);

        for (int i = 0; i < robotPositionsWithHeadings.size(); i++) {
            turns.add(null);
        }

        if (robotPositionsWithHeadings.size() > 2) {
            RobotPosition base = robotPositionsWithHeadings.get(0);
            RobotPosition next = robotPositionsWithHeadings.get(1);
            double slope = 1.0 * (next.getY() - base.getY()) / (next.getX() - base.getX());

            for (int i = 2; i < robotPositionsWithHeadings.size(); i++) {
                RobotPosition newpoint = robotPositionsWithHeadings.get(i);

                double newslope = 1.0 * (newpoint.getY() - next.getY()) / (newpoint.getX() - next.getX());
                if (newslope != slope) {
                    turns.set(i - 1, robotPositionsWithHeadings.get(i - 1));
                    turningIndices.add(i - 1);
                    slope = newslope;
                }

                next = newpoint;
            }
        }

        turningIndices.add(robotPositionsWithHeadings.size() - 1);

        return turningIndices;

    }
}
