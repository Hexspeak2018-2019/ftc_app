
package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//@Disabled
@Autonomous(name = "Depter", group = "Linear")

public class Depter extends LinearOpMode {

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

            robot.leadScrewUp(25, 1, 20, this);

            switch (position) {
                case 0:
                    telemetry.addData("Gold Mineral Position 0", "Left");
                    telemetry.update();
                    robot.tankDrivecs(.4, 90, 9, 10, this);
                    robot.tankDrivecs(.5, 155, 110, 10, this);
                    robot.tankRotate(-125,this);
                    robot.BucketServo.setPosition(0.52);
                    sleep(2000);
                    robot.tankDrivecs(1, 180, 75, 10, this);
                    break;
                case 1:
                    telemetry.addData("Gold Mineral Position 1", "Center");
                    telemetry.update();
                    robot.tankDrivecs(.4, 90, 9,10 , this);
                    robot.tankDrivecs(.5, 180, 128, 10, this);
                    robot.tankRotate(-45,this);
                    robot.BucketServo.setPosition(0.52);
                    sleep(2000);
                    robot.tankDrivecs(1, 180, 12, 10, this);
                    robot.tankDrivecs(1, 270, 85, 10, this);
                    break;


                case 2:
                    robot.tankDrivecs(.5, 90, 9, 10, this);
                    robot.tankDrivecs(.5, 215, 88, 20, this);
                    robot.tankRotate(-35,this);
                    robot.tankDrivecs(.5, 90, 83, 10, this);
                    robot.tankRotate(-130,this);
                    robot.BucketServo.setPosition(0.52);
                    sleep(2000);
                    robot.tankDrivecs(1, 180, 85, 10, this);

                    break;
                case -1: // center / unknown
                    //robot.tankDrive2(1, 90, 0, 3, this);
                    //robot.leadScrewUp(36, 1, 18, this);
                    telemetry.addData("Gold Mineral Position 1", "Missing Center");
                    telemetry.update();
                    robot.tankDrivecs(.4, 90, 9,10 , this);
                    robot.tankDrivecs(.5, 180, 128, 10, this);
                    robot.tankRotate(-45,this);
                    robot.BucketServo.setPosition(0.52);
                    sleep(2000);
                    robot.tankDrivecs(1, 180, 12, 10, this);
                    robot.tankDrivecs(1, 270, 85, 10, this);


                    break;
            }
            break;


        }
    }

}



