/*
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "MovementNextGenFullyLoaded", group = "Linear")

public class MovementNextGenFullyLoaded extends LinearOpMode {

    HardwareHexbotRobotRuckus robot = new HardwareHexbotRoverRuckus();

    private ElapsedTime runtime = new ElapsedTime();



    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();



//        ArmMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        LinkMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        //RotationMotor.setMode((DcMotor.RunMode.STOP_AND_RESET_ENCODER));
//        ArmMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        LinkMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        //RotationMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        telemetry.addData("Arm Position", "Starting at %7d",
//                ArmMotor.getCurrentPosition());
//        telemetry.addData("Link Position", "Starting at %7d",
//                LinkMotor.getCurrentPosition());
//        telemetry.update();
//        ArmMotor.setDirection(DcMotor.Direction.REVERSE);
//        LinkMotor.setDirection(DcMotor.Direction.FORWARD);
//        //RotationMotor.setDirection(DcMotor.Direction.FORWARD);
        leadScrew.setDirection(DcMotor.Direction.FORWARD);
        // double a;
        waitForStart();

        while (opModeIsActive()) {
//            double differenceRange = ArmMotor.getCurrentPosition()-LinkMotor.getCurrentPosition();
            double leadPower;
            double drive = gamepad1.left_trigger;
            double turn  = -gamepad1.right_trigger;
            leadPower    = Range.clip(drive + turn, -1.0, 1.0) ;
            leadScrew.setPower(leadPower);
            telemetry.update();


            */
/*if(gamepad1.dpad_up) {
                if ((ArmMotor.getCurrentPosition() < ArmLiftPosition) && (LinkMotor.getCurrentPosition() < 10))

                {

                    ArmMotor.setPower(.8);
                    LinkMotor.setPower(0);

                }
                else if ((ArmMotor.getCurrentPosition() < ArmFinalPosition) && (LinkMotor.getCurrentPosition() > LinkFinalPosition))

                {

                    ArmMotor.setPower(.7);
                    LinkMotor.setPower(-.9);

                } else if ((ArmMotor.getCurrentPosition() >= ArmFinalPosition) && (LinkMotor.getCurrentPosition() > LinkFinalPosition))

                {

                    ArmMotor.setPower(0);
                    LinkMotor.setPower(-.8);

                } else if ((ArmMotor.getCurrentPosition() < ArmFinalPosition) && (LinkMotor.getCurrentPosition() <= LinkFinalPosition))

                {

                    ArmMotor.setPower(.8);
                    LinkMotor.setPower(0);

                }
                else {
                    ArmMotor.setPower(0);
                    LinkMotor.setPower(0);
                }

            }

            else if (gamepad1.dpad_down)
            {
                if (( ArmMotor.getCurrentPosition() > 100) && (LinkMotor.getCurrentPosition() < -50 ))
                {
                    ArmMotor.setPower(-.8);
                    LinkMotor.setPower(.8);
                    telemetry.addData("I am not in less than 20", (LinkMotor.getCurrentPosition()/TickPerDeg));
                }

                else if ( ArmMotor.getCurrentPosition() >= 0 && LinkMotor.getCurrentPosition() >=0)//LinkMotor.getCurrentPosition() <=20 && LinkMotor.getCurrentPosition() <0)
                {

                    ArmMotor.setPower(-.8);
                    LinkMotor.setPower(0);
                    telemetry.addData("I am in less than 20", (LinkMotor.getCurrentPosition() / TickPerDeg));

                }


                else if(ArmMotor.getCurrentPosition() >= 2)
                {

                    ArmMotor.setPower(0);
                    LinkMotor.setPower(0);
                }
                else {
                    ArmMotor.setPower(0);
                    LinkMotor.setPower(0);
                    telemetry.addData("Link  stopped at ", (LinkMotor.getCurrentPosition()/TickPerDeg));
                }

            }
            else {
                ArmMotor.setPower(0);
                LinkMotor.setPower(0);
            }*//*

            */
/*if (gamepad1.dpad_right)
            {
                if (RotationMotor.getCurrentPosition() <= RotationFinalPosition)
                {
                    RotationMotor.setPower(.3);
                }
                else {
                    RotationMotor.setPower(0);
                }
            }
            else if (gamepad1.dpad_left)
            {
                if (RotationMotor.getCurrentPosition() >= RotationHomePosition)
                {
                    RotationMotor.setPower(-.3);
                }
                else if (RotationMotor.getCurrentPosition() <= 0)
                {
                    RotationMotor.setPower(0);
                }
                else{
                    RotationMotor.setPower(0);
                }
            }
            else
            {
                RotationMotor.setPower(0);
            }*//*


            */
/*telemetry.addData("Arm Position", (ArmMotor.getCurrentPosition()/TickPerDeg)); // updates telemetry to display current postion of motor
            telemetry.addData("Link Position", (LinkMotor.getCurrentPosition()/TickPerDeg));
            telemetry.addData("Arm Position", (ArmMotor.getPower())); // updates telemetry to display current postion of motor
            telemetry.addData("Link Position", (LinkMotor.getPower()));
            telemetry.update();*//*


//
//            topLeft.setPower(.3);
//            topRight.setPower(.3);
//            bottomRight.setPower(.3);
//            bottomLeft.setPower(.3);
            float left_stick_x = gamepad1.left_stick_x;
            float left_stick_y = gamepad1.left_stick_y;
            float right_stick_x = gamepad1.right_stick_x;
            */
/*float turtle2 = -gamepad1.left_stick_x;
            float sideToSide = gamepad1.right_stick_x;*//*


        */
/*  float topLeftSpeed = (throttle - turn + turtle);//+
           float topRightSpeed = -(throttle + turn + turtle);//-
          float bottomLeftSpeed = -(throttle + turn - turtle);//+
            float bottomRightSpeed = (throttle - turn - turtle);//-*//*



            float topLeftSpeed = (-left_stick_x + left_stick_y + right_stick_x);//+
            float topRightSpeed = (-left_stick_x - left_stick_y + right_stick_x);//-
            float bottomLeftSpeed = (left_stick_x + left_stick_y + right_stick_x);//+
            float bottomRightSpeed = (left_stick_x - left_stick_y + right_stick_x);//-


            telemetry.addData("Top Left Motor Pwr", topLeftSpeed);
            telemetry.addData("Top Right Motor Pwr", topRightSpeed);
            telemetry.addData("Btm Left Motor Pwr", bottomLeftSpeed);
            telemetry.addData("Btm Right Motor Pwr", bottomRightSpeed);
            telemetry.update();
            topLeft.setPower(topLeftSpeed);
            topRight.setPower(topRightSpeed);
            bottomLeft.setPower(bottomLeftSpeed);
            bottomRight.setPower(bottomRightSpeed);

            idle();
        }
    }

}//runopmode





*/
