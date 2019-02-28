package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "TeleopProject2020", group = "Linear")

public class State2020Teleop extends LinearOpMode {

    HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();
    private int position = 0;
    private int ARM_STEP = 10;
    private int position1;
    private int position2;
    private int position3;
    private int position4;

    @Override
    public void runOpMode() throws InterruptedException {
        double bucketPosition = robot.BucketHomePosition;


        robot.init(hardwareMap, telemetry);

        robot.resetMotorsAndEncoders();

        robot.resetServo();

        telemetry.addData("LM Encoder value is", (robot.LinkMotor.getCurrentPosition() / robot.TickPerDeg));
        telemetry.addData("AM Encoder value is", (robot.ArmMotor.getCurrentPosition() / robot.TickPerDeg));
        telemetry.update();


        waitForStart();
        position = robot.ArmMotor.getCurrentPosition();
        robot.resetEncoderValues();
        telemetry.addData("LM Starting value is", (robot.LinkMotor.getCurrentPosition()));
        telemetry.addData("AM Starting value is", (robot.ArmMotor.getCurrentPosition()));
        telemetry.update();
        int increment = 10;
        int encoderValue = 0;
        int max_arm_position = 3300;
        int min_arm_position = 0;
        double arm_motor_power = 0.5;
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
            double rotPwr = gamepad1.right_stick_x * 0.7;

            robot.tankDrive(drivePower, robotAngle, rotPwr);

           /* if (gamepad1.dpad_up) {

                robot.ArmMotor.setTargetPosition(position - ARM_STEP);
                robot.LinkMotor.setTargetPosition(position1 - ARM_STEP);

                //robot.ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION)
                robot.ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.LinkMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.ArmMotor.setPower(-.3);
                robot.LinkMotor.setPower(-.3);
                position3 = robot.ArmMotor.getCurrentPosition();
                position2 = robot.LinkMotor.getCurrentPosition();
                position = position - ARM_STEP;
                position1 = position1 - ARM_STEP;
            } else if (gamepad1.dpad_down) {
                telemetry.addData("gamepad down :", position);
                robot.ArmMotor.setTargetPosition(position + ARM_STEP);
                robot.LinkMotor.setTargetPosition(position1 + ARM_STEP);
                //robot.ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.LinkMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.ArmMotor.setPower(.3);
                robot.LinkMotor.setPower(.3);
                position3 = robot.ArmMotor.getCurrentPosition();
                position2 = robot.LinkMotor.getCurrentPosition();
                position = position + ARM_STEP;
                position1 = position1 + ARM_STEP;
            } else {
                telemetry.addData("neither gamepad :", position);
                robot.ArmMotor.setTargetPosition(position3);
                robot.LinkMotor.setTargetPosition(position2);

                robot.ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.LinkMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.ArmMotor.setPower(0.1);
                robot.LinkMotor.setPower(0.1);


            }
*/
            // if (robot.ArmMotor.getCurrentPosition() < 590 && robot.ArmMotor.getCurrentPosition() >= 0) {
            //position = robot.ArmMotor.getCurrentPosition();
           /* if (gamepad1.dpad_up) {
                telemetry.addData("we are good", robot.ArmMotor.getCurrentPosition());
                robot.ArmMotor.setTargetPosition(position - ARM_STEP);
                //robot.LinkMotor.setTargetPosition(position - ARM_STEP);
                //robot.ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.ArmMotor.setPower(-1);
                //robot.LinkMotor.setPower(-1);
                robot.ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                //robot.LinkMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                position = robot.ArmMotor.getCurrentPosition();

            } else if (gamepad1.dpad_down) {
                robot.ArmMotor.setTargetPosition(position + ARM_STEP);
                //robot.LinkMotor.setTargetPosition(position + ARM_STEP);
                //robot.ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.ArmMotor.setPower(1);
                //robot.LinkMotor.setPower(1);
                robot.ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                //robot.LinkMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                position = robot.ArmMotor.getCurrentPosition();
            } else {
                robot.ArmMotor.setTargetPosition(position);
                ///robot.LinkMotor.setTargetPosition(position);
                //robot.ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.ArmMotor.setPower(0.1);
                //robot.LinkMotor.setPower(0.1);
                robot.ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                //robot.LinkMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }*/

            //else if (robot.ArmMotor.getCurrentPosition() < 980 && robot.ArmMotor.getCurrentPosition() >590){
             /*   position = robot.ArmMotor.getCurrentPosition();

                if (gamepad1.dpad_up) {
                    //position = robot.ArmMotor.getCurrentPosition();
                    robot.ArmMotor.setTargetPosition(position - ARM_STEP);
                    // robot.ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.ArmMotor.setPower(-1);
                    position = robot.ArmMotor.getCurrentPosition();
                } else if (gamepad1.dpad_down) {
                    //position = robot.ArmMotor.getCurrentPosition();
                    robot.ArmMotor.setTargetPosition(position + ARM_STEP);
                    //robot.ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.ArmMotor.setPower(1);
                    position = robot.ArmMotor.getCurrentPosition();
                } else {
                    robot.ArmMotor.setTargetPosition(position);
                    //robot.ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.ArmMotor.setPower(0.1);
                    robot.ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }

           // else if (robot.ArmMotor.getCurrentPosition() >980 && robot.ArmMotor.getCurrentPosition() <= 2100) {
                //position = robot.ArmMotor.getCurrentPosition();
                if (gamepad1.dpad_up) {
                    //position = robot.ArmMotor.getCurrentPosition();
                    robot.ArmMotor.setTargetPosition(position - ARM_STEP);
                    //robot.ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.ArmMotor.setPower(-1);
                    robot.ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    position = robot.ArmMotor.getCurrentPosition();
                } else if (gamepad1.dpad_down) {
                    //position = robot.ArmMotor.getCurrentPosition();
                    robot.ArmMotor.setTargetPosition(position + ARM_STEP);
                    //robot.ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.ArmMotor.setPower(1);
                    robot.ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    position = robot.ArmMotor.getCurrentPosition();
                } else {
                    robot.ArmMotor.setTargetPosition(position);
                    //robot.ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.ArmMotor.setPower(-0.1);
                    robot.ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }*/




     /*       double leadScrewPower;
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
*/

            //Tank Drive with Joystick

          /*  double drivePower = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
            double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 2;
            double rotPwr = gamepad1.right_stick_x * 0.7;
*/
            if (gamepad1.dpad_up && encoderValue <= max_arm_position) {
                encoderValue += increment;
            } else if (gamepad1.dpad_down && encoderValue > min_arm_position) {
                encoderValue -= increment;
            }

            robot.ArmMotor.setTargetPosition(encoderValue);


            robot.ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            robot.ArmMotor.setPower(Math.abs(arm_motor_power));
          
            if(gamepad1.dpad_left){
                robot.ArmMotor.setTargetPosition(2205);
                robot.ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
            if (gamepad1.left_bumper) {
                robot.BucketMotor.setPower(1);
            } else if (gamepad1.right_bumper) {
                robot.BucketMotor.setPower(-.3);
            } else if (gamepad1.y) {

                robot.BucketServo.setPosition(.7);
            } else if (gamepad1.b) {

                robot.BucketServo.setPosition(.2);
            } else {
                robot.BucketServo.setPosition(.5);
                robot.BucketMotor.setPower(0);
            }


            telemetry.addData("ARM  stopped at ", (robot.ArmMotor.getCurrentPosition()));
            telemetry.addData("Link  stopped at ", (robot.LinkMotor.getCurrentPosition()));
            telemetry.addData("Lead Screw  stopped at ", (robot.leadScrewMotor.getCurrentPosition()));
            telemetry.addData("Bucket Position ", (robot.BucketServo.getPosition()));
            telemetry.addData("Link Final Pos ", (robot.LinkFinalPosition / robot.TickPerDeg));
            telemetry.addData("LeftFront Motor Power", robot.leftFrontMotor.getPower());
            telemetry.addData("RightFront Motor Power", robot.rightFrontMotor.getPower());
            telemetry.addData("LeftRear Motor Power", robot.leftRearMotor.getPower());
            telemetry.addData("RightRear Motor Power", robot.rightRearMotor.getPower());
            telemetry.addData("Encoder value", encoderValue);
            telemetry.addData("Bucket Motor Power", (robot.BucketMotor.getPower()));
            //telemetry.addData("Encoder value on MotorL", .getCurrentPosition());
            telemetry.addData("Encoder value on MotorR", robot.ArmMotor.getCurrentPosition());
           // telemetry.addData("Motor powerL ", "%5.2f", leftDrive.getPower());
            telemetry.addData("Motor powerR ", "%5.2f", robot.ArmMotor.getPower());
            telemetry.update();


        }
    }

}







