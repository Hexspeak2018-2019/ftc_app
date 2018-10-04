package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Lander Hook2", group = "Practice")
public class LanderHook2 extends OpMode {

    Servo fluffyServo;

        @Override
    public void init() {
        fluffyServo = hardwareMap.servo.get("turning");
    }

    @Override
    public void loop() {

            fluffyServo.setPosition(fluffyServo.getPosition());

            while(gamepad1.y) {
                fluffyServo.setDirection(Servo.Direction.REVERSE);
                fluffyServo.setPosition(0);
                
            }


        while(gamepad1.x) {
            fluffyServo.setDirection(Servo.Direction.FORWARD);
            fluffyServo.setPosition(0);
        }
    }
}
