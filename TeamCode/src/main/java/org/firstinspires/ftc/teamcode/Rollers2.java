package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;

public class Rollers2 {

        double leftPower;
        double rightPower;


        @TeleOp(name="Rollers2", group="Pushbot")
        //@Disabled
        public class PushbotTeleopPOV_Linear extends LinearOpMode {

            /* Declare OpMode members. */
            HardwarePushbot robot           = new HardwarePushbot();   // Use a Pushbot's hardware
            // could also use HardwarePushbotMatrix class.

            @Override
            public void runOpMode() {
                DcMotor left = null;
                DcMotor right = null;

                /* Initialize the hardware variables.
                 * The init() method of the hardware class does all the work here
                 */
                robot.init(hardwareMap);

                // Send telemetry message to signify robot waiting;
                telemetry.addData("Say", "Hello Driver");    //
                telemetry.update();

                // Wait for the game to start (driver presses PLAY)
                waitForStart();

                // run until the end of the match (driver presses STOP)
                while (opModeIsActive()) {


                    telemetry.addData("Say", "Hello Driver");

                    // Send telemetry message to signify robot waiting;
                    telemetry.addData("Say", "Hello Driver");    //
                    telemetry.update();

                    // Wait for the game to start (driver presses PLAY)
                    waitForStart();

                    while (opModeIsActive()) {



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
