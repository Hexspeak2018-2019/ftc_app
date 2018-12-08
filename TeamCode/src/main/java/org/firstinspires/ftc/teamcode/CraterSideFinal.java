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

            robot.tankRotate(30,10,this);
       telemetry.addData("Gyro Angle is ",robot.getCurrentAngle());
            telemetry.addData("Gyro correction is ",robot.gyroCorrection(45,0.1));
       telemetry.update();

        }
    }
}