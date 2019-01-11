package org.firstinspires.ftc.teamcode.Team358;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous
public class Auto_Unlatch_Temp extends LinearOpMode {
    DcMotor latch;

    DcMotor lF;
    DcMotor lB;
    DcMotor rF;
    DcMotor rB;

    public void runOpMode() throws InterruptedException {
        latch = hardwareMap.dcMotor.get("lF");
        lF = hardwareMap.dcMotor.get("lF");
        lB = hardwareMap.dcMotor.get("lB");
        rF = hardwareMap.dcMotor.get("rF");
        rB = hardwareMap.dcMotor.get("rB");

        rF.setDirection(DcMotor.Direction.REVERSE);
        rB.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {
            Encoders358.runWithTicks1(latch, 1, 1000);
            //TODO: different value!!!
            Encoders358.Forward(lF, lB, rF, rB, 0.25, 3);
            Encoders358.runWithTicks1(latch, 1, -1000);
        }

    }
}
