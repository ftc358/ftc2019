package org.firstinspires.ftc.teamcode.Team358.AutoLegacy;

interface StopperIF {

    void start();

    /**
     * Returns true if we should keep going
     *
     * @return
     */
    boolean keepGoing(int position);

    void finish();
}