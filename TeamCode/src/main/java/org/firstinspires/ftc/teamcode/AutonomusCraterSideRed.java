package org.firstinspires.ftc.teamcode;
        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "AutonomousRedSideCrater", group = "Linear")

public class AutonomusCraterSideRed extends LinearOpMode {

    HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();

@Override
    public void runOpMode () throws InterruptedException {
    robot.init(hardwareMap, telemetry);

        waitForStart();

        robot.leadScrewUp(15,1,20);
       robot.tankDrive(.5, 180,0,0.5);


        }
    }


