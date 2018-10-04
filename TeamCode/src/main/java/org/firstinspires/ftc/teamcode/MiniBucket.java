package org.firstinspires.ftc.teamcode;

    import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Mini Bucket", group = "Practice")
public class MiniBucket extends OpMode {

    Servo fluffyServo;

    @Override
    public void init() {
        fluffyServo = hardwareMap.servo.get("turning");
    }

    @Override
    public void loop() {

        fluffyServo.setDirection(Servo.Direction.REVERSE);

        if (gamepad1.dpad_up= true) {

            fluffyServo.setPosition(0);
        }

        if (gamepad1.dpad_down= true) {
            double positionCurrent2 =  fluffyServo.getPosition();
            double positionNew2 = positionCurrent2 - 0.01;
            fluffyServo.setPosition(positionNew2);
        }

    }
}