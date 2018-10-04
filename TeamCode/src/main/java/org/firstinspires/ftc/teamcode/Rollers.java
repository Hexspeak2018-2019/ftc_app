package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;


public class Rollers {



    public class Rollers_test {


        double leftPower;
        double rightPower;

        @TeleOp(name = "Rollers", group = "Pushbot")
        //@Disabled
        public class Rollers1 extends LinearOpMode {

            /* Declare OpMode members. */
            HardwarePushbot robot = new HardwarePushbot();   // Use a Pushbot's hardware

            @Override
            public void runOpMode() {
                telemetry.addData("Say", "Hello Driver");

                // Send telemetry message to signify robot waiting;
                telemetry.addData("Say", "Hello Driver");    //
                telemetry.update();

                // Wait for the game to start (driver presses PLAY)
                waitForStart();

                while (opModeIsActive()) {

                    DcMotor left = null;
                    DcMotor right = null;

                    while (2 > 1) {
                        leftPower = gamepad1.left_stick_y;
                        rightPower = gamepad1.right_stick_y;

                        left.setPower(leftPower);
                        right.setPower(leftPower);

                    }

                }
            }
        }

    }

}
