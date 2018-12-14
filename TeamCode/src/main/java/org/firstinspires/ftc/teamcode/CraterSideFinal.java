package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Crater-cs", group = "Linear")

public class CraterSideFinal extends LinearOpMode {

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


            robot.tankRotate(45,this);
            sleep(3000);
            robot.tankRotate(90,this);
            sleep(3000);
            robot.tankRotate(180,this);
            sleep(3000);




      
            /*telemetry.addData("Servo Position is ",robot.BucketServo.getPosition());
       telemetry.update();*/
break;
        }
    }
}