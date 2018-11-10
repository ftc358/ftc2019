package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Autonomous359 extends LinearOpMode {

DcMotor leftMotor;
DcMotor rightMotor;
DcMotor leftLauch;
DcMotor rightLaunch;
DcMotor Intake;

    public void runOpMode() throws InterruptedException {

    leftMotor=hardwareMap.dcMotor.get("leftMotor");
    rightMotor=hardwareMap.dcMotor.get("rightMotor");
    leftLauch=hardwareMap.dcMotor.get("leftLaunch");
    rightLaunch=hardwareMap.dcMotor.get("rightLaunch");
    Intake=hardwareMap.dcMotor.get("Intake");

    rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    rightLaunch.setDirection(DcMotorSimple.Direction.REVERSE);

    while (opModeIsActive()){

        Encoders359.Forward(leftLauch, rightLaunch, 0.5,8888);  //Launch down
        Encoders359.Turn(leftMotor,rightMotor,0.2,1280);        //Turn to get rid of the hook
        Encoders359.Forward(leftMotor,rightMotor,0.2,2560);     //Go forward
        Encoders359.Turn(leftMotor,rightMotor,0.2,1280);        //Turn again
        Encoders359.Forward(leftMotor,rightMotor,0.2,2560);     //Go forward till the edge
        Encoders359.Turn(leftMotor,rightMotor,0.2,888);         //Turn to be parallel to the edge
        Encoders359.Forward(leftMotor,rightMotor,0.2,2560);     //Go to the Depot

        Encoders359.Intake(Intake,0.2,666);                     //Intake down
        Encoders359.Intake(Intake,0.2,666);                     //Intake up

        Encoders359.Forward(leftMotor,rightMotor,0.2,6666);     //Go back from the depot
        Encoders359.Turn(leftMotor,rightMotor,0.2,1280);        //Be ready to go back
        Encoders359.Forward(leftMotor,rightMotor,0.2,8888);     //Go back and crush the jewels
        }
    }
}
