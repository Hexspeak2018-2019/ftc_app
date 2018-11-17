package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;










import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import java.io.InterruptedIOException;

@Autonomous(name = "Trial", group = "Linear")

public class DepotSide extends LinearOpMode {

    HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();
    HexbotRoverMethods hexMethods = new HexbotRoverMethods();


    @Override
    public void runOpMode () throws InterruptedException {
        robot.init(hardwareMap, telemetry);


        waitForStart();
            while (opModeIsActive()) {
                robot.leadScrewUp(16, 1, 20);
               robot.tankDrive(.3, 180, 0, 0.5);
                robot.tankDrive(.5, 270, 0, 0.5);
                robot.tankDrive(.6, 0, 0, 15);
                sleep(1000);
                break;


            }

        }

    }



