package org.firstinspires.ftc.teamcode.Team358;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class Playground extends AutoEngine358 {

    private boolean done = false;

    @Override
    public void runOpMode() throws InterruptedException {
        initialize(new RobotPosition(11, -11, 225));
        latch.setPower(0);
        waitForStart();
        while (opModeIsActive() && !done) {
            runMotor(latch,1,-10400);
            forward(1,2);
            strafe(1,12.5);
            turn(new IMUTurner(calculateTurn(getAbsoluteCurrentHeading(),225), 1, _imu1, 1, null), true, true);
            forward(1,-5.5);
            done = true;
        }
    }
}
