package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name = "OnlyGold",group = "TEST")
public class Gold extends LinearOpMode {
    GoldDetection detector = new GoldDetection();

    public void runOpMode() {

        detector.activateTF(hardwareMap);
        waitForStart();

        while (opModeIsActive()) {

            detector.detectObject2(telemetry);
            sleep(33000);

            /*int pos = detector.detectObject(telemetry);
            sleep(1000);
            telemetry.addData("the position is " , pos);
            telemetry.update();
            sleep(5000);*/
            //if(pos== -1) {
             //  pos = detector.detectObject2(telemetry);

             //   sleep(5000);

              //  telemetry.addData("the pos variable is ", pos);


            //}
        }
    }
}

