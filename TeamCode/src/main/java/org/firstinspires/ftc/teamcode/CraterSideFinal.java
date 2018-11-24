package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.TimestampedI2cData;
import com.qualcomm.robotcore.util.RobotLog;
import com.qualcomm.robotcore.util.ThreadPool;

import org.firstinspires.ftc.robotcore.internal.opmode.TelemetryInternal;
import org.firstinspires.ftc.robotcore.internal.opmode.OpModeManagerImpl;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
//import java.util.concurrent.InterruptedException;

@Autonomous(name="TestAuto",group = "Red Autonomus")
@Disabled
public class CraterSideFinal extends LinearOpMode {

    //instance of VumarkDetection
    HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();

    @Override

    public void runOpMode() throws CancellationException {
        try {
            while (opModeIsActive()) {
                robot.init(hardwareMap, telemetry);

                waitForStart();
                robot.localtelemetry.addData("I reached here step 1", (robot.leadScrewMotor));
                robot.localtelemetry.update();

                robot.leadScrewUp(167, 1, 20,this);
                robot.tankDrive(.3, 180, 0, 10,this);
                robot.tankDrive(.4, 270, 0, 6,this);
                robot.localtelemetry.addData("I reached here", (robot.leadScrewMotor));
                robot.localtelemetry.update();
            }
        }
        catch(CancellationException ie){
                robot.localtelemetry.addData("I reached here catch", (robot.leadScrewMotor));
                robot.localtelemetry.update();
            }
        finally{
                robot.localtelemetry.addData("I reached here finally", (robot.leadScrewMotor));
                robot.localtelemetry.update();
                requestOpModeStop();

            }
        }

    }





