package org.firstinspires.ftc.teamcode.Team358;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@TeleOp
public class TestForThreeMotors extends OpMode {
    DcMotor motor1;
    DcMotor motor3;
    DcMotor motor2;

    public void init() {
        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");
        motor3 = hardwareMap.dcMotor.get("motor3");
    }

    public void loop() {
        motor1.setPower(gamepad1.left_stick_y);
        motor2.setPower(gamepad1.right_stick_y);
        if (gamepad1.x) {
            motor3.setPower(0.6);
        } else if (gamepad1.y) {
            motor3.setPower(-0.6);
        }
        else{
            motor3.setPower(0);
        }
    }
}

