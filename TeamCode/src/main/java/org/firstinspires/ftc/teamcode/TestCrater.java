package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "T-direction", group = "Linear")

public class TestCrater extends LinearOpMode {

    HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();
    GoldDetection detector = new GoldDetection();


    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap, telemetry);
        detector.activateTF(hardwareMap);
        sleep(700);
        telemetry.addData("Camera is :", "Activated, Ready to Start Recognition");
        telemetry.update();

        waitForStart();


        while (opModeIsActive()) {

            int position = detector.detectObject2(telemetry);
            detector.shutdownTF();
            telemetry.addData("Gold Mineral Position is", position);
            telemetry.update();

            switch (position) {
                case 0: // if ( position == 0) /left
                    //if unknown, assume center and continue
                    //robot.tankDrive2(1, 180, 0, 3, this);
                    telemetry.addData("Gold Mineral Position 0", "Left");
                    telemetry.update();
                  //  robot.leadScrewUp(36, 1, 18, this);
                    robot.tankDrivecs(.4, 90, 3, 10, this);
                    robot.tankDrivecs(.5, 160, 60, 10, this);
                    robot.tankDrivecs(.5,340,20,10,this);
                   /* robot.tankDrive(.5, 0, 0, .9, this);
                    robot.tankDrive(.5, 90, 0, 6.9, this);
                    robot.tankDrive(1, 45, 0, 1.2, this);
                    sleep(500);
                    robot.TeamMarker.setPosition(-100);
                    sleep(500);
                    robot.tankDrive(1, 225, 0, 2.5, this);*/
                    break;

                case 1: //center
                    //robot.tankDrive2(1, 90, 0, 3, this);
                   // robot.leadScrewUp(36, 1, 18, this);
                    robot.tankDrivecs(.4, 90, 3, 10, this);
                    robot.tankDrivecs(.5, 185, 60, 10, this);
                    robot.tankDrivecs(.5,10,20,10,this);
                   /* robot.tankDrive(.5, 0, 0, .9, this);
                    robot.tankDrive(.5, 90, 0, 5, this);
                    robot.tankDrive(1, 45, 0, 1, this);
                    sleep(500);
                    robot.TeamMarker.setPosition(-100);
                    sleep(500);
                    robot.tankDrive(1, 225, 0, 2.5, this);*/
                    telemetry.addData("Gold Mineral Position 1", "Center");
                    telemetry.update();
                    break;
                case 2: //right
                  //  robot.leadScrewUp(36, 1, 18, this);
                    robot.tankDrivecs(.5, 90, 3, 10, this);
                    robot.tankDrivecs(.5, 215, 60,10, this);
                    robot.tankDrivecs(.5,40,20,10,this);
                   /* robot.tankDrive(.5, 0, 0, 1, this);
                    robot.tankDrive(.5, 90, 0, 2.43, this);
                    robot.tankDrive(1, 45, 0, 1.3, this);*/
                    sleep(500);
                    //robot.TeamMarker.setPosition(-100);
                    sleep(500);
                    telemetry.addData("Gold Mineral Position 2", "Right");
                    telemetry.update();
                    //robot.tankDrive2(1, 45, 0, 3, this);
                    break;
                case -1: // center / unknown
                    //robot.tankDrive2(1, 90, 0, 3, this);
                    // robot.leadScrewUp(36, 1, 18, this);
                    robot.tankDrivecs(.4, 90, 3, 10, this);
                    robot.tankDrivecs(.5, 185, 60, 10, this);
                    robot.tankDrivecs(.5,10,20,10,this);
                   /* robot.tankDrive(.5, 0, 0, .9, this);
                    robot.tankDrive(.5, 90, 0, 5, this);
                    robot.tankDrive(1, 45, 0, 1, this);
                    sleep(500);
                    robot.TeamMarker.setPosition(-100);
                    sleep(500);
                    robot.tankDrive(1, 225, 0, 2.5, this);*/
                    telemetry.addData("Gold Mineral Position 1", "Center");
                    telemetry.update();
                    break;
            }
            break;

        }
    }
}