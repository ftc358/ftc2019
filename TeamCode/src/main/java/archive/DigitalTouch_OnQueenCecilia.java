package archive;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
@TeleOp(name = "Sensor: Digital touch", group = "Sensor")

public class DigitalTouch_OnQueenCecilia extends LinearOpMode{

    DigitalChannel digitalTouch;  // Hardware Device Object

    @Override
    public void runOpMode() {
        DcMotor leftMotor=hardwareMap.dcMotor.get("left_motor");
        DcMotor rightMotor=hardwareMap.dcMotor.get("right_motor");

        // get a reference to our digitalTouch object.
        digitalTouch = hardwareMap.get(DigitalChannel.class, "sensor_digital");

        // set the digital channel to input.
        digitalTouch.setMode(DigitalChannel.Mode.INPUT);

        // wait for the start button to be pressed.
        waitForStart();

        // while the op mode is active, loop and read the light levels.
        // Note we use opModeIsActive() as our loop condition because it is an interruptible method.
        while (opModeIsActive()) {

            // send the info back to driver station using telemetry function.
            // if the digital channel returns true it's HIGH and the button is unpressed.
            if (digitalTouch.getState()) {
                telemetry.addData("Digital Touch", "Is Not Pressed");
                leftMotor.setPower(0.3);
                rightMotor.setPower(0.3);
            } else {
                telemetry.addData("Digital Touch", "Is Pressed");
                leftMotor.setPower(0);
                rightMotor.setPower(0);
            }

            telemetry.update();
        }
    }
}