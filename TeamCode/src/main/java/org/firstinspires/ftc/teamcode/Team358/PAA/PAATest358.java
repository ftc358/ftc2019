package org.firstinspires.ftc.teamcode.Team358.PAA;

import android.graphics.Point;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Team358.Encoders358;

@Autonomous
public class PAATest358 extends LinearOpMode {

    DcMotor lF;
    DcMotor lB;
    DcMotor rF;
    DcMotor rB;

    CRServo intake;

    AutoEngine358 autoEngine = new AutoEngine358();

    public void runOpMode() throws InterruptedException {
        lF = hardwareMap.dcMotor.get("lF");
        lB = hardwareMap.dcMotor.get("lB");
        rF = hardwareMap.dcMotor.get("rF");
        rB = hardwareMap.dcMotor.get("rB");

        rF.setDirection(DcMotor.Direction.REVERSE);
        rB.setDirection(DcMotor.Direction.REVERSE);

        MoveAction forwardTest = new MoveAction(new Point(1, 2), new Runnable() {
            public void run() {
                Encoders358.Forward(lF, lB, rF, rB, 0.25, 10);
            }
        });


        MoveAction backwardTest = new MoveAction(new Point(1, 3), new Runnable() {
            public void run() {
                Encoders358.Forward(lF, lB, rF, rB, 0.25, -20);
            }
        });

        ScoreAction servoTest = new ScoreAction(true, new Runnable() {
            public void run() {
                intake.setPower(1);
            }
        });


        autoEngine.addRobotAction(forwardTest);
        autoEngine.addRobotAction(servoTest);
        autoEngine.addRobotAction(backwardTest);
        telemetry.update();
        autoEngine.runRobotActions();
    }
}
