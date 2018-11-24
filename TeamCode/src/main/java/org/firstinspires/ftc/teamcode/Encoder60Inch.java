package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "60inch", group = "Linear")

public class Encoder60Inch extends LinearOpMode {

    HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap, telemetry);
        robot.resetEncoderValues();
        waitForStart();
        while (opModeIsActive())
        {


            if (robot.rightFrontMotor.getCurrentPosition() <= robot.ANDYMARK_TICKS_PER_REV*100)
            {
                robot.leftFrontMotor.setPower(.2);
                robot.rightFrontMotor.setPower(.2);
                robot.leftRearMotor.setPower(-.2);
                robot.rightRearMotor.setPower(-.2);
            }
            else
            {
                robot.leftFrontMotor.setPower(0);
                robot.rightFrontMotor.setPower(0);
                robot.leftRearMotor.setPower(0);
                robot.rightRearMotor.setPower(0);
            }
            sleep(5000);

            robot.localtelemetry.addData("Encoder Valueslf", (robot.leftFrontMotor.getCurrentPosition()));
            robot.localtelemetry.addData("Encoder Valuesrf", (robot.rightFrontMotor.getCurrentPosition()));
            robot.localtelemetry.addData("Encoder Valueslr", (robot.leftRearMotor.getCurrentPosition()));
            robot.localtelemetry.addData("Encoder Valuesrr", (robot.rightRearMotor.getCurrentPosition()));
            robot.localtelemetry.update();





        }

    }

}
