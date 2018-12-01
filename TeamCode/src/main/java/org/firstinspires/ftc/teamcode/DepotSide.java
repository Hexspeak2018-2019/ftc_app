/*
package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//@Disabled
@Autonomous(name = "DepotSideSR", group = "Linear")

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

            int position = 2;
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
                robot.leadScrewUp(36,1,18,this);
                robot.tankDrive(.5,90,0,.5,this);
                robot.tankDrive(.5, 176, 0, .5, this);
                robot.tankDrive(.5, 270, 0, 1.6, this);
                robot.tankDrive(.5, 176, 0, 4.3, this);
                robot.tankDrive(.5, 135, 0, 1.5, this);

                //robot.tankDrive(.5, 150, 0, .3, this);

                //robot.tankDrive(.5,270,0,2.9,this);

                sleep(500);
                robot.TeamMarker.setPosition(-75);
                sleep(2000);
                robot.tankDrive(.5,270,0,.75,this);
                robot.tankDrive(.5,30,0,1.1,this);

                //robot.tankDrive(.5, 140, 0, .2, this);
                //robot.tankDrive(.5, 150, 0, .3, this);
                //robot.tankDrive(.5, 30, 0, 2, this);
                //robot.tankDrive(1,225,0,2.5,this);
                break;
            }
            else if (position == 1)
            {
                robot.leadScrewUp(36,1,18,this);
                robot.tankDrive(.5,90,0,.5,this);
                robot.tankDrive(.5, 176, 0, .6, this);
                robot.tankDrive(.5, 270, 0, .3, this);
                robot.tankDrive(.5, 176, 0, 4.2, this);
                robot.tankDrive(.5,189,0,.9,this);
                robot.tankDrive(.5,182,0,1.2,this);
                robot.tankDrive(.5,186,0,.5,this);
                robot.tankDrive(.5,192,0,.2,this);
                robot.tankDrive(.5,199,0,.2,this);
                sleep(500);
                robot.TeamMarker.setPosition(-20);

                sleep(2000);


                break;
            }
            else if (position == 2)
            {
                robot.leadScrewUp(36,1,18,this);
                robot.tankDrive(.5,90,0,.5,this);
                robot.tankDrive(.5, 176, 0, .6, this);
                robot.tankDrive(1, 153, 0, 1.7, this);
                sleep(500);
                robot.tankDrive(.5,339,0,.6,this);
                //robot.tankDrive(.5, 190, 0, 4.5, this);
                //robot.tankDrive(.5, 225, 0, .5, this);
                robot.tankDrive(.5,225,0,3.2,this);

                sleep(500);
                robot.TeamMarker.setPosition(-100);
                sleep(500);
                //robot.tankDrive(1,225,0,2.5,this);

                break;
            }
            else
            {
                robot.leadScrewUp(36,1,18,this);
                robot.tankDrive(.5,90,0,.5,this);
                robot.tankDrive(.5, 176, 0, .6, this);
                robot.tankDrive(.5, 270, 0, .3, this);
                robot.tankDrive(.5, 176, 0, 4.2, this);
                robot.tankDrive(.5,189,0,.9,this);
                robot.tankDrive(.5,182,0,1.2,this);
                robot.tankDrive(.5,186,0,.5,this);
                robot.tankDrive(.5,192,0,.2,this);
                robot.tankDrive(.5,199,0,.2,this);
                sleep(500);
                robot.TeamMarker.setPosition(-20);

                sleep(2000);


                break;
            }




        }
    }
}
         */
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



