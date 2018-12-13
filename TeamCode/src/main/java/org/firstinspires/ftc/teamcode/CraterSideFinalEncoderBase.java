package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "CraterSideC", group = "Linear")

public class CraterSideFinalEncoderBase extends LinearOpMode {

    HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();
    GoldDetection detector = new GoldDetection();

    @Override
    public void runOpMode() throws InterruptedException {
       robot.init(hardwareMap, telemetry);
        detector.activateTF(hardwareMap);
        sleep(700);
        telemetry.addData("Camera is :", "Activated, Ready to Start Recognition");
        telemetry.update();
        /*int position = detector.detectObject2(telemetry);
        telemetry.addData("Gold Mineral Position is", position);
        telemetry.update();*/
       // detector.shutdownTF();*/


        //robot.BucketServo.setPosition(0.48);
        waitForStart();

        while (opModeIsActive()) {

             int position = detector.detectObject2(telemetry);
            detector.shutdownTF();
            telemetry.addData("Gold Mineral Position is", position);
            telemetry.update();

            //telemetry.addData("# Object Detected", detector.updatedRecognitions.size());

            robot.leadScrewUp(36, 1, 18, this);


            switch (position) {//it works DONT CHANGE time: 18 sec left Not COMPLETE
                case 0: // if ( position == 0) /left
                    //if unknown, assume center and continue
                    //robot.tankDrive2(1, 180, 0, 3, this);
                    telemetry.addData("Gold Mineral Position 0", "Left");
                    telemetry.update();
                    //robot.leadScrewUp(36,1,18,this);
                    robot.tankDrivecs(.5, 90, 9, 10, this);
                    robot.tankDrivecs(1, 150, 32, 20, this);
                    robot.tankDrivecs(1, 350, 9, 10, this);
                    robot.tankDrivecs(1, 90, 33, 10, this);
                    robot.tankRotate(42,this);
                    robot.tankDrivecs(1, 90, 45, 10, this);
                    robot.BucketServo.setPosition(0.52);
                    sleep(2000);
                    robot.tankDrivecs(1, 266, 75, 10, this);
                    //robot.tankRotate(45,this);

                    /*robot.tankDrivecs(.5, 90, 9, 10, this);
                    robot.tankDrivecs(.5, 163, 60, 20, this);
                    robot.tankDrivecs(.5, 350, 9, 10, this);
                    robot.tankDrivecs(.5, 90, 100, 10, this);
                    robot.tankDrivecs(.5, 45, 90, 10, this);*/

                   /* robot.tankDrive(.5, 0, 0, .9, this);
                    robot.tankDrive(.5, 90, 0, 6.9, this);
                    robot.tankDrive(1, 45, 0, 1.2, this);
                    sleep(500);
                    robot.TeamMarker.setPosition(-100);
                    sleep(500);
                    robot.tankDrive(1, 225, 0, 2.5, this);*/
                    break;

                case 1: //center//it works DONT CHANGE time: 15 sec COMPLETE
                    telemetry.addData("Gold Mineral Position 1", "Center");
                    telemetry.update();
                    /*robot.tankDrivecs(.5, 90, 9, 10, this);
                    robot.tankDrivecs(.5, 180, 47, 20, this);
                    robot.tankDrivecs(.5, 0, 8, 10, this);
                    robot.tankDrivecs(.5, 90, 110, 10, this);
                    robot.tankDrivecs(.5, 45,90, 10, this);*/
                   // robot.leadScrewUp(36,1,18,this);
                    robot.tankDrivecs(.5, 90, 9, 10, this);
                    robot.tankDrivecs(1, 190, 25, 20, this);
                    robot.tankDrivecs(1, 0, 9, 10, this);
                    robot.tankDrivecs(1, 90, 60, 10, this);
                    robot.tankRotate(42,this);
                    robot.tankDrivecs(1, 88, 40, 10, this);
                    robot.BucketServo.setPosition(0.52);
                    sleep(2000);
                    robot.tankDrivecs(1, 266, 80, 10, this);
                    //robot.tankRotate(45,this);


                    /*sleep(500);
                    robot.TeamMarker.setPosition(-100);
                    sleep(500);
                    robot.tankDrive(1, 225, 0, 2.5, this);*/

                    break;
                case 2:
                    telemetry.addData("Gold Mineral Position 2", "Right");
                    telemetry.update();//right//it works DONT CHANGE time: 15 sec with out leadscrew COMPLETE
                    //robot.leadScrewUp(36,1,18,this);
                    robot.tankDrivecs(.5, 90, 9, 10, this);
                    robot.tankDrivecs(1, 217, 30, 20, this);
                    robot.tankDrivecs(1, 340, 10, 10, this);
                    robot.tankDrivecs(1, 90, 82, 10, this);
                    robot.tankRotate(42,this);
                    robot.tankDrivecs(1, 90, 42, 10, this);
                    robot.BucketServo.setPosition(0.52);
                    sleep(2000);
                    robot.tankDrivecs(1, 270, 85, 10, this);
                    /*robot.tankDrivecs(.5, 90, 9, 10, this);
                    robot.tankDrivecs(.5, 215, 74, 20, this);
                    robot.tankDrivecs(.5, 340, 15, 10, this);
                    robot.tankDrivecs(.5, 90, 125, 10, this);
                    robot.tankDrivecs(.5, 45, 108, 10, this);*/
                    /*robot.tankDrive(.5, 90, 0, 2.43, this);
                    robot.tankDrive(1, 45, 0, 1.3, this);*/
                    //sleep(500);
                    //robot.TeamMarker.setPosition(-100);
                    //sleep(500);
                    telemetry.addData("Gold Mineral Position 2", "Right");
                    telemetry.update();
                    //robot.tankDrive2(1, 45, 0, 3, this);
                    break;
                case -1: // center / unknown//it works DONT CHANGE
                    telemetry.addData("Gold Mineral Position 1", "Missing Center");
                    telemetry.update();
                    /*robot.tankDrivecs(.5, 90, 9, 10, this);
                    robot.tankDrivecs(.5, 180, 47, 20, this);
                    robot.tankDrivecs(.5, 0, 8, 10, this);
                    robot.tankDrivecs(.5, 90, 110, 10, this);
                    robot.tankDrivecs(.5, 45,90, 10, this);*/
                    // robot.leadScrewUp(36,1,18,this);
                    robot.tankDrivecs(.5, 90, 9, 10, this);
                    robot.tankDrivecs(1, 190, 25, 20, this);
                    robot.tankDrivecs(1, 0, 9, 10, this);
                    robot.tankDrivecs(1, 90, 60, 10, this);
                    robot.tankRotate(42,this);
                    robot.tankDrivecs(1, 88, 40, 10, this);
                    robot.BucketServo.setPosition(0.52);
                    sleep(2000);
                    robot.tankDrivecs(1, 266, 80, 10, this);
                    //robot.tankRotate(45,this);


                    /*sleep(500);
                    robot.TeamMarker.setPosition(-100);
                    sleep(500);
                    robot.tankDrive(1, 225, 0, 2.5, this);*/

                    break;
            }
            break;


        }
    }
}