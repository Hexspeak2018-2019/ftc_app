package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Leady ScrewY", group = "Linear")

public class LeadScrewTest extends LinearOpMode {

    HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();
    GoldDetection detector = new GoldDetection();

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap, telemetry);

        /*int position = detector.detectObject2(telemetry);
        telemetry.addData("Gold Mineral Position is", position);
        telemetry.update();*/
        // detector.shutdownTF();*/


        //robot.BucketServo.setPosition(0.48);
        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Lead Screw  stopped at ", (robot.leadScrewMotor.getCurrentPosition()));
            telemetry.update();

        }
    }
}




