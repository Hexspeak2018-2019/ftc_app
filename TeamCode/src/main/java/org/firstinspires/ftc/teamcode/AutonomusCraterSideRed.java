package org.firstinspires.ftc.teamcode;
        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "AutonomousRedSideCraterCompetition", group = "Linear")

public class AutonomusCraterSideRed extends LinearOpMode {

    HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();
    HexBotTensorFlowObjectDetection block = new HexBotTensorFlowObjectDetection();
@Override
    public void runOpMode () throws InterruptedException {
    robot.init(hardwareMap, telemetry);

        waitForStart();

        robot.leadScrewUp(15,1,20,this);
    robot.tankDrive2(1,180,0,3,this);

        }
    }


