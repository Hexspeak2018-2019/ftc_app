package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "FourServos", group = "Linear Opmode")
//@Disabled
public class FourServos extends LinearOpMode {

    HardwareHexbot robot = new HardwareHexbot();

   public void runOpMode() {

        robot.openClaw();

        waitForStart();

        while (opModeIsActive()) {

            float throttle = gamepad1.right_stick_y;
            float turn = -gamepad1.left_stick_x;

            float leftSpeed = -(throttle + turn);
            float rightSpeed = throttle - turn;

            // inward position
            if (gamepad1.a) {
                robot.closeClaw();
            }

            // outward position
            if (gamepad2.right_bumper) {
                robot.openClaw();
            }

        }
    }
}
