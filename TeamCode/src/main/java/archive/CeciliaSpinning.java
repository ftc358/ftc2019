package archive;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
@TeleOp

public class CeciliaSpinning extends OpMode{
    DcMotor motor;
    //final static double maxSpeed =1;

    public void init(){
        motor=hardwareMap.dcMotor.get("motor");
    }

    public void loop(){
        if(gamepad1.a){
            motor.setPower(0.3);
            motor.setTargetPosition(96);
        }
        else{
            motor.setPower(0);
        }
    }
}