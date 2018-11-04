package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.util.ArrayList;

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

        ArrayList<DcMotor> motorArrayList = new ArrayList<DcMotor>();
        motorArrayList.add(lF);
        motorArrayList.add(lB);
        motorArrayList.add(rF);
        motorArrayList.add(rB);
        lF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        state358 = state.EXTEND;
        waitForStart();

        while (opModeIsActive()) {

            telemetry.addData("Going into state: ", state358);
            telemetry.update();

            switch(state358) {

                case EXTEND:

                    Encoders.Forward(motorArrayList, 0.5, 500);
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