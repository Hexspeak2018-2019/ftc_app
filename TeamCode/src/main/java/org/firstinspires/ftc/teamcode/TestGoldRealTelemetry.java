package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "GoldMeasuretry", group = "Tehst")

public class TestGoldRealTelemetry extends LinearOpMode {
    GoldDetection detector = new GoldDetection();

    public void runOpMode() {


        detector.activateTF(hardwareMap);
        waitForStart();
        while (opModeIsActive()) {
            detector.detectObject(telemetry);
            sleep(10090);
        }
    }
}
