package org.firstinspires.ftc.teamcode.Team358;

import static java.lang.Math.sqrt;

public class AutoD358_Blue extends AutoEngine358 {
    private double POWER = 1;
    private boolean done = false;

    public void runOpMode() throws InterruptedException {

        initialize(new RobotPosition(11, 11, 315));
        token.setPosition(0);

        waitForStart();

        while (opModeIsActive() && !done) {

            unlatch();

            int cubePosition = lookForwardAndCheck();

            switch (cubePosition) {
                case 1:
                    turnTo(180, POWER);
                    forward(POWER, 42);
                    turnTo(90, POWER);
                    forward(POWER, -32);
                    dropToken();
                    turnTo(90, POWER);
                    forward(POWER, 74);
                    done = true;
                    break;
                case 2:
                    turnTo(225, POWER);
                    forward(POWER, 21 * 2 * sqrt(2));
                    turnTo(90, POWER);
                    dropToken();
                    forward(POWER, 84);
                    done = true;
                    break;
                case 3:
                    turnTo(270, POWER);
                    forward(POWER, 42);
                    turnTo(0, POWER);
                    forward(POWER, -32);
                    turnTo(0, POWER);
                    dropToken();
                    forward(POWER, 74);
                    done = true;
                    break;
            }
        }
    }

    public void dropToken() {
        token.setPosition(0.6);
    }

    public void unlatch() throws InterruptedException {
        runMotor(latch, 1, -10400);
        forward(1, 2);
        strafe(1, 12.5);
        turnTo(315, POWER);
        forward(1, -5.5);
        done = true;
    }
}
