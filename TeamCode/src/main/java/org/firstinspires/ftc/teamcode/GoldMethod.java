package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name = "MethodGold", group = "camera")


public class GoldMethod extends LinearOpMode {
    HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap, telemetry);
        gold(0);

        }
        public void gold(int position) {
        if(position == 0) {
            robot.tankDrive(1,-40,0,2);

        } else if (position == 1){
            robot.tankDrive(1,0,0,2);
        } else {
            robot.tankDrive(1,40,0,2);
        }

     }
}
