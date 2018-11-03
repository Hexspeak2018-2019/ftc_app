package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "RedSilver", group = "Linear")

public class RedSilver extends LinearOpMode {


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


            robot.tankDrive(0.5, 0, 0,1);
            telemetry.addData("Robot goes to depot", "tankDrive");
            telemetry.update();


            robot.tankDrive(0,0,0.7,2);
            telemetry.addData("Robot faces depot", "tankDrive");
            telemetry.update();

            robot.tankDrive(0.3,50,0.7,5);
            telemetry.addData("Robot goes to depot", "tankDrive");
            telemetry.update();

            //robot should be turing so that thet eam marker attachment is close to the depot
            robot.tankDrive(0.3,50,0.7,5);
            telemetry.addData("TM Attachment close to depot", "tankDrive");
            telemetry.update();

            //change values
            robot.TeamMarker.setPosition(0.6);
            telemetry.addData("TM drops in depot", "TeamMarker");
            telemetry.update();

            robot.tankDrive(0.8,130,0.4,8);
            telemetry.addData("Robot touches crater", "tankDrive");
            telemetry.update();
           }
    }
}
