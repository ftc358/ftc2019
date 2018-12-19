package org.firstinspires.ftc.teamcode.Team358.PAA;

import android.graphics.Point;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Team358.Encoders358;

@Autonomous
public class PAATest358 extends LinearOpMode {

    DcMotor lF;
    DcMotor lB;
    DcMotor rF;
    DcMotor rB;

    AutoEngine358 autoEngine = new AutoEngine358();

    public void runOpMode() throws InterruptedException {
        lF = hardwareMap.dcMotor.get("lF");
        lB = hardwareMap.dcMotor.get("lB");
        rF = hardwareMap.dcMotor.get("rF");
        rB = hardwareMap.dcMotor.get("rB");

        rF.setDirection(DcMotor.Direction.REVERSE);
        rB.setDirection(DcMotor.Direction.REVERSE);

        MoveAction forwardTest = new MoveAction(new Point(1, 1), new Runnable() {
            public void run() {
                Encoders358.Forward(lF, lB, rF, rB, 0.25, 10);
            }
        });


        MoveAction backwardTest = new MoveAction(new Point(1, 1), new Runnable() {
            public void run() {
                Encoders358.Forward(lF, lB, rF, rB, 0.25, -20);
            }
        });


        autoEngine.addRobotAction(forwardTest);
        autoEngine.addRobotAction(backwardTest);
        autoEngine.runRobotActions();
    }
}
