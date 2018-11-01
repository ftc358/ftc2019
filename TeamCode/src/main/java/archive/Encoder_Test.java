package archive;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Encoders;

import java.util.ArrayList;

@Disabled
@Autonomous

public class Encoder_Test extends LinearOpMode{

    DcMotor leftMotor;
    DcMotor rightMotor;

    public void runOpMode() throws InterruptedException
    {
        //initialize motors
        leftMotor = hardwareMap.dcMotor.get("leftMotor");
        rightMotor = hardwareMap.dcMotor.get("rightMotor");

        rightMotor.setDirection(DcMotor.Direction.REVERSE);

        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        ArrayList<DcMotor> motorArrayList = new ArrayList<DcMotor>();
        motorArrayList.add(leftMotor);
        motorArrayList.add(rightMotor);

        Encoders.Forward(motorArrayList,0.5, 1200);
        Encoders.Turn(motorArrayList,0.5, 495);
    }
}