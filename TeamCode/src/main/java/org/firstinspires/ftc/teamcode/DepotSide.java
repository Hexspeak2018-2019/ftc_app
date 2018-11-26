package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//@Disabled
@Autonomous(name = "DepotSideCompetition", group = "Linear")

public class DepotSide extends LinearOpMode {

    HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();
    HexBotTensorFlowObjectDetection block = new HexBotTensorFlowObjectDetection();
    GoldDetection detector = new GoldDetection();

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap, telemetry);
        detector.activateTF(hardwareMap);
        waitForStart();
        while (opModeIsActive()) {

            int position = 0;
            //telemetry.addData("# Object Detected", detector.updatedRecognitions.size());
            telemetry.addData("The position is " + position, position);
            telemetry.update();
            //robot.leadScrewUp(15, 1, 20, this);


            switch (position) {
                case 0: // if ( position == 0) /left
                    //if unknown, assume center and continue
                    //robot.tankDrive2(1, 180, 0, 3, this);
                    telemetry.addData("Gold Mineral Position 0", "Left");
                    telemetry.update();
                    break;

                case 1: //center
                    //robot.tankDrive2(1, 90, 0, 3, this);
                    telemetry.addData("Gold Mineral Position 1", "Center");
                    telemetry.update();
                    break;
                case 2: //right
                    telemetry.addData("Gold Mineral Position 2", "Right");
                    telemetry.update();
                    //robot.tankDrive2(1, 45, 0, 3, this);
                    break;
                case -1: // center / unknown
                    telemetry.addData("Gold Mineral Position -1", "Missing");
                    telemetry.update();
                    //robot.tankDrive2(1, 270, 0, 3, this);

                    break;
            }
            if (position == 0) {
                robot.tankDrive(.5, 176, 0, .5, this);
                robot.tankDrive(.5, 270, 0, .7, this);
                robot.tankDrive(.5, 180, 0, 4.6, this);
                robot.tankDrive(.5, 130, 0, 1.3, this);
                robot.tankDrive(.5,270,0,2.9,this);

                sleep(500);
                robot.TeamMarker.setPosition(-100);
                sleep(500);

                //robot.tankDrive(1,225,0,2.5,this);
                break;
            }
            else if (position == 1)
            {
                robot.tankDrive(.5,176,0,4,this);
                sleep(500);
                robot.TeamMarker.setPosition(-100);
                sleep(500);
                robot.tankDrive(1,225,0,2.5,this);
                break;
            }
            else if (position == 2)
            {
                robot.tankDrive(.5,150,0,2.6,this);
                robot.tankDrive(.5,0,0,.9,this);
                robot.tankDrive(.5,90,0,3,this);
                robot.tankDrive(1,45,0,1.3,this);
                sleep(500);
                robot.TeamMarker.setPosition(-100);
                sleep(500);
                robot.tankDrive(1,225,0,2.5,this);

                break;
            }
            else
            {
                robot.tankDrive(.5,176,0,2.4,this);
                robot.tankDrive(.5,0,0,.9,this);
                robot.tankDrive(.5,90,0,5.5,this);
                robot.tankDrive(1,45,0,.9,this);
                sleep(500);
                robot.TeamMarker.setPosition(-100);
                sleep(500);
                robot.tankDrive(1,225,0,2.5,this);
            }




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


