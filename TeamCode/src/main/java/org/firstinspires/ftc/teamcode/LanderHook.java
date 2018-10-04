package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Lander Hook", group = "Practice")
    public class LanderHook extends LinearOpMode{

        Servo landerHook;

        @Override
    public void runOpMode() {
            landerHook = hardwareMap.servo.get("hook");

            waitForStart();
        while (opModeIsActive()) {

            landerHook.setDirection(Servo.Direction.FORWARD);

//                if (gamepad1.x = true) {
//                    landerHook.setDirection(Servo.Direction.REVERSE);
//                }
            }
        }
    }