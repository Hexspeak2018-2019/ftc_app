package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;


@TeleOp(name="Servo",group="Test")
public class ServoProgram extends OpMode {

    Servo servo1 = null;
    double servoPosition = 0.1;

    @Override
    public void init() {
        servo1.setPosition(servoPosition);
        servo1 = hardwareMap.servo.get("Servo1");
        telemetry.addData("The value is " + servoPosition, servoPosition);

    }

    @Override public void loop() {

        if(gamepad1.a && servoPosition<1) {
            servoPosition += 0.07;

        }
        if(gamepad1.b && servoPosition>0) {
            servoPosition -= 0.07;
        }

        servo1.setPosition(servoPosition);
        telemetry.addData("The value is " + servoPosition, servoPosition);



    }
}
