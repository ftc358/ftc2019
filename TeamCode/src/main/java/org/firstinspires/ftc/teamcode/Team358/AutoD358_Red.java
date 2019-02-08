package org.firstinspires.ftc.teamcode.Team358;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;

@Autonomous
public class AutoD358_Red extends AutoEngine358 {
    private double POWER = 1;
    private boolean done = false;

    public void runOpMode() throws InterruptedException {

        initialize(new RobotPosition(11, 11, 135));
        token.setPosition(0);

        waitForStart();

        while (opModeIsActive() && !done) {

            //TODO: unlatch & go to starting position

            switch (lookForwardAndCheck()) {
                case 1:
                    turn(new IMUTurner(calculateTurn(getAbsoluteCurrentHeading(),0), POWER, _imu1, 1, null), true, true);
                    forward(POWER, 42);
                    turn(new IMUTurner(calculateTurn(getAbsoluteCurrentHeading(),270), POWER, _imu1, 1, null), true, true);
                    forward(POWER, -32);
                    dropToken();
                    turn(new IMUTurner(calculateTurn(getAbsoluteCurrentHeading(),270), POWER, _imu1, 1, null), true, true);
                    forward(POWER, 74);
                    done = true;
                    break;
                case 2:
                    turn(new IMUTurner(calculateTurn(getAbsoluteCurrentHeading(),45), POWER, _imu1, 1, null), true, true);
                    forward(POWER, 21*2*sqrt(2));
                    turn(new IMUTurner(calculateTurn(getAbsoluteCurrentHeading(),270), POWER, _imu1, 1, null), true, true);
                    dropToken();
                    forward(POWER, 84);
                    done = true;
                    break;
                case 3:
                    turn(new IMUTurner(calculateTurn(getAbsoluteCurrentHeading(),90), POWER, _imu1, 1, null), true, true);
                    forward(POWER, 42);
                    turn(new IMUTurner(calculateTurn(getAbsoluteCurrentHeading(),180), POWER, _imu1, 1, null), true, true);
                    forward(POWER, -32);
                    turn(new IMUTurner(calculateTurn(getAbsoluteCurrentHeading(),180), POWER, _imu1, 1, null), true, true);
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
}
