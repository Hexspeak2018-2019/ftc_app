package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

//import java.util.concurrent.InterruptedException;

@Autonomous(name="Test2.0",group = "Red Autonomus")

public class TestingFrontOfRobot extends LinearOpMode {

    //instance of VumarkDetection
    HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();

    @Override

    public void runOpMode() throws InterruptedException {

        robot.init(hardwareMap, telemetry);
        waitForStart();
        while (opModeIsActive()) {
            robot.tankDrive(.5,0,0,10,this);
            telemetry.addData("ticks for leftFrontMotor",robot.leftFrontMotor);


        }
        }
        }