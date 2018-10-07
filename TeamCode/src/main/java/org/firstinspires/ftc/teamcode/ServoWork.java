package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Servo",group="Training")
public class ServoWork extends OpMode {

    Servo servo2;
    Servo servo3;
    double servoPosition = 0.1;
    double servoPosition2 = 0.1;

    public void init() {
        servo2 = hardwareMap.get(Servo.class, "servo2");
        servo3 = hardwareMap.get(Servo.class, "servo3");

        telemetry.addData("Servo position for servo 2 currently is " + servoPosition, servoPosition);
        telemetry.addData("Servo position for servo 3 currently is " + servoPosition2, servoPosition2);

    }

    public void loop() {
        if(gamepad1.a && servoPosition < 1) {
            servoPosition += 0.05;
            servoPosition2 += 0.05;

        }

                if (gamepad1.b && servoPosition > 0) {
            servoPosition -= 0.05;
            servoPosition2 -= 0.05;

        }

        servo2.setPosition(servoPosition);
        servo3.setPosition(servoPosition2);

        }

    }