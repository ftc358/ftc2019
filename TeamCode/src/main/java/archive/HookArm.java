package archive;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Disabled
@TeleOp

public class HookArm extends OpMode{

    private DcMotor hookArmMotor;

    private double power = 0.25;
    private int target = 0;
    private double runPower = power;
    private int diff = 0;

    public void init() { //

        hookArmMotor = hardwareMap.dcMotor.get("hookArmMotor");

        hookArmMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        hookArmMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        hookArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    public void loop() { //

        if (gamepad1.a) {
            target = target + 200;
            while (gamepad1.a) {
                if  (target > 2000) {
                    target = 2000;
                }
            }
        }
        else if (gamepad1.b) { //
            target = target - 200;
            while (gamepad1.b) {
                if  (target < 0) {
                    target = 0;
                }
            }
        }
        else if (gamepad1.x) {
            target = 0;
        }
        else if (gamepad1.y) {
            target = 1800;
        }

        hookArmMotor.setTargetPosition(target);

        if (hookArmMotor.getCurrentPosition()!=target) {
            hookArmMotor.setPower(runPower);
        }

        while (hookArmMotor.isBusy()) { //

            if (gamepad1.a) {
                target = target + 200;
                while (gamepad1.a) {
                    if  (target > 2000) {
                        target = 2000;
                    }
                }
            }
            else if (gamepad1.b) {
                target = target - 200;
                while (gamepad1.b) {
                    if  (target < 0) {
                        target = 0;
                    }
                }
            }

            hookArmMotor.setTargetPosition(target);

            diff = Math.abs(hookArmMotor.getCurrentPosition() - target);

            if (diff > 400) {
                runPower = power;
            }
            else {
                runPower = power * Math.pow(1 - Math.pow(diff/400,2),1/2);
            }

            hookArmMotor.setPower(runPower);

        }

        hookArmMotor.setPower(0);

    }

}