package org.firstinspires.ftc.teamcode.Team358.PAA;

public class RobotPosition {
    int x;
    int y;

    //Initializer
    RobotPosition(int x, int y) {
        if (x < 39) {
            if (y < 39) {
                this.x = x;
                this.y = y;
            } else {
                throw new IllegalArgumentException("Y value is outside the field");
            }
        } else {
            throw new IllegalArgumentException("X value is outside the field");
        }
    }

    /**
     * Relative position:
     * 4 0 5
     * 2 R 3
     * 6 1 7
     */

    public int getRelativePosition(RobotPosition nextPosition, RobotPosition currentPosition) {
        int relativePosition = 8;

        switch (nextPosition.x - currentPosition.x) {
            case 1:
                switch (nextPosition.y - currentPosition.y) {
                    case 1:
                        relativePosition = 5;
                    case 0:
                        relativePosition = 3;
                    case -1:
                        relativePosition = 7;
                    default:
                        throw new IllegalArgumentException("Positions not adjacent");
                }
            case 0:
                switch (nextPosition.y - currentPosition.y) {
                    case 1:
                        relativePosition = 0;
                    case 0:
                        throw new IllegalArgumentException("Is current position");
                    case -1:
                        relativePosition = 1;
                    default:
                        throw new IllegalArgumentException("Positions not adjacent");
                }
            case -1:
                switch (nextPosition.y - currentPosition.y) {
                    case 1:
                        relativePosition = 4;
                    case 0:
                        relativePosition = 2;
                    case -1:
                        relativePosition = 6;
                    default:
                        throw new IllegalArgumentException("Positions not adjacent");
                }
        }
        return relativePosition;
    }
}
