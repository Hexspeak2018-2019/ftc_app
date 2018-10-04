package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Hook Up", group = "Practice")
public class HookUp extends OpMode {

    Servo fluffyServo;

    @Override
    public void init() {
        fluffyServo = hardwareMap.servo.get("turning");
    }

    @Override
    public void loop() {

        fluffyServo.setDirection(Servo.Direction.REVERSE);
    }
}