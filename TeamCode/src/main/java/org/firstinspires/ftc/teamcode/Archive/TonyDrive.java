package archive;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
@TeleOp

public class TonyDrive extends OpMode{

    DcMotor leftMotor;
    DcMotor rightMotor;

    public void init(){
        leftMotor=hardwareMap.dcMotor.get("lf");
        rightMotor=hardwareMap.dcMotor.get("rf");
    }

    public void loop(){
        leftMotor.setPower(gamepad1.left_stick_y);
        rightMotor.setPower(-gamepad1.right_stick_y);
    }

}