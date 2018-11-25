package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "MovementNextGenRealCss", group = "Linear")

public class ArmLinkBucket extends LinearOpMode {

    HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();





    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();


        while (opModeIsActive()) {


           if (gamepad1.a)
           {
               robot.BucketServo.setPosition(10);
           }
           if(gamepad1.b)
           {
               robot.BucketServo.setPosition(0);
           }
           if(gamepad1.y)
           {
               robot.BucketServo.setPosition(-10);
           }


            //Arm drive*/


            if(gamepad1.dpad_up) {
                if ((robot.ArmMotor.getCurrentPosition() < robot.ArmLiftPosition)
                        // && (robot.LinkMotor.getCurrentPosition() < -10)
                        && (robot.LimitSwitchLinkBottom.getState() == false))

                {

                    robot.ArmMotor.setPower(.4);

                    robot.LinkMotor.setPower(0.3);

                }

                else if ((robot.ArmMotor.getCurrentPosition() < robot.ArmLiftPosition) )

                {

                    robot.ArmMotor.setPower(.4);

                    robot.LinkMotor.setPower(0);

                }


                else if ((robot.ArmMotor.getCurrentPosition() < robot.ArmFinalPosition)
                        && (robot.LinkMotor.getCurrentPosition() > robot.LinkFinalPosition)
                        && (robot.LimitSwitchLinkTop.getState() == false))

                {

                    robot.ArmMotor.setPower(.3);
                    robot.LinkMotor.setPower(-.5);

                }
                else if ((robot.ArmMotor.getCurrentPosition() < robot.ArmFinalPosition) &&
                        (robot.LinkMotor.getCurrentPosition() > robot.LinkFinalPosition)
                        && (robot.LimitSwitchLinkTop.getState() == true))

                {

                    robot.ArmMotor.setPower(.3);
                    robot.LinkMotor.setPower(0);

                }
                else if ((robot.ArmMotor.getCurrentPosition() >= robot.ArmFinalPosition)
                        && (robot.LinkMotor.getCurrentPosition() > robot.LinkFinalPosition)
                        && (robot.LimitSwitchLinkTop.getState() == false))

                {

                    robot.ArmMotor.setPower(0);
                    robot.LinkMotor.setPower(-.4);

                } else if ((robot.ArmMotor.getCurrentPosition() < robot.ArmFinalPosition)
                        && (robot.LinkMotor.getCurrentPosition() <= robot.LinkFinalPosition))

                {

                    robot.ArmMotor.setPower(.4);
                    robot.LinkMotor.setPower(0);

                }
                else {
                    robot.ArmMotor.setPower(0);
                    robot.LinkMotor.setPower(0);
                }

            }

            else if (gamepad1.dpad_down)
            {
                if (( robot.ArmMotor.getCurrentPosition() > 100) && (robot.LinkMotor.getCurrentPosition() < -50 ))
                {
                    robot.ArmMotor.setPower(-.4);
                    robot.LinkMotor.setPower(.4);
                    telemetry.addData("I am not in less than 20", (robot.LinkMotor.getCurrentPosition()/robot.TickPerDeg));
                }

                else if ( robot.ArmMotor.getCurrentPosition() >= 0 && robot.LinkMotor.getCurrentPosition() >=0)
                {

                    robot.ArmMotor.setPower(-.4);
                    robot.LinkMotor.setPower(0);
                    telemetry.addData("I am in less than 20", (robot.LinkMotor.getCurrentPosition() / robot.TickPerDeg));

                }


                else if(robot.ArmMotor.getCurrentPosition() >= 2)
                {

                    robot.ArmMotor.setPower(0);
                    robot.LinkMotor.setPower(0);
                }
                else {
                    robot.ArmMotor.setPower(0);
                    robot.LinkMotor.setPower(0);
                    telemetry.addData("Link  stopped at ", (robot.LinkMotor.getCurrentPosition()/robot.TickPerDeg));
                }

            }
            else {
                robot.ArmMotor.setPower(0);
                robot.LinkMotor.setPower(0);
            }

            if (gamepad1.left_bumper)
            {

                robot.BucketMotor.setPower(-1);

            }
            else if (gamepad1.right_bumper)
            {

                robot.BucketMotor.setPower(1);

            }
            else
            {
                robot.BucketMotor.setPower(0);
            }
            //
            if ( gamepad2.dpad_up)
            {
                robot.ArmMotor.setPower(.3);
            }

            else if ( gamepad2.dpad_down)
            {
                robot.ArmMotor.setPower(-.3);
            }
            else
            {
                robot.ArmMotor.setPower(0);
            }

            if (gamepad2.a && robot.LimitSwitchLinkBottom.getState() == false)
            {
                robot.LinkMotor.setPower(.4);
            }
            else if (gamepad2.y)
            {
                robot.LinkMotor.setPower(-.4);
            }

            else

            {
                robot.LinkMotor.setPower(0);
            }





            if (robot.LimitSwitchLinkBottom.getState() == false) {
                telemetry.addData("LimitSwitchLinkBottom", "Is Not Pressed");
            }
            else {
                telemetry.addData("LimitSwitchLinkBottom", "Is  Pressed");
            }
            if (robot.LimitSwitchLinkTop.getState() == false) {
                telemetry.addData("LimitSwitchLinkTop", "Is Not Pressed");
            }
            else {
                telemetry.addData("LimitSwitchLinkTop", "Is  Pressed");
            }

            if (robot.LimitSwitchLsBottom.getState() == false) {
                telemetry.addData("LimitSwitchLsBottom", "Is Not Pressed");
            }
            else {
                telemetry.addData("LimitSwitchLsBottom", "Is  Pressed");
            }

            telemetry.addData("ARM  stopped at ", (robot.ArmMotor.getCurrentPosition()/robot.TickPerDeg));
            telemetry.addData("Link  stopped at ", (robot.LinkMotor.getCurrentPosition()/robot.TickPerDeg));


            telemetry.update();

/*            telemetry.addData("LM Encoder value is", (robot.LinkMotor.getCurrentPosition() / robot.TickPerDeg));
            telemetry.addData("AM Encoder value is", (robot.ArmMotor.getCurrentPosition() / robot.TickPerDeg));
            telemetry.addData("Servo Position is", bucketPosition);
            telemetry.update();*/


        }
    }

}





