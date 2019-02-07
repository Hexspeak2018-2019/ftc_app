package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Bob23", group = "bob")

public class TESTLEADSCREW extends LinearOpMode {

    HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();


    @Override
    public void runOpMode() throws InterruptedException {
robot.init(hardwareMap,telemetry);
        waitForStart();

        while (opModeIsActive()) {
            robot.leadScrewUp(2,1,3,this);
        }
    }
}