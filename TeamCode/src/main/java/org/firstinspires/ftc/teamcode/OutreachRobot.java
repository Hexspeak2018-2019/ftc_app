package org.firstinspires.ftc.teamcode;
import android.text.method.MovementMethod;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "Outreach", group = "Linear")

public class OutreachRobot extends LinearOpMode {

    OutreachHardC out = new OutreachHardC();


    @Override
    public void runOpMode() throws InterruptedException {

        out.init(hardwareMap,telemetry);


        //telemetry.update();

        waitForStart();
        out.setMotorDirections();

        while (opModeIsActive()) {

            double drivePower  = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
            double robotAngle  = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 2;
            double rotPwr      = gamepad1.right_stick_x;
            out.Movement(drivePower,robotAngle,rotPwr);
        }
    }
}

//            //Tank Drive with Joystick







