package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "MovementNextGenReal", group = "Linear")

public class AndroidStudioMovement extends LinearOpMode {

    HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();


    @Override
    public void runOpMode() throws InterruptedException {

        robot.init(hardwareMap, telemetry);
        waitForStart();

        while (opModeIsActive()) {


            //Tank Drive with Joystick

            double drivePower  = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
            double robotAngle  = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
            double rotPwr      = gamepad1.right_stick_x;

            double angleInRad = robotAngle*(Math.PI/180);

            double wheelSpeeds[] = new double[4];

            wheelSpeeds[0]  = - (drivePower* Math.sin(angleInRad + Math.PI/4) +rotPwr);
            wheelSpeeds[1]  =   (drivePower*  Math.cos(angleInRad + Math.PI/4) - rotPwr);
            wheelSpeeds[2]  = - (drivePower* Math.cos(angleInRad + Math.PI/4) + rotPwr);
            wheelSpeeds[3]  =   (drivePower*  Math.sin(angleInRad + Math.PI/4) - rotPwr);

            robot.normalize(wheelSpeeds);
            //make sure all motors run forward when set to positive power

            robot.leftFrontMotor.setPower(wheelSpeeds[0]);
            robot.rightFrontMotor.setPower(wheelSpeeds[1]);
            robot.leftRearMotor.setPower(wheelSpeeds[2]);
            robot.rightRearMotor.setPower(wheelSpeeds[3]);

            //Arm drive


            if(gamepad1.dpad_up) {
                if ((robot.ArmMotor.getCurrentPosition() < robot.ArmLiftPosition) && (robot.LinkMotor.getCurrentPosition() < 10))

                {

                    robot.ArmMotor.setPower(.8);
                    robot.LinkMotor.setPower(0);

                }
                else if ((robot.ArmMotor.getCurrentPosition() < robot.ArmFinalPosition) && (robot.LinkMotor.getCurrentPosition() > robot.LinkFinalPosition))

                {

                    robot.ArmMotor.setPower(.7);
                    robot.LinkMotor.setPower(-.9);

                } else if ((robot.ArmMotor.getCurrentPosition() >= robot.ArmFinalPosition) && (robot.LinkMotor.getCurrentPosition() > robot.LinkFinalPosition))

                {

                    robot.ArmMotor.setPower(0);
                    robot.LinkMotor.setPower(-.8);

                } else if ((robot.ArmMotor.getCurrentPosition() < robot.ArmFinalPosition) && (robot.LinkMotor.getCurrentPosition() <= robot.LinkFinalPosition))

                {

                    robot.ArmMotor.setPower(.8);
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
                    robot.ArmMotor.setPower(-.8);
                    robot.LinkMotor.setPower(.8);
                    telemetry.addData("I am not in less than 20", (robot.LinkMotor.getCurrentPosition()/robot.TickPerDeg));
                }

                else if ( robot.ArmMotor.getCurrentPosition() >= 0 && robot.LinkMotor.getCurrentPosition() >=0)//LinkMotor.getCurrentPosition() <=20 && LinkMotor.getCurrentPosition() <0)
                {

                    robot.ArmMotor.setPower(-.8);
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


        }
    }

        }





