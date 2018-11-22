package org.firstinspires.ftc.teamcode;
        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "AutonomousRedSideCrater", group = "Linear")

public class AutonomusCraterSideRed extends LinearOpMode {

    HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();
    GoldDetection detector = new GoldDetection();
@Override
    public void runOpMode () throws InterruptedException {
    robot.init(hardwareMap, telemetry);

        waitForStart();

        robot.leadScrewUp(15,1,20);
       robot.tankDrive(.3, 180,0,0.5);

       detector.activateTF();

       int goldPosition = detector.detectObject();

       // do what ever here based on gold Position
        robot.tankDrive(.3, 270,0,1);

        detector.shutdownTF();

        }
    }