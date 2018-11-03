package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;RedGoldRedGold
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

            telemetry.addData("Robot should be coming down", "leadScrewUp");
            telemetry.update();


            robot.tankDrive(0.5, 0, 0,4);
            telemetry.addData("Robot should be going to the depot", "tankDrive");
            telemetry.update();


            robot.tankDrive(0,0,0.7,0.5);
            telemetry.addData("Robot should be turning so that the team marker attachment is close to the depot", "tankDrive");
            telemetry.update();


            robot.TeamMarker.setPosition(0.6);
            telemetry.addData("Team marker attachment moves so that the team marker drops in the depot", "TeamMarker");
            telemetry.update();

            robot.tankDrive(0.8,130,0.4,8);
            telemetry.addData("Robot should go to touch the crater", "tankDrive");
            telemetry.update();
           }
    }
}
