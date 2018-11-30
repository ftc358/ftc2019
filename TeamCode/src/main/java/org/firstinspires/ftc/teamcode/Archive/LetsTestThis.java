package org.firstinspires.ftc.teamcode.Archive;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Encoders;

@Disabled
@Autonomous


public class LetsTestThis extends LinearOpMode {


    DcMotor lF;

    DcMotor lB;
    DcMotor rF;
    DcMotor rB;

    state state358;

    public void runOpMode() throws InterruptedException {

        lF = hardwareMap.dcMotor.get("lF");
        lB = hardwareMap.dcMotor.get("lB");
        rF = hardwareMap.dcMotor.get("rF");

        rB = hardwareMap.dcMotor.get("rB");
        rF.setDirection(DcMotor.Direction.REVERSE);
        rB.setDirection(DcMotor.Direction.REVERSE);


//        ArrayList<DcMotor> motorArrayList = new ArrayList<DcMotor>();
//        motorArrayList.add(lF);
//        motorArrayList.add(lB);

//        motorArrayList.add(rF);

//        motorArrayList.add(rB);
        state358 = state.EXTEND;
        waitForStart();

        while (opModeIsActive()) {


            telemetry.addData("Going into state", state358);
            telemetry.update();
            switch (state358) {

                case EXTEND:
                    Encoders.Forward(lF, lB, rF, rB, 0.25, 1000);
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

    enum state {
        EXTEND, DETECT, KNOCK, STOP
    }


}

//Ignore the last lines (104-106)