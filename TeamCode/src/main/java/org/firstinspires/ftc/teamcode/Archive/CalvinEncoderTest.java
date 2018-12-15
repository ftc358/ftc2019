package org.firstinspires.ftc.teamcode.Archive;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.ArrayList;

//@Autonomous
@Disabled
public class CalvinEncoderTest extends LinearOpMode {
    DcMotor lF;
    DcMotor lB;
    DcMotor rF;
    DcMotor rB;

    public void runOpMode() throws InterruptedException {
        //initialize motor
        lF = hardwareMap.dcMotor.get("lF");
        lB = hardwareMap.dcMotor.get("lB");
        rF = hardwareMap.dcMotor.get("rF");
        rB = hardwareMap.dcMotor.get("rB");

        rF.setDirection(DcMotor.Direction.REVERSE);
        rB.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        ArrayList<DcMotor> motorArrayList = new ArrayList<DcMotor>();
        motorArrayList.add(lF);
        motorArrayList.add(lB);
        motorArrayList.add(rF);
        motorArrayList.add(rB);

        CalvinEncoders.Forward(motorArrayList, 0.5, 1200);
    }
}
