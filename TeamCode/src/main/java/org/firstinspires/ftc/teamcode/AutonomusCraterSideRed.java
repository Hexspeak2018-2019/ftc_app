package org.firstinspires.ftc.teamcode;
        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "AutonomousRedSideCrater", group = "Linear")

public class AutonomusCraterSideRed extends LinearOpMode {

    HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();

@Override
    public void runOpMode () throws InterruptedException {

    while(opModeIsActive()) {
        robot.leadScrewUp(90,.7,2);
        sleep(1000);
        robot.tankDrive(1, 270,0,4);
        sleep(1000);
        robot.tankDrive(1,180,0,4);
        }
    }
}
