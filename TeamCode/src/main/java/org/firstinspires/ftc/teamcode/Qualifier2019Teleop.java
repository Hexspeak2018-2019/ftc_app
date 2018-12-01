package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "Teleop-SR", group = "Linear")

public class Qualifier2019Teleop extends LinearOpMode {

    HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();


    @Override
    public void runOpMode() throws InterruptedException {

        double bucketPosition = robot.BucketHomePosition;
        double bucketMaxPosition = 0.9;

        robot.init(hardwareMap, telemetry);

        robot.resetMotorsAndEncoders();
        robot.setEncoderMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.resetServo();

        telemetry.addData("LM Encoder value is", (robot.LinkMotor.getCurrentPosition() / robot.TickPerDeg));
        telemetry.addData("AM Encoder value is", (robot.ArmMotor.getCurrentPosition() / robot.TickPerDeg));
        telemetry.update();


        waitForStart();


        while (opModeIsActive()) {
            double leadScrewPower;
            double leadScrewUpPower = gamepad1.left_trigger * 0.7;
            double leadScrewDownPower = -gamepad1.right_trigger * 0.7;
            leadScrewPower = Range.clip(leadScrewUpPower + leadScrewDownPower, -1.0, 1.0);

            if (leadScrewUpPower > leadScrewDownPower) {
                robot.leadScrewMotor.setPower(leadScrewPower);
            } else if ((leadScrewDownPower > leadScrewUpPower)) //&& robot.LimitSwitchLsBottom.getState() == false)
            {
                robot.leadScrewMotor.setPower(leadScrewPower);
            } else {
                robot.leadScrewMotor.setPower(0);
            }


            //Tank Drive with Joystick

            double drivePower = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
            double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 2;
            double rotPwr = gamepad1.right_stick_x;

            robot.tankDrive(drivePower, robotAngle, rotPwr);


            if (gamepad1.dpad_left && bucketPosition > robot.BucketHomePosition)
                bucketPosition -= 0.005;


            else if (gamepad1.dpad_right && bucketPosition < bucketMaxPosition) {
                bucketPosition += 0.005;
            }


            robot.BucketServo.setPosition(Range.clip(bucketPosition, robot.BucketHomePosition, bucketMaxPosition));


            //Arm drive*/


            if (gamepad1.dpad_up) {
                if ((robot.ArmMotor.getCurrentPosition() < robot.ArmLiftPosition)
                        && (robot.LinkMotor.getCurrentPosition() > -10)) {
                    robot.ArmMotor.setPower(.4);
                    robot.LinkMotor.setPower(0);
                    telemetry.addData("Loop no ", "1");
                    telemetry.update();
                } else if ((robot.ArmMotor.getCurrentPosition() < robot.ArmFinalPosition)
                        && (robot.LinkMotor.getCurrentPosition() < robot.LinkFinalPosition)) {
                    robot.ArmMotor.setPower(.5);
                    robot.LinkMotor.setPower(.3);
                    telemetry.addData("Loop no ", "2");
                    telemetry.update();
                }
               /* else if ((robot.ArmMotor.getCurrentPosition() >= robot.ArmFinalPosition)
                        && (robot.LinkMotor.getCurrentPosition() > robot.LinkFinalPosition)) {
                    robot.ArmMotor.setPower(0);
                    robot.LinkMotor.setPower(-.4);
                    telemetry.addData("Loop no ", "3");
                    telemetry.update();
                } else if ((robot.ArmMotor.getCurrentPosition() < robot.ArmFinalPosition)
                        && (robot.LinkMotor.getCurrentPosition() <= robot.LinkFinalPosition)) {
                    robot.ArmMotor.setPower(.4);
                    robot.LinkMotor.setPower(0);
                    telemetry.addData("Loop no ", "4");
                    telemetry.update();

                } else if (gamepad1.dpad_down) {
                    if ((robot.ArmMotor.getCurrentPosition() > 100) && (robot.LinkMotor.getCurrentPosition() < -50)) {
                        robot.ArmMotor.setPower(-.4);
                        robot.LinkMotor.setPower(.4);
                        telemetry.addData("I am not in less than 20", (robot.LinkMotor.getCurrentPosition() / robot.TickPerDeg));
                    } else if (robot.ArmMotor.getCurrentPosition() >= 0 && robot.LinkMotor.getCurrentPosition() >= 0) {
                        robot.ArmMotor.setPower(-.4);
                        robot.LinkMotor.setPower(0);
                        telemetry.addData("I am in less than 20", (robot.LinkMotor.getCurrentPosition() / robot.TickPerDeg));
                    }
                }*/
            }
            else {
                robot.ArmMotor.setPower(0);
                robot.LinkMotor.setPower(0);
            }


            if (gamepad1.left_bumper) {

                robot.BucketMotor.setPower(-1);

            } else if (gamepad1.right_bumper) {

                robot.BucketMotor.setPower(1);

            } else {
                robot.BucketMotor.setPower(0);
            }


            telemetry.addData("ARM  stopped at ", (robot.ArmMotor.getCurrentPosition()/ robot.TickPerDeg));
            telemetry.addData("Link  stopped at ", (robot.LinkMotor.getCurrentPosition() ));
            telemetry.addData("Link Final Pos ", (robot.LinkFinalPosition));



            telemetry.update();


        }
    }

}






