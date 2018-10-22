package org.firstinspires.ftc.teamcode;

        import com.qualcomm.robotcore.eventloop.opmode.OpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp

public class CeciliaDrive extends OpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;

    public void init(){
        leftMotor=hardwareMap.dcMotor.get("left_motor");
        rightMotor=hardwareMap.dcMotor.get("right_motor");

        rightMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    public void loop(){
        leftMotor.setPower((Math.abs(gamepad1.left_stick_y)*gamepad1.left_stick_y)/8);
        rightMotor.setPower((Math.abs(gamepad1.right_stick_y)*gamepad1.right_stick_y)/8);
    }
    //back of the motor facing right: positive
    //not exp
}