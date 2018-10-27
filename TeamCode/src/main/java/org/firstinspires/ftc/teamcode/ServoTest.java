package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.hardware.CRServo;


@TeleOp(name = "ServoTest55", group = "Linear")

public class ServoTest extends LinearOpMode {
    private CRServo Servo1;
    double servoPosition = 0.1;



    @Override
    public void runOpMode() throws InterruptedException {
        Servo1 = hardwareMap.crservo.get("servo");
        waitForStart();

        while (opModeIsActive()) {
        double power = servoPosition += .1;
        double reverse = servoPosition -= .1;
                if (gamepad1.a && servoPosition <= 5) {

                    Servo1.setPower(.3);
                }

                 else if(gamepad1.x && servoPosition >=0) {

                     Servo1.setPower(-.3);
                }
                else if (servoPosition<0)
            {
                    Servo1.setPower(0);
                }
                else {
                    Servo1.setPower(0);
                }

                telemetry.addData("The value is " + servoPosition, servoPosition);

            }

    }
}







