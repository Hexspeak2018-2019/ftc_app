package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Drive Teleop", group = "Practice")
    public class LanderHook extends OpMode{

        Servo landerHook;

        @Override
        public void init() {
            landerHook =hardwareMap.servo.get("hook");
        }

        @Override
        public void loop() {
            landerHook.setDirection(Servo.Direction.FORWARD);

            if(gamepad1.dpad_up = true) {
                    double positionCurrent = landerHook.getPosition();
                    double positionNew = positionCurrent + 0.01;
                    landerHook.setPosition(positionNew);
            }

            if(gamepad1.dpad_down = true) {
                    double positionCurrent2 = landerHook.getPosition();
                    double positionNew2= positionCurrent2 - 0.01;
                    landerHook.setPosition(positionNew2);
            }

        }

}
