//package org.firstinspires.ftc.teamcode.Team358.PAA;
//
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.util.RobotLog;
//
//import org.firstinspires.ftc.teamcode.Team358.Robot358Main;
//
////@TeleOp(name = "TestTurner", group = "test")
//@Disabled
//public class TestTurner extends Robot358Main {
//    @Override
//    public void runOpMode() throws InterruptedException {
//        telemetry.addLine("Initializing Motors and Sensors");
//        telemetry.update();
//
//        initialize();
//
//        telemetry.addLine("Ready");
//        telemetry.update();
//
//        waitForStart();
//
//        double power = .5;
//        boolean runUsingEncoders = true;
//
//        RobotLog.d("TestTurner::180");
//
//        double lastHeading = getCurrentHeading();
//
//        turn(new IMUTurner(180, power, _imu1, .25, null), runUsingEncoders, true);
//
//        sleep(1000);
//
//        double currentHeading = getCurrentHeading();
//        double headingChange = currentHeading - lastHeading;
//        RobotLog.d("TestTurner::headingChange: " + headingChange);
//
//        RobotLog.d("TestTurner::90");
//
//        lastHeading = getCurrentHeading();
//
//        turn(new IMUTurner(90, power, _imu1, .25, null), runUsingEncoders, true);
//
//        sleep(1000);
//
//        currentHeading = getCurrentHeading();
//        headingChange = currentHeading - lastHeading;
//        RobotLog.d("TestTurner::headingChange: " + headingChange);
//
//        RobotLog.d("TestTurner::45");
//
//        lastHeading = getCurrentHeading();
//
//        turn(new IMUTurner(45, power, _imu1, .25, null), runUsingEncoders, true);
//
//        currentHeading = getCurrentHeading();
//        headingChange = currentHeading - lastHeading;
//        RobotLog.d("TestTurner::headingChange: " + headingChange);
//
//        sleep(1000);
//
//        RobotLog.d("TestTurner::10");
//
//        lastHeading = getCurrentHeading();
//
//        turn(new IMUTurner(10, power, _imu1, .25, null), runUsingEncoders, true);
//
//        currentHeading = getCurrentHeading();
//        headingChange = currentHeading - lastHeading;
//        RobotLog.d("TestTurner::headingChange: " + headingChange);
//
//        sleep(1000);
//
//        RobotLog.d("TestTurner::5");
//
//        lastHeading = getCurrentHeading();
//
//        turn(new IMUTurner(5, power, _imu1, .25, null), runUsingEncoders, true);
//
//        currentHeading = getCurrentHeading();
//        headingChange = currentHeading - lastHeading;
//        RobotLog.d("TestTurner::headingChange: " + headingChange);
//
//        sleep(1000);
//
//        RobotLog.d("TestTurner::1");
//
//        lastHeading = getCurrentHeading();
//
//        turn(new IMUTurner(1, power, _imu1, .25, null), runUsingEncoders, true);
//
//        currentHeading = getCurrentHeading();
//        headingChange = currentHeading - lastHeading;
//        RobotLog.d("TestTurner::headingChange: " + headingChange);
//
//        turn(new IMUTurner(-180, power, _imu1, .25, null), runUsingEncoders, true);
//
//        sleep(1000);
//
//        currentHeading = getCurrentHeading();
//        headingChange = currentHeading - lastHeading;
//        RobotLog.d("TestTurner::headingChange: " + headingChange);
//
//        RobotLog.d("TestTurner::90");
//
//        lastHeading = getCurrentHeading();
//
//        turn(new IMUTurner(-90, power, _imu1, .25, null), runUsingEncoders, true);
//
//        sleep(1000);
//
//        currentHeading = getCurrentHeading();
//        headingChange = currentHeading - lastHeading;
//        RobotLog.d("TestTurner::headingChange: " + headingChange);
//
//        RobotLog.d("TestTurner::45");
//
//        lastHeading = getCurrentHeading();
//
//        turn(new IMUTurner(-45, power, _imu1, .25, null), runUsingEncoders, true);
//
//        currentHeading = getCurrentHeading();
//        headingChange = currentHeading - lastHeading;
//        RobotLog.d("TestTurner::headingChange: " + headingChange);
//
//        sleep(1000);
//
//        RobotLog.d("TestTurner::10");
//
//        lastHeading = getCurrentHeading();
//
//        turn(new IMUTurner(-10, power, _imu1, .25, null), runUsingEncoders, true);
//
//        currentHeading = getCurrentHeading();
//        headingChange = currentHeading - lastHeading;
//        RobotLog.d("TestTurner::headingChange: " + headingChange);
//
//        sleep(1000);
//
//        RobotLog.d("TestTurner::5");
//
//        lastHeading = getCurrentHeading();
//
//        turn(new IMUTurner(-5, power, _imu1, .25, null), runUsingEncoders, true);
//
//        currentHeading = getCurrentHeading();
//        headingChange = currentHeading - lastHeading;
//        RobotLog.d("TestTurner::headingChange: " + headingChange);
//
//        sleep(1000);
//
//        RobotLog.d("TestTurner::1");
//
//        lastHeading = getCurrentHeading();
//
//        turn(new IMUTurner(-1, power, _imu1, .25, null), runUsingEncoders, true);
//
//        currentHeading = getCurrentHeading();
//        headingChange = currentHeading - lastHeading;
//        RobotLog.d("TestTurner::headingChange: " + headingChange);
//
//    }
//
//}
