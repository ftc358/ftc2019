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

    //TODO: change to actual headings

    public double getRelativeHeading(RobotPosition lastPosition) {
        int relativeHeading = 0;

        switch (this.x - lastPosition.x) {
            case 1:
                switch (this.y - lastPosition.y) {
                    case 1:
                        relativeHeading = 5;
                        break;
                    case 0:
                        relativeHeading = 3;
                        break;
                    case -1:
                        relativeHeading = 7;
                        break;
                    default:
                        throw new IllegalArgumentException("Positions not adjacent");
                }
                break;
            case 0:
                switch (this.y - lastPosition.y) {
                    case 1:
                        relativeHeading = 0;
                        break;
                    case 0:
                        throw new IllegalArgumentException("Is current position");
                    case -1:
                        relativeHeading = 1;
                        break;
                    default:
                        throw new IllegalArgumentException("Positions not adjacent");
                }
                break;
            case -1:
                switch (this.y - lastPosition.y) {
                    case 1:
                        relativeHeading = 4;
                        break;
                    case 0:
                        relativeHeading = 2;
                        break;
                    case -1:
                        relativeHeading = 6;break;
                    default:
                        throw new IllegalArgumentException("Positions not adjacent");
                }
                break;
        }
        return relativeHeading;
    }
}
