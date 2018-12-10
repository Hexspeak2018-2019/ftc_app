
package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//@Disabled
@Autonomous(name = "DepotSideBaseOnbot", group = "Linear")

public class DepotSideEncoderBase extends LinearOpMode {

    HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();
    GoldDetection detector = new GoldDetection();

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap, telemetry);
        detector.activateTF(hardwareMap);
        //sleep(700);
        telemetry.addData("Camera is :", "Activated, Ready to Start Recognition");
        telemetry.update();


        waitForStart();

        while (opModeIsActive()) {

            int position = detector.detectObject2(telemetry);
            detector.shutdownTF();
            telemetry.addData("Gold Mineral Position is", position);
            telemetry.update();

            //telemetry.addData("# Object Detected", detector.updatedRecognitions.size());

            //robot.leadScrewUp(15, 1, 20, this);


            switch (position) {
                case 0: // if ( position == 0) /left time:15 sec
                    //if unknown, assume center and continue
                    //robot.tankDrive2(1, 180, 0, 3, this);
                    telemetry.addData("Gold Mineral Position 0", "Left");
                    telemetry.update();
                    //robot.leadScrewUp(36, 1, 18, this);
                    robot.tankDrivecs(.4, 90, 9, 10, this);
                    robot.tankDrivecs(.5, 155, 100, 10, this);
                    robot.tankDrivecs(.5, 340, 15, 10, this);
                    robot.tankDrivecs(.5, 215, 60, 10, this);
                   /* robot.tankDrive(.5, 0, 0, .9, this);
                    robot.tankDrive(.5, 90, 0, 6.9, this);
                    robot.tankDrive(1, 45, 0, 1.2, this);
                    sleep(500);
                    robot.TeamMarker.setPosition(-100);
                    sleep(500);
                    robot.tankDrive(1, 225, 0, 2.5, this);
                    break;
                    */
                    break;

                case 1: //center time: 22sec
                    //robot.tankDrive2(1, 90, 0, 3, this);
                    //robot.leadScrewUp(36, 1, 18, this);
                    telemetry.addData("Gold Mineral Position 1", "Center");
                    telemetry.update();
                    robot.tankDrivecs(.4, 90, 9,10 , this);
                    robot.tankDrivecs(.5, 180, 120, 10, this);
                    //robot.BucketServo.setPosition(.3);
                    //robot.tankDrivecs(.5, 225, 50, 2,this);


                   /* robot.tankDrive(.5, 0, 0, .9, this);

                    robot.tankDrive(.5, 90, 0, 5, this);
                    robot.tankDrive(1, 45, 0, 1, this);
                    sleep(500);
                    robot.TeamMarker.setPosition(-100);
                    sleep(500);

*/
                    //robot.tankDrive(1, 225, 0, 2.5, this);

                    break;
                case 2: //right /NOT WORKING PROTO TYPE CHANGE PLEASE
                    //robot.leadScrewUp(36, 1, 18, this);
                    robot.tankDrivecs(.5, 90, 9, 10, this);
                    robot.tankDrivecs(.5, 215, 93, 20, this);
                    //robot.tankDrivecs(.5, 35, 9, 10, this);
                    robot.tankDrivecs(.5, 135, 60, 10, this);
                    // robot.tankDrive(.5, 90, 0, 2.43, this);
                    //robot.tankDrive(1, 45, 0, 1.3, this);*/
                    //sleep(500);
                    //robot.TeamMarker.setPosition(-100);
                    //sleep(500);
                    //robot.tankDrive(.5,339,0,0.6,this);
                    // robot.tankDrive(.5,225,0,3.2,this);
                    telemetry.addData("Gold Mineral Position 2", "Right");
                    telemetry.update();
                    //robot.tankDrive2(1, 45, 0, 3, this);
                    break;
                case -1: // center / unknown
                    //robot.tankDrive2(1, 90, 0, 3, this);
                    //robot.leadScrewUp(36, 1, 18, this);
                    robot.tankDrive(.4, 90, 0, .5, this);
                    robot.tankDrive(.5, 176, 0, 4.3, this);
                    robot.tankDrive(.5, 225, 0, 2,this);


                   /* robot.tankDrive(.5, 0, 0, .9, this);

                    robot.tankDrive(.5, 90, 0, 5, this);
                    robot.tankDrive(1, 45, 0, 1, this);
                    sleep(500);
                    robot.TeamMarker.setPosition(-100);
                    sleep(500);

*/
                    //robot.tankDrive(1, 225, 0, 2.5, this);
                    telemetry.addData("Gold Mineral Position 1", "Center");
                    telemetry.update();
                    break;
            }
            break;


        }
    }

}

/*   import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
                    import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "AutonomousRedSideCrater", group = "Linear")
@Autonomous(name = "AutonomousRedSideCraterCompetition", group = "Linear")

public class AutonomusCraterSideRedOnBot extends LinearOpMode {

    HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();
    GoldDetection detector = new GoldDetection();
    HexBotTensorFlowObjectDetection block = new HexBotTensorFlowObjectDetection();
    GoldDetection
    @Override
    public void runOpMode () throws InterruptedException {
        robot.init(hardwareMap, telemetry);

        waitForStart();

        robot.leadScrewUp(15,1,20, this);
        robot.tankDrive(.3, 180,0,0.5);
        robot.leadScrewUp(15,1,20,this);
        robot.tankDrive2(1,180,0,3,this);

        detector.activateTF(hardwareMap);

        int position =detector.detectObject();
        telemetry.addData("position is " + position, position);

        // do what ever here based on gold Position
        robot.tankDrive(.3, 270,0,1);
    }
}

        detector.shutdownTF();

                }
                }
*/



