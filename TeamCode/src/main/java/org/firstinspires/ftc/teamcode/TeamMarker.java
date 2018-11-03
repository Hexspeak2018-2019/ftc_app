package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name = "TeamMarker", group = "Linear")

public class TeamMarker extends LinearOpMode {
    private Servo tm;

    @Override
    public void runOpMode() throws InterruptedException {
        tm = hardwareMap.servo.get("tm");
        waitForStart();

        while (opModeIsActive()) {
            tm.setPosition(0.7);
            //0.6 position also works
        }
    }
}
