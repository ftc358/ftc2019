package org.firstinspires.ftc.teamcode.Team358.AutoLegacy;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class IMUTurner extends DefaultStopper implements TurnerIF {
    double _power;
    double _degrees;
    BNO055IMU _imu;
    double _initialHeading;
    double _maxError;

    double _slowDifference = 25;
    double _rampDown = 3;
    double _minFactor = .15;

    IMUTurner(double degrees, double power, BNO055IMU imu, double maxError, StopperIF stopper) {
        super(stopper);

        _degrees = -degrees;
        _power = power;
        _imu = imu;
        _maxError = maxError;
    }

    IMUTurner(double degrees, double power, BNO055IMU imu, double maxError, StopperIF stopper,
              double slowDifference, double rampDown, double minFactor) {
        this(degrees, power, imu, maxError, stopper);

        _slowDifference = slowDifference;
        _rampDown = rampDown;
        _minFactor = minFactor;
    }

    @Override
    public void start() {
        super.start();

        RobotLog.d("IMUTurner::start()::A");

        Orientation angles = _imu.getAngularOrientation().toAxesReference(AxesReference.INTRINSIC).toAxesOrder(AxesOrder.ZYX).toAngleUnit(AngleUnit.DEGREES);

        RobotLog.d("IMUTurner::angles::" + angles);

        _initialHeading = angles.firstAngle;

        RobotLog.d("IMUTurner::start()::B");
    }

    @Override
    public double getPower() {
        return _power;
    }

    @Override
    public double getScaleFactor() {

        Orientation angles = _imu.getAngularOrientation().toAxesReference(AxesReference.INTRINSIC).toAxesOrder(AxesOrder.ZYX).toAngleUnit(AngleUnit.DEGREES);

        double absHeading = angles.firstAngle;

        double heading = absHeading - _initialHeading;
        heading += (heading > 180) ? -360 : (heading < -180) ? 360 : 0;

        double difference = _degrees - heading;
        difference += (difference > 180) ? -360 : (difference < -180) ? 360 : 0;

        if (Math.abs(difference) < _maxError) {
            return Double.NaN;
        } else {
            double direction = difference > 0 ? 1 : -1;

            double factor;

            if (Math.abs(difference) > _slowDifference) {
                // Go full speed if we are more than 10 degrees different from target angle
                factor = 1;
            } else {
                factor = (1.0 - _minFactor) * Math.pow(Math.abs(difference) / _slowDifference, _rampDown) + _minFactor;
            }

            return direction * factor;
        }
    }
}
