package org.firstinspires.ftc.teamcode;

        import com.qualcomm.robotcore.eventloop.opmode.OpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp

public class spinning extends OpMode{
    DcMotor motor;
    final static double maxSpeed =1;

    public void init(){
        motor=hardwareMap.dcMotor.get("motor");
    }

    public void loop(){
        if(gamepad1.a){
            motor.setPower(maxSpeed);
        }
        else{
            motor.setPower(0);
        }
    }
}