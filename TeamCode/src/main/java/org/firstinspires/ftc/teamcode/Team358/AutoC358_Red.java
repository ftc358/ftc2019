package org.firstinspires.ftc.teamcode.Team358;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import static java.lang.Math.sqrt;

@Autonomous
public class AutoC358_Red extends AutoEngine358 {
    private double POWER = 1;
    private boolean done = false;

    public void runOpMode() throws InterruptedException {

        //TODO: change this! actually unlatching from lander should be 135
        initialize(new RobotPosition(11, -11, 225));

        token.setPosition(0);

        waitForStart();

        while (opModeIsActive() && !done) {

            //TODO: unlatch & go to starting position

            int cubePosition = lookForwardAndCheck();

            telemetry.addData("cube position", cubePosition);
            telemetry.addData("current absolute heading", getAbsoluteCurrentHeading());
            telemetry.update();

            switch (cubePosition) {
                case 1:
                    turn(new IMUTurner(calculateTurn(getAbsoluteCurrentHeading(),90), POWER, _imu1, 1, null), true, true);
                    forward(POWER, 28);
                    turn(new IMUTurner(calculateTurn(getAbsoluteCurrentHeading(),45), POWER, _imu1, 1, null), true, true);
                    forward(POWER,7*2*sqrt(2));
                    turn(new IMUTurner(calculateTurn(getAbsoluteCurrentHeading(),180), POWER, _imu1, 1, null), true, true);
                    forward(POWER,-60);
                    turn(new IMUTurner(calculateTurn(getAbsoluteCurrentHeading(),180), POWER, _imu1, 1, null), true, true);
                    token.setPosition(0.6);
                    forward(POWER,76);
                    done = true;
                    break;
                case 2:
                    turn(new IMUTurner(calculateTurn(getAbsoluteCurrentHeading(),135), POWER, _imu1, 1, null), true, true);
                    forward(POWER,6*2*sqrt(2));
                    forward(POWER,-4*2*sqrt(2));
                    turn(new IMUTurner(calculateTurn(getAbsoluteCurrentHeading(),45), POWER, _imu1, 1, null), true, true);
                    forward(POWER,18*2*sqrt(2));
                    turn(new IMUTurner(calculateTurn(getAbsoluteCurrentHeading(),180), POWER, _imu1, 1, null), true, true);
                    forward(POWER,-44);
                    turn(new IMUTurner(calculateTurn(getAbsoluteCurrentHeading(),180), POWER, _imu1, 1, null), true, true);
                    token.setPosition(0.6);
                    forward(POWER,76);
                    done = true;
                    break;
                case 3:
                    turn(new IMUTurner(calculateTurn(getAbsoluteCurrentHeading(),180), POWER, _imu1, 1, null), true, true);
                    forward(POWER,24);
                    forward(POWER,-12);
                    turn(new IMUTurner(calculateTurn(getAbsoluteCurrentHeading(),45), POWER, _imu1, 1, null), true, true);
                    forward(POWER,21*2*sqrt(2));
                    turn(new IMUTurner(calculateTurn(getAbsoluteCurrentHeading(),180), POWER, _imu1, 1, null), true, true);
                    forward(POWER,-44);
                    turn(new IMUTurner(calculateTurn(getAbsoluteCurrentHeading(),180), POWER, _imu1, 1, null), true, true);
                    token.setPosition(0.6);
                    forward(POWER,76);
                    done = true;
                    break;
            }
        }
    }
}
