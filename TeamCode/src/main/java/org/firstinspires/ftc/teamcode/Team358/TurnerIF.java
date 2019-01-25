package org.firstinspires.ftc.teamcode.Team358;

public interface TurnerIF extends StopperIF {
    /**
     * Return the power that should be used for the motors
     *
     * @return
     */
    double getPower();

    /**
     * Return a number between -1 and 1 for the amount it should turn
     *
     * @return
     */
    double getScaleFactor();
}

