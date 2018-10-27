package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Claw", group = "Practice")
public class Claw extends OpMode {

    Servo xAxis;
    Servo yAxis;
    Servo claw;

    @Override
    public void init() {
        xAxis = hardwareMap.servo.get("xAxis");
        yAxis = hardwareMap.servo.get("yAxis");
        claw = hardwareMap.servo.get("claw");
    }

    @Override
    public void loop() {


        if (gamepad1.left_bumper) {
            xAxis.setPosition(0.5);

            if (gamepad1.left_trigger == -1) {
                xAxis.setPosition(0);


                if (gamepad1.right_bumper) {
                    yAxis.setPosition(0.5);

                    if (gamepad1.right_trigger == -1) {
                        yAxis.setPosition(0);


//       if(gamepad1.y){
//           claw.setPosition(0.75);
//
//       } else if(gamepad1.x) {
//           claw.setPosition(0);
//       }
//
//       }

                    }
                }
            }
        }
    }
}
