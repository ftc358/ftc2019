package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous
public class AutoRedC358 extends LinearOpMode{

    DcMotor lF;
    DcMotor lB;
    DcMotor rF;
    DcMotor rB;
    state state358;

    enum state {

        EXTEND, DETECT, KNOCK, STOP

    }

    public void runOpMode() throws InterruptedException {

        lF = hardwareMap.dcMotor.get("lF");
        lB = hardwareMap.dcMotor.get("lB");
        rF = hardwareMap.dcMotor.get("rF");
        rB = hardwareMap.dcMotor.get("rB");

        rF.setDirection(DcMotor.Direction.REVERSE);
        rB.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {

            telemetry.addData("Going into state: ", state358);
            telemetry.update();

            switch(state358) {

                case EXTEND:

                    // do something
                    state358 = state.DETECT;
                    break;
                case DETECT:

                    // do something
                    state358 = state.KNOCK;
                    break;
                case KNOCK:

                    // do something
                    state358 = state.STOP;
                    break;

                case STOP:

                    lF.setPower(0);
                    lB.setPower(0);
                    rF.setPower(0);
                    rB.setPower(0);
                    sleep(30000);

            }
        }
    }
}