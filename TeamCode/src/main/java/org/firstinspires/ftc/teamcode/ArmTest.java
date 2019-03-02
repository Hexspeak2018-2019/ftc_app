package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "TeleopArmC", group = "Linear")

public class ArmTest extends LinearOpMode {

    HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();
    private int ARM_STEP = 15;
    private int position = 0;

    @Override
    public void runOpMode() throws InterruptedException {

        robot.init(hardwareMap, telemetry);

        waitForStart();
        robot.resetEncoderValues();
        position = robot.ArmMotor.getCurrentPosition();

        while (opModeIsActive()) {
            //private int position = robot.ArmMotor.getCurrentPosition();

            //Tank Drive with Joystick
            telemetry.addData("ARM  stopped at ", (robot.ArmMotor.getCurrentPosition()));

            //if (robot.ArmMotor.getCurrentPosition() < 590) {
                //position = robot.ArmMotor.getCurrentPosition();
                if (gamepad1.dpad_up) {

                    robot.ArmMotor.setTargetPosition(position - ARM_STEP);
                    robot.LinkMotor.setTargetPosition(position - ARM_STEP);
                    //robot.ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.ArmMotor.setPower(-.5);
                    robot.LinkMotor.setPower(-.5);
                    robot.ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.LinkMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    position = position - ARM_STEP;
                } else if (gamepad1.dpad_down) {
                    telemetry.addData("gamepad down :", position);
                    robot.ArmMotor.setTargetPosition(position + ARM_STEP);
                    robot.LinkMotor.setTargetPosition(position + ARM_STEP);
                    //robot.ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.ArmMotor.setPower(.5);
                    robot.LinkMotor.setPower(.5);
                    robot.ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.LinkMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    position = position + ARM_STEP;
                } else {
                    telemetry.addData("neither gamepad :", position);
                    robot.ArmMotor.setPower(0);
                    robot.LinkMotor.setPower(0);
                    position = robot.LinkMotor.getCurrentPosition();

                }
            telemetry.addData("gamepad up :", position);
            telemetry.update();

           // else if (robot.ArmMotor.getCurrentPosition() < 980 && robot.ArmMotor.getCurrentPosition() >590){
               /* position = robot.ArmMotor.getCurrentPosition();

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


           // else if (robot.ArmMotor.getCurrentPosition() >980) {
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
                }

*/            /*if (gamepad1.dpad_up){
                robot.ArmMotor.setTargetPosition(robot.ArmMotor.getCurrentPosition() + 200);

                robot.ArmMotor.setPower(1);
            }
            else if (gamepad1.dpad_down) {
                robot.ArmMotor.setTargetPosition(robot.ArmMotor.getCurrentPosition() - 200);
                robot.ArmMotor.setPower(-1);
            }

             else {
                robot.ArmMotor.setPower(0);

            }*/
            //robot.LinkMotor.setPower(gamepad1.left_stick_y);



            // robot.BucketServo.setPosition(Range.clip(bucketPosition, robot.BucketHomePosition, bucketMaxPosition));


            //Arm drive*/




        }
    }

}






