package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "RedGold", group = "Linear")

public class RedGold extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();

        waitForStart();

        while (opModeIsActive()) {

            robot.leadScrewUp(90,0.7, 15);
            telemetry.addData("Robot lowers", "leadScrewUp");
            telemetry.update();


            robot.tankDrive(0.5, 0, 0,4);
            telemetry.addData("Robot goes to depot", "tankDrive");
            telemetry.update();


            robot.tankDrive(0,0,0.7,1);
            telemetry.addData("Robot faces depot", "tankDrive");
            telemetry.update();


            robot.TeamMarker.setPosition(0.6);
            telemetry.addData("TM Attachment drops in depot", "TeamMarker");
            telemetry.update();

            robot.tankDrive(0.8,130,0.4,8);
            telemetry.addData("Robot touches crater", "tankDrive");
            telemetry.update();
           }
    }
}
