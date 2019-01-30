package org.firstinspires.ftc.teamcode.Team358;

public class RobotPosition {
    int x;
    int y;
    double heading;

    Boolean isTurn;

    //Initializers
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

    RobotPosition(int x, int y, double heading) {
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

        if (!(heading >= 0 && heading <= 360)) {
            throw new IllegalArgumentException("Heading value is outside 360 degrees");
        } else {
            this.heading = heading;
        }
    }

    //TODO: test actual headings
    //I have no idea if it works. According to the datasheet the heading increases clockwise from 0 to 360, but I do not remember it that way
    //Will have to test

    public double getRelativeHeading(RobotPosition nextPosition) {
        double relativeHeading = 0;

        switch (nextPosition.x - this.x) {
            case 1:
                switch (nextPosition.y - this.y) {
                    case 1:
                        relativeHeading = 45;
                        break;
                    case 0:
                        relativeHeading = 90;
                        break;
                    case -1:
                        relativeHeading = 135;
                        break;
                    default:
                        throw new IllegalArgumentException("Positions not adjacent");
                }
                break;
            case 0:
                switch (nextPosition.y - this.y) {
                    case 1:
                        relativeHeading = 0;
                        break;
                    case 0:
                        throw new IllegalArgumentException("Is current position");
                    case -1:
                        relativeHeading = 180;
                        break;
                    default:
                        throw new IllegalArgumentException("Positions not adjacent");
                }
                break;
            case -1:
                switch (nextPosition.y - this.y) {
                    case 1:
                        relativeHeading = 315;
                        break;
                    case 0:
                        relativeHeading = 270;
                        break;
                    case -1:
                        relativeHeading = 225;
                        break;
                    default:
                        throw new IllegalArgumentException("Positions not adjacent");
                }
                break;
        }
        return relativeHeading;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getHeading() {
        return heading;
    }

    public void setHeading(double heading) {
        this.heading = heading;
    }

    public Boolean getTurn() {
        return isTurn;
    }

    public void setTurn(Boolean turn) {
        isTurn = turn;
    }
}
